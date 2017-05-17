package com.example.iplan.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iplan.R;
import com.example.iplan.base.ParentWithNaviFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomepageFragment extends ParentWithNaviFragment {

    @Bind(R.id.hour)
    EditText hour;
    @Bind(R.id.min)
    EditText min;
    @Bind(R.id.dowhat)
    EditText dowhat;
    @Bind(R.id.come)
    Button come;
    @Bind(R.id.show)
    TextView show;

    @Override
    protected String title() {
        return "首页";
    }

    public HomepageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.come)
    public void onViewClicked() {
    }
}
