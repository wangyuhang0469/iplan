package com.example.iplan.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import cn.bmob.newim.BmobIM;

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
    TextView isAlarm;
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
    private int year1;
    private int year2;
    private int month1;
    private int month2;
    private int day1;
    private int day2;
    private int hour1;
    private int hour2;
    private int min;
    private int min2;
    private String a;
    private String b;
    private int m;
    private int n;
    private int i;

    private Date dateStart;
    private Date dateEnd;


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

                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
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

            //获取系统时间
           Calendar c = Calendar.getInstance();
            year1 = c.get(Calendar.YEAR);
            month1 = c.get(Calendar.MONTH);
            day1 = c.get(Calendar.DAY_OF_MONTH);
            hour1 = c.get(Calendar.HOUR_OF_DAY);
            min = c.get(Calendar.MINUTE);
            a=String.valueOf(year1)+String.valueOf(month1)+String.valueOf(day1)+String.valueOf(hour1)+String.valueOf(min);

            //获取制定计划选中时间
            year2=calendar.get(Calendar.YEAR);
            month2=calendar.get(Calendar.MONTH);
            day2=calendar.get(Calendar.DAY_OF_MONTH);
            hour2= calendar.get(Calendar.HOUR_OF_DAY);
            min2=calendar.get(Calendar.MINUTE);
            b=String.valueOf(year2)+String.valueOf(month2)+String.valueOf(day2)+String.valueOf(hour2)+String.valueOf(min2);
            m=Integer.valueOf(a).intValue();
            n=Integer.valueOf(b).intValue();

            i=m-n;


            if (i<0) {
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
                AlertDialog.Builder AdBuilder = new AlertDialog.Builder(SetPlanActivity.this);
                AdBuilder.setTitle("只能给未来的时间安排计划");
                AdBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }





//
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            String who = UserModel.getInstance().getCurrentUser().getUsername();
//            values.put("who", who);
//            values.put("year", calendar.get(Calendar.YEAR) + "");
//            values.put("month", calendar.get(Calendar.MONTH) + "");
//            values.put("dayOfMonth", calendar.get(Calendar.DAY_OF_MONTH) + "");
//            values.put("hour", calendar.get(Calendar.HOUR_OF_DAY) + "");
//            values.put("min", calendar.get(Calendar.MINUTE) + "");
//            values.put("dowhat", editDowhat.getText().toString());
//            values.put("alarm", isAlarm.toString());
//            db.insert("Time", null, values);
//            values.clear();
//            Intent intent = getIntent();
//            setResult(RESULT_OK, intent);
//            finish();


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
