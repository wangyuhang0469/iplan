package com.example.iplan.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.iplan.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoChange extends AppCompatActivity {

    @Bind(R.id.v_top)
    View vTop;

    @Bind(R.id.tv_left)
    ImageView tvLeft;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_avator)
    ImageView ivAvator;
    @Bind(R.id.layout_head)
    RelativeLayout layoutHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.layout_name)
    RelativeLayout layoutName;
    @Bind(R.id.sex)
    TextView sex;
    @Bind(R.id.tv_sex)
    EditText tvSex;
    @Bind(R.id.layout_sex)
    RelativeLayout layoutSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.layout_age)
    RelativeLayout layoutAge;
    @Bind(R.id.tv_birth)
    TextView tvBirth;
    @Bind(R.id.layout_birth)
    RelativeLayout layoutBirth;
    @Bind(R.id.layout_all)
    LinearLayout layoutAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_change);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.v_top)
    public void onViewClicked() {
    }
}
