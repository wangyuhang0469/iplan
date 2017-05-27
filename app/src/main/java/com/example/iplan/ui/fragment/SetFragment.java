package com.example.iplan.ui.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.iplan.R;
import com.example.iplan.adapter.base.BaseRecyclerHolder;
import com.example.iplan.base.ImageLoaderFactory;
import com.example.iplan.base.ParentWithNaviFragment;
import com.example.iplan.bean.User;
import com.example.iplan.model.UserModel;
import com.example.iplan.ui.AboutUs;
import com.example.iplan.ui.CircleImageView;
import com.example.iplan.ui.FeedBack;
import com.example.iplan.ui.LoginActivity;
import com.example.iplan.ui.UserInfoChange;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;

/**
 * 设置
 *
 * @author :smile
 * @project:SetFragment
 * @date :2016-01-25-18:23
 */
public class SetFragment extends ParentWithNaviFragment {

    @Bind(R.id.tv_set_name)
    TextView tv_set_name;
//
//    @Bind(R.id.layout_info)
//    RelativeLayout layout_info;

    @Bind(R.id.iv_avator)
    CircleImageView iv_avator;

//    @Bind(R.id.tv_name)
//    TextView tv_name;

    @Bind(R.id.layout_about_us)
    RelativeLayout layout_about_us;

    @Bind(R.id.layout_share)
    RelativeLayout layout_share;

    @Bind(R.id.layout_problems)
    RelativeLayout layout_problems;

    User user;
    BmobIMUserInfo info;



    @Override
    protected String title() {
        return "设置";
    }


    public static SetFragment newInstance() {
        SetFragment fragment = new SetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_set, container, false);
        initNaviView();
        ButterKnife.bind(this, rootView);
        String username = UserModel.getInstance().getCurrentUser().getUsername();
        tv_set_name.setText(TextUtils.isEmpty(username) ? "" : username);
        return rootView;
    }


    //   @OnClick(R.id.layout_info)
//        public void onInfoClick(View view){
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("u", BmobUser.getCurrentUser(User.class));
//            startActivity(UserInfoActivity.class,bundle);
//    }
    //跳转到关于我们页面（对本软件的介绍）
    @OnClick(R.id.layout_about_us)
    public void onInfoClick(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), AboutUs.class);
        startActivity(intent);
    }

    //跳转到关于用户资料页
    @OnClick(R.id.iv_avator)
    public void onUserInfoClick(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), UserInfoChange.class);
        startActivity(intent);
    }

    //分享
    @OnClick(R.id.layout_share)
    public void onShareClick(View view) {
        Intent intent1 = new Intent(Intent.ACTION_SEND);
        intent1.putExtra(Intent.EXTRA_TEXT, "这是iplan APP");
        intent1.setType("text/plain");
        startActivity(Intent.createChooser(intent1, "share"));
    }

    //常见问题反馈
    @OnClick(R.id.layout_problems)
    public void onFeedBackClick(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), FeedBack.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_logout)
    public void onLogoutClick(View view) {
        UserModel.getInstance().logout();
        //可断开连接
        BmobIM.getInstance().disConnect();
        getActivity().finish();
        startActivity(LoginActivity.class, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
