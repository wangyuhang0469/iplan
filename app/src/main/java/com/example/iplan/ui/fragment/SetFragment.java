package com.example.iplan.ui.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iplan.R;
import com.example.iplan.adapter.base.BaseRecyclerHolder;
import com.example.iplan.base.ImageLoaderFactory;
import com.example.iplan.base.ParentWithNaviFragment;
import com.example.iplan.bean.User;
import com.example.iplan.model.UserModel;
import com.example.iplan.ui.CircleImageView;
import com.example.iplan.ui.FeedBack;
import com.example.iplan.ui.Help;
import com.example.iplan.ui.LoginActivity;
import com.example.iplan.ui.MainActivity;
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

    @Bind(R.id.layout_help)
    RelativeLayout layout_help;


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

        //打客服电话
        AlertDialog.Builder AdBuilder = new AlertDialog.Builder(getActivity());
//        AdBuilder.setTitle("<(＾－＾)>");
//        AdBuilder.setMessage("您将要拨打电话至15231170279");
//        AdBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent i = new Intent();
//                i.setAction(Intent.ACTION_CALL);
//                i.setData(Uri.parse("tel:15231170279"));
//                startActivity(i);
//            }
//        });
//        AdBuilder.setNegativeButton("取消", null);
//        AdBuilder.create();
//        AdBuilder.show();
    }

    //跳转到关于我们页面（对本软件的介绍）
    @OnClick(R.id.layout_help)
    public void onHelpClick(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), Help.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_logout)
    public void onLogoutClick(View view) {

//直接退出
//        UserModel.getInstance().logout();
//        //可断开连接
//        BmobIM.getInstance().disConnect();
//        getActivity().finish();
//        startActivity(LoginActivity.class, null);

        //弹出退出提示框
        AlertDialog.Builder AdBuilder = new AlertDialog.Builder(getActivity());
            AdBuilder.setTitle("确认退出吗？");
            AdBuilder.setIcon(android.R.drawable.ic_dialog_info);
            AdBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“确认”后的操作
                            UserModel.getInstance().logout();
                            //可断开连接
                            BmobIM.getInstance().disConnect();
                            getActivity().finish();
                            startActivity(LoginActivity.class, null);

                        }
                    });
            AdBuilder.setNegativeButton("返回", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“返回”后的操作,这里不设置没有任何操作
                        }
                    }).show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
