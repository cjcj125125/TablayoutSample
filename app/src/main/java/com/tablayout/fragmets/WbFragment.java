package com.tablayout.fragmets;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tablayout.sample.R;


public class WbFragment extends Fragment {


    public WbFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WbFragment newInstance() {
        WbFragment fragment = new WbFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wb, container, false);
    }

}
