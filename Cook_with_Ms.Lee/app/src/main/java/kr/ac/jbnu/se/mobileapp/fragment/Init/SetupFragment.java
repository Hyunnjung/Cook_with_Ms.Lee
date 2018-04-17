package kr.ac.jbnu.se.mobileapp.fragment.Init;
/**
 * Copyright 2018 All rights reserved by WaySeekers.
 *
 * @author bongO moon
 * @since 2018. 04. 11.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import kr.ac.jbnu.se.mobileapp.R;
import kr.ac.jbnu.se.mobileapp.activity.InitActivity;

public class SetupFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_init_setup, container, false);

        ImageButton ibtn = (ImageButton) view.findViewById(R.id.);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitActivity activity = (InitActivity) getActivity();
                activity.onFragmentChanged(2);
            }
        });

        return view;
    }
}
