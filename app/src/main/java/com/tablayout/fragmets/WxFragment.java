package com.tablayout.fragmets;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tablayout.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WxFragment extends Fragment {


    @BindView(R.id.listView)
    ListView listView;

    public WxFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WxFragment newInstance() {
        WxFragment fragment = new WxFragment();
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
        View view = inflater.inflate(R.layout.fragment_wx, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();

    }

    private void initview() {
        List<String>strList=new ArrayList<>();
        for(int i=0;i<100;i++){
            strList.add("条目"+i);
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strList);
        listView.setAdapter(adapter);
    }
}
