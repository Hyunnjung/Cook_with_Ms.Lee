package kr.ac.jbnu.se.mobileapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import kr.ac.jbnu.se.mobileapp.R;
import kr.ac.jbnu.se.mobileapp.fragment.Init.LoginFragment;
import kr.ac.jbnu.se.mobileapp.fragment.Init.ReadyFragment;

public class InitActivity extends AppCompatActivity {

    ReadyFragment readyFragment;
    LoginFragment loginFragment;

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
        if(i == 1){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out, R.anim.fragment_in_backstack, R.anim.fragment_out_backstack);
            loginFragment = new LoginFragment();
            ft.replace(R.id.container, loginFragment).commit();
        }
    }
}
