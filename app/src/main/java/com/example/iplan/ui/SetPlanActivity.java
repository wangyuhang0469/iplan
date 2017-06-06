package com.example.iplan.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.iplan.R;
import com.example.iplan.adapter.MyDatabaseHelper;
import com.example.iplan.clock.AlarmManagerUtil;
import com.example.iplan.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPlanActivity extends Activity {

    @Bind(R.id.edit_year)
    TextView editYear;
    @Bind(R.id.edit_month)
    TextView editMonth;
    @Bind(R.id.edit_dayOfMonth)
    TextView editDayOfMonth;
    @Bind(R.id.edit_dowhat)
    EditText editDowhat;
    @Bind(R.id.isAlarm)
    Switch isAlarm;
    @Bind(R.id.send)
    Button send;
    @Bind(R.id.tv_left)
    ImageView back;
    @Bind(R.id.date_tv)
    TextView date_tv;
    @Bind(R.id.setalarm)
    Button setalarm;

    private MyDatabaseHelper dbHelper;
    Calendar calendar;
    private int ring = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_plan_layout);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        dbHelper = new MyDatabaseHelper(this, "Time.db", null, 2);
        editYear.setText(format(calendar.get(Calendar.YEAR)));
        editMonth.setText(format(calendar.get(Calendar.MONTH) + 1));
        editDayOfMonth.setText(format(calendar.get(Calendar.DAY_OF_MONTH)));

        String now = format(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + format(calendar.get(Calendar.MINUTE));
        date_tv.setText(now);

        date_tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(SetPlanActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // TODO Auto-generated method stub


                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        String tmps = format(hourOfDay) + ":" + format(minute);
                        date_tv.setText(tmps);


                    }
                }, hour, minute, true).show();
            }
        });

    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    @OnClick(R.id.send)
    public void onViewClicked() {
        if (editDowhat.length() != 0) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            String who = UserModel.getInstance().getCurrentUser().getUsername();
            values.put("who", who);
            values.put("year", calendar.get(Calendar.YEAR) + "");
            values.put("month", calendar.get(Calendar.MONTH) + "");
            values.put("dayOfMonth", calendar.get(Calendar.DAY_OF_MONTH) + "");
            values.put("hour", calendar.get(Calendar.HOUR_OF_DAY) + "");
            values.put("min", calendar.get(Calendar.MINUTE) + "");
            values.put("dowhat", editDowhat.getText().toString());
            values.put("alarm", isAlarm.toString());

            db.insert("Time", null, values);
            values.clear();
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "请输入你的计划", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_left)
    public void onBackClicked() {
        finish();
    }


    private String format(int time) {
        String str = "" + time;
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    @OnClick({R.id.edit_month, R.id.edit_dayOfMonth, R.id.edit_year})
    public void onViewClicked(View view) {
        new DatePickerDialog(SetPlanActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                editYear.setText(format(calendar.get(Calendar.YEAR)));
                editMonth.setText(format(calendar.get(Calendar.MONTH) + 1));
                editDayOfMonth.setText(format(calendar.get(Calendar.DAY_OF_MONTH)));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.setalarm)
    public void onSetAlarmClicked() {
        AlarmManagerUtil.setAlarm(this, 1, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), 1, 0, "闹钟响了", ring);
        Toast.makeText(this, calendar.get(Calendar.HOUR_OF_DAY)+""+calendar.get(Calendar.MINUTE), Toast.LENGTH_SHORT).show();
    }
}
