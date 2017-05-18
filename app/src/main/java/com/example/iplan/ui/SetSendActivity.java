package com.example.iplan.ui;

import android.net.Uri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iplan.R;
import com.example.iplan.base.BaseActivity;
import com.example.iplan.base.ParentWithNaviActivity;
import com.example.iplan.bean.ScheduleMessage;
import com.example.iplan.planclass.Plan;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.exception.BmobException;

public class SetSendActivity extends ParentWithNaviActivity {

    @Bind(R.id.edit_id)
    EditText editId;
    @Bind(R.id.edit_dayOfMonth)
    EditText editDayOfMonth;
    @Bind(R.id.edit_hour)
    EditText editHour;
    @Bind(R.id.edit_min)
    EditText editMin;
    @Bind(R.id.edit_dowhat)
    EditText editDowhat;
    @Bind(R.id.edit_alarm)
    EditText editAlarm;
    @Bind(R.id.edit_time)
    EditText editTime;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.btn_send)
    Button btnSend;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private BmobIMUserInfo info ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_send);
        ButterKnife.bind(this);
        info = (BmobIMUserInfo) getBundle().getSerializable("userInfo");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @OnClick({R.id.btn_add, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                break;
            case R.id.btn_send:
//                Plan a = new Plan(Long.parseLong(editId.getText().toString()),Integer.parseInt(editDayOfMonth.getText().toString(),
//                        Integer.parseInt(editHour.getText().toString(),Integer.parseInt(editMin.getText().toString(),);
                sendSchedukeMessage();
                break;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SetSend Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    private void sendSchedukeMessage() {
        //启动一个会话，如果isTransient设置为true,则不会创建在本地会话表中创建记录，
        //设置isTransient设置为false,则会在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中

        BmobIMConversation d = BmobIM.getInstance().startPrivateConversation(info, true, null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), d);
        ScheduleMessage msg = new ScheduleMessage();
//        User currentUser = BmobUser.getCurrentUser(User.class);
        msg.setContent("发送了个时间表给你");//给对方的一个留言信息
        Map<String, Object> map = new HashMap<>();
//        map.put("name", currentUser.getUsername());//发送者姓名，这里只是举个例子，其实可以不需要传发送者的信息过去
//        map.put("avatar",currentUser.getAvatar());//发送者的头像
//        map.put("uid",currentUser.getObjectId());//发送者的uid
        map.put("hour", "999");
        map.put("min", "111");
        map.put("dowhat", "来自聊天界面的信息");
        msg.setExtraMap(map);
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e) {
                if (e == null) {//发送成功
                    toast("发送计划表成功");
                } else {//发送失败
                    toast("发送失败:" + e.getMessage());
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    @Override
    protected String title() {
        return "发送计划表";
    }
}
