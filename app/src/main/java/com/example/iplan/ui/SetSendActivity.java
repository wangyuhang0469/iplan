package com.example.iplan.ui;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.iplan.R;
import com.example.iplan.adapter.ChatAdapter;
import com.example.iplan.base.BaseActivity;
import com.example.iplan.base.ParentWithNaviActivity;
import com.example.iplan.bean.ScheduleMessage;
import com.example.iplan.planclass.Plan;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.orhanobut.logger.Logger;

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
    @Bind(R.id.isAlarm)
    Switch isAlarm;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_send);
        ButterKnife.bind(this);
//        info = (BmobIMUserInfo) getBundle().getSerializable("userInfo");
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
                Plan a = new Plan(Integer.parseInt(editId.getText().toString()),
                        Integer.parseInt(editDayOfMonth.getText().toString()),
                        Integer.parseInt(editHour.getText().toString()),
                        Integer.parseInt(editMin.getText().toString()),
                        editDowhat.getText().toString(),
                        isAlarm.isChecked());
                Intent intent =getIntent();
                //这里使用bundle绷带来传输数据
                Bundle bundle =new Bundle();
                //传输的内容仍然是键值对的形式
                bundle.putString("second","hello world from secondActivity!");//回发的消息,hello world from secondActivity!
                bundle.putSerializable("a", a);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
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