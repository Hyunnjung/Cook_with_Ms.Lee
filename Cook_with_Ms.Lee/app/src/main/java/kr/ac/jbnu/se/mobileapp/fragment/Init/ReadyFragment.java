package kr.ac.jbnu.se.mobileapp.fragment.Init;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import kr.ac.jbnu.se.mobileapp.R;
import kr.ac.jbnu.se.mobileapp.activity.InitActivity;

public class ReadyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_init_ready, container, false);

        ImageButton ibtn = (ImageButton) rootView.findViewById(R.id.btn_setup_next);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitActivity activity = (InitActivity) getActivity();   //프래그먼트를 관리하는 액티비티 리턴
                activity.onFragmentChanged(1);  //메인 액티비티에 새로 추가할 메소드로 프래그먼트 매니저를 이용해 프래그먼트를 전환하는 메소드
                //액티비티를 본떠 만든 프래그먼트, 액티비티 관리-> 시스템 프래그먼트 관리-> 액티비티
                //액티비티에서 프래그먼트를 전환하도록 해야함
            }
        });

        return rootView;
    }



}
