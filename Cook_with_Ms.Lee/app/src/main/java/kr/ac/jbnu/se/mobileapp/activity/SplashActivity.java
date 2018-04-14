package kr.ac.jbnu.se.mobileapp.activity;
/**
 * Copyright 2018 All rights reserved by WaySeekers.
 *
 * @author bongO moon
 * @since 2018. 04. 14.
 */
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.ContentValues.TAG;
import static com.kakao.util.helper.Utility.getPackageInfo;

//1. 로그인 여부 x
//-> 이닛 액티비티로 간다.
//2. 로그인 여부 O
//-> 메인 액티비티로 간다.
//UserProfile을 저장할 공간 / 서비스 /

public class SplashActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        Log.d("kakao keyhash value", getKeyHash(mContext));

        try{
            Thread.sleep(2500);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, InitActivity.class));
        finish();
    }

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }
}
