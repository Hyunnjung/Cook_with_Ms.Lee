package kr.ac.jbnu.se.mobileapp.fragment.Init;
/**
 * Copyright 2018 All rights reserved by WaySeekers.
 *
 * @author bongO moon
 * @since 2018. 04. 11.
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import kr.ac.jbnu.se.mobileapp.R;
import kr.ac.jbnu.se.mobileapp.activity.InitActivity;
import kr.ac.jbnu.se.mobileapp.activity.MainActivity;

public class LoginFragment extends Fragment {
    private SessionCallback callback;
    private FirebaseFirestore db;

    /**
     * 로그인 버튼을 클릭 했을시 access token을 요청하도록 설정한다.
     *
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_init_login, container, false);

        db = FirebaseFirestore.getInstance();

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

        //네트워크 연결이 안될 시 토스트 날림
        if (!isConnected()){
            Log.d("Net", "not connect");
            Toast.makeText(getActivity().getApplicationContext(), "인터넷을 연결하고 다시 시도해주세요", Toast.LENGTH_LONG).show();
        }

        //kakao login button set
        final LoginButton loginButton = (LoginButton) view.findViewById(R.id.btn_login_kakao);
        loginButton.setSuportFragment(this); // set fragment for LoginButton


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Session.getCurrentSession().removeCallback(callback);
    }

    //카카오 세션 콜백
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Log.d("ErrorSession", exception.getMessage());
                Logger.e(exception);
            }
        }
    }

    protected void requestMe() {
        UserManagement.getInstance().requestMe(new MeResponseCallback() {

            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.d("Error", "오류로 카카오로그인 실패 ");
            }
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("Error", "오류로 카카오로그인 실패 ");
            }
            @Override
            public void onNotSignedUp() {
            }
            @Override
            public void onSuccess(UserProfile userProfile) {
                //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.
                if(!userProfile.getEmailVerified()){
                    Toast.makeText(getActivity().getApplicationContext(), "이메일을 인증해주세요.", Toast.LENGTH_LONG);
                }
                else {
                    db.collection("users")
                            .add(userProfile)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("firebase", "Error adding document", e);
                                }
                            });
                    Log.e("UserProfile", userProfile.toString());
                    redirectMainActivity();
                }
            }
        });
    }

//    private void requestAccessTokenInfo() {
//        AuthService.getInstance().requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
//            @Override
//            public void onSessionClosed(ErrorResult errorResult) {
//
//            }
//
//            @Override
//            public void onNotSignedUp() {
//                // not happened
//            }
//
//            @Override
//            public void onFailure(ErrorResult errorResult) {
//                Logger.e("failed to get access token info. msg=" + errorResult);
//            }
//
//            @Override
//            public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
//                long userId = accessTokenInfoResponse.getUserId();
//                Logger.d("this access token is for userId=" + userId);
//
//                long expiresInMilis = accessTokenInfoResponse.getExpiresInMillis();
//                Logger.d("this access token expires after " + expiresInMilis + " milliseconds.");
//            }
//        });
//    }

    private void redirectMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void redirectSetupFragment(){
        InitActivity activity = (InitActivity) getActivity();
        activity.onFragmentChanged(2);
    }

    private boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}