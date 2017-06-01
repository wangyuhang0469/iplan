package com.example.iplan.ui;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.iplan.R;
import com.example.iplan.adapter.MyDatabaseHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendTime extends Activity {
    @Bind(R.id.edit_who)
    EditText editWho;
    @Bind(R.id.edit_year)
    EditText editYear;
    @Bind(R.id.edit_month)
    EditText editMonth;
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
    @Bind(R.id.send)
    Button send;
    private MyDatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        ButterKnife.bind(this);
        dbHelper = new MyDatabaseHelper(this, "Time.db", null, 2);

    }

    @OnClick(R.id.send)
    public void onViewClicked() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("who", editWho.getText().toString());
        values.put("year", editYear.getText().toString());
        values.put("month", editMonth.getText().toString());
        values.put("dayOfMonth", editDayOfMonth.getText().toString());
        values.put("hour",editHour.getText().toString() );
        values.put("min", editMin.getText().toString());
        values.put("dowhat", editDowhat.getText().toString());
        values.put("alarm", isAlarm.toString());
        values.put("createTime", editTime.getText().toString());

        db.insert("Time", null, values);
        values.clear();
        Intent intent = getIntent();
        setResult(RESULT_OK, intent);
        finish();
    }
}