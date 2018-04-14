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
                InitActivity activity = (InitActivity) getActivity();
                activity.onFragmentChanged(1);
            }
        });

        return rootView;
    }
}
