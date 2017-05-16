package com.example.iplan.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iplan.R;
import com.example.iplan.base.ParentWithNaviFragment;

public class BlankFragment extends ParentWithNaviFragment
{
    @Override
    protected String title() {
        return "空白";
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }



}