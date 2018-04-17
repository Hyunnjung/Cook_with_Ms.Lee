package kr.ac.jbnu.se.mobileapp.activity;
/**
 * Copyright 2018 All rights reserved by WaySeekers.
 *
 * @author bongO moon
 * @since 2018. 04. 14.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.ac.jbnu.se.mobileapp.R;

public class MainActivity extends AppCompatActivity {

    //SQLiteDatabase db;  //db객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
