package com.example.iplan.ui;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.iplan.R;
import com.example.iplan.adapter.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class PreviousPlansActivity extends Activity {


    private ListView listView;
    private MyDatabaseHelper dbHelper;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private SimpleAdapter simpleAdapter;
    private int lastPress = 0;
    private boolean delState = false;
    private String who;
    int[] imageId = new int[] { R.drawable.alarm_check0, R.drawable.alarm_check01};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_plans);
        ButterKnife.bind(this);

        Intent intent=this.getIntent();
        Bundle bunde = intent.getExtras();
        who = bunde.getString("who");
        listView = (ListView)findViewById(R.id.list);

        simpleAdapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"hour", "min","dowhat","alarm","year","month","dayOfMonth"}, new int[]{R.id.show_hour,R.id.show_min,R.id.title,R.id.home_alarm,R.id.show_year,R.id.show_month,R.id.show_day});

        listView.setAdapter(simpleAdapter);

    }

    private List<Map<String, Object>> getData() {
        dbHelper = new MyDatabaseHelper(this, "Time.db", null, 2);
        SQLiteDatabase DB = dbHelper.getReadableDatabase();
        String a = "select * from Time where who ='"+who+ "'group by month,dayOfMonth,hour,min ORDER BY cast(year as int) DESC, cast(month as int) DESC, cast(dayOfMonth as int) DESC, cast(hour as int) ASC, cast(min as int) ASC";
        Cursor cursor = DB.rawQuery(a, null);
        //清空list
        list.clear();
        //查询到的数据添加到list集合
        while (cursor.moveToNext()) {
            String hour = format(cursor.getString(5));
            String min = format(cursor.getString(6));
            String year = format(cursor.getString(2));
            String month = format(cursor.getString(3));
            String dayOfMonth = format(cursor.getString(4));
            String dowhat = cursor.getString(7);
            int isalarm = cursor.getInt(8);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("hour", hour);
            map.put("min",min);
            map.put("year",year);
            map.put("month",month);
            map.put("dayOfMonth",dayOfMonth);
            map.put("dowhat", dowhat);
            map.put("alarm", imageId[isalarm]);
            list.add(map);
        }
        return list;
    }

    private String format(String time) {
        String str = "" + time;
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

}
