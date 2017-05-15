package com.example.iplan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 王先生 on 2017/5/9.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // TODO Auto-generated method stub
        Toast.makeText(arg0, "你设置的闹铃时间到了", Toast.LENGTH_LONG).show();
    }
}