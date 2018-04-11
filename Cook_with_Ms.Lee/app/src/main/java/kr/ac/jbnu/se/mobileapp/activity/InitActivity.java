package kr.ac.jbnu.se.mobileapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import kr.ac.jbnu.se.mobileapp.R;
import kr.ac.jbnu.se.mobileapp.fragment.Init.ReadyFragment;

public class InitActivity extends AppCompatActivity {

    public static final String INTENT_SETUP_TYPE = "setupType";
    public static final int SETUP_TYPE_NONE = 0;
    public static final int SETUP_TYPE_USER = 1;
    public static final int SETUP_TYPE_SETUP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        int setupType = getIntent().getIntExtra(INTENT_SETUP_TYPE, SETUP_TYPE_NONE);
        Bundle b = new Bundle();
        b.putInt(INTENT_SETUP_TYPE, setupType);

        fragmentUtil = new FragmentUtil(getSupportFragmentManager(), R.id.content);
        switch(setupType){
            case SETUP_TYPE_USER:
                fragmentUtil.add(new LoginFragment(), b);
                break;
            case SETUP_TYPE_SETUP:
                fragmentUtil.add(new UserInfoFragment(), b);
                break;
            default:
                if(!SharedPreferencesUtil.getInstance().hasRecentAddress() || !SharedPreferencesUtil.getInstance().hasUserId()){
                    b.putInt(INTENT_SETUP_TYPE, SETUP_TYPE_NONE);
                    fragmentUtil.add(new ReadyFragment(), b);
                }else{
                    Status.USER_ID = SharedPreferencesUtil.getInstance().getUserId();
                    Status.USER_FBID = SharedPreferencesUtil.getInstance().getFbId();
                    Status.USER_NAME = SharedPreferencesUtil.getInstance().getUserName();

                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        for(Fragment f : getSupportFragmentManager().getFragments())
            f.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
    }
}
