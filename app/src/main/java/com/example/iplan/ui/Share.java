package com.example.iplan.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Share extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * Android原生分享功能
     * @param appName:要分享的应用程序名称
     */
    private void share(String appName) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType("text/plain");
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "f分享");
        share_intent.putExtra(Intent.EXTRA_TEXT, "HI 推荐您使用一款软件:" + appName);
        share_intent = Intent.createChooser(share_intent, "分享");
        startActivity(share_intent);
    }
}
