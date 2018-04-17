package kr.ac.jbnu.se.mobileapp.activity;
/**
 * Copyright 2018 All rights reserved by WaySeekers.
 *
 * @author bongO moon
 * @since 2018. 04. 09.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import kr.ac.jbnu.se.mobileapp.R;
import kr.ac.jbnu.se.mobileapp.fragment.Init.LoginFragment;
import kr.ac.jbnu.se.mobileapp.fragment.Init.ReadyFragment;
import kr.ac.jbnu.se.mobileapp.fragment.Init.SetupFragment;

public class InitActivity extends FragmentActivity {

    private ReadyFragment readyFragment;
    private LoginFragment loginFragment;
    private SetupFragment setupFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        readyFragment = new ReadyFragment();
        ft.add(R.id.container, readyFragment).commit();
    }

    //beginTransaction()은 트랜스잭션 객체가 생성 및 시작 - commit()은 작업 실행
    public void onFragmentChanged(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(i == 1){
            ft.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out, R.anim.fragment_in_backstack, R.anim.fragment_out_backstack);
            loginFragment = new LoginFragment();
            ft.replace(R.id.container, loginFragment).commit();
        }
        else if(i == 2){
            ft.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out, R.anim.fragment_in_backstack, R.anim.fragment_out_backstack);
            setupFragment = new SetupFragment();
            ft.replace(R.id.container, setupFragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
