package com.tablayout.fragmets;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tablayout.dbutils.DataDao;
import com.tablayout.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QqFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QqFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.del)
    Button del;
    @BindView(R.id.upda)
    Button upda;
    @BindView(R.id.query)
    Button query;
    private View rootView;
    private DataDao daDao;

    public QqFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QqFragment newInstance() {
        QqFragment fragment = new QqFragment();
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
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_qq, container, false);
            ButterKnife.bind(this, rootView);
            setListener();
        } else {
            ViewGroup parentView = (ViewGroup) container.getParent();
            if (parentView != null) {
                parentView.removeView(rootView);
            }
        }
        return rootView;
    }

    private void setListener() {
        add.setOnClickListener(this);
        del.setOnClickListener(this);
        upda.setOnClickListener(this);
        query.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (daDao == null) {
            daDao = new DataDao(getActivity());
        }
        switch (v.getId()) {
            case R.id.add:
                daDao.addData();
                break;
            case R.id.del:
                daDao.delById(1);
                break;
            case R.id.upda:
                daDao.update(2);
                break;
            case R.id.query:
                daDao.queryAll();
                break;

        }
    }
}
