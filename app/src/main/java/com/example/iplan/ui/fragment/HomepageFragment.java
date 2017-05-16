package com.example.iplan.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iplan.R;
import com.example.iplan.base.ParentWithNaviFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomepageFragment extends ParentWithNaviFragment {

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
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }


}
