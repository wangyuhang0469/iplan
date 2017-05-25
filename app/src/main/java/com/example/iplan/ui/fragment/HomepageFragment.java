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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    @Bind(R.id.btn_last)
    Button btnLast;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected String title() {
        return "首页";
    }

    public HomepageFragment() {
        // Required empty public constructor
    }

    private Calendar c = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        int a = c.get(Calendar.YEAR);
        date.setText(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日");
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

    @OnClick({R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_last:
                c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)-1));
                date.setText(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日");
                break;
            case R.id.btn_next:
                c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)+1));
                date.setText(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日");
                break;
        }
    }
}
