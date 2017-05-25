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
import com.example.iplan.base.ImageLoaderFactory;
import com.example.iplan.bean.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.bean.BmobIMUserInfo;

public class UserInfoChange extends AppCompatActivity {


        @Bind(R.id.iv_avator)
        ImageView iv_avator;
        @Bind(R.id.tv_name1)
        TextView tv_name1;


//    @Bind(R.id.btn_chat)
//    Button btn_chat;

        User user;
        BmobIMUserInfo info;
//
//        @Override
//        protected String title() {
//            return "个人资料";
//        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_info_change);
            ButterKnife.bind(this);
//            initNaviView();
//            user = (User) getBundle().getSerializable("u");
            //构造聊天方的用户信息:传入用户id、用户名和用户头像三个参数
//            info = new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar());
//            ImageLoaderFactory.getLoader().loadAvator(iv_avator, user.getAvatar(), R.mipmap.head);
//            tv_name1.setText(user.getUsername());
        }


}













