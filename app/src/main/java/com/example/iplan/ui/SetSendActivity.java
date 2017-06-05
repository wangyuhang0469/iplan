package com.example.iplan.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.iplan.R;
import com.example.iplan.base.ParentWithNaviActivity;
import com.example.iplan.planclass.Plan;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetSendActivity extends ParentWithNaviActivity {


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
    @Bind(R.id.edit_who)
    EditText editWho;
    @Bind(R.id.edit_year)
    EditText editYear;
    @Bind(R.id.edit_month)
    EditText editMonth;
    @Bind(R.id.activity_set_send)
    LinearLayout activitySetSend;
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
                Plan a = new Plan(editWho.getText().toString(),
                        Integer.parseInt(editYear.getText().toString()),
                        Integer.parseInt(editMonth.getText().toString()),
                        Integer.parseInt(editDayOfMonth.getText().toString()),
                        Integer.parseInt(editHour.getText().toString()),
                        Integer.parseInt(editMin.getText().toString()),
                        editDowhat.getText().toString(),
                        isAlarm.isChecked(),
                        Long.parseLong(editMin.getText().toString()));
                Intent intent = getIntent();
                //这里使用bundle绷带来传输数据
                Bundle bundle = new Bundle();
                bundle.putSerializable("a", a);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
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