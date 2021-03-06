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
import android.widget.ToggleButton;

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
    ToggleButton isAlarm;
    @Bind(R.id.send)
    Button send;
    @Bind(R.id.tv_left)
    ImageView back;
    @Bind(R.id.date_tv)
    TextView date_tv;

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
            String year1 =format( c.get(Calendar.YEAR)-2000);
            String month1 =format( c.get(Calendar.MONTH));
            String day1 =format( c.get(Calendar.DAY_OF_MONTH));
            String hour1 =format( c.get(Calendar.HOUR_OF_DAY));
            String min =format( c.get(Calendar.MINUTE));
            String a=year1+month1+day1+hour1+min;
            //获取制定计划选中时间
            String year2=format(calendar.get(Calendar.YEAR)-2000);
            String month2=format(calendar.get(Calendar.MONTH));
            String day2=format(calendar.get(Calendar.DAY_OF_MONTH));
            String hour2=format(calendar.get(Calendar.HOUR_OF_DAY));
            String min2=format(calendar.get(Calendar.MINUTE));
            String b=year2+month2+day2+hour2+min2;
            int m=Integer.valueOf(a).intValue();
            int n=Integer.valueOf(b).intValue();

            int i=m-n;


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
                values.put("alarm", isAlarm.isChecked());
                db.insert("Time", null, values);
                values.clear();
                if (isAlarm.isChecked()){
                int id = calendar.get(Calendar.MONTH)*1000000 + calendar.get(Calendar.DAY_OF_MONTH)*10000 + calendar.get(Calendar.HOUR_OF_DAY)*100+calendar.get(Calendar.MINUTE);
                AlarmManagerUtil.setAlarm(this, 1, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), id, 0, editDowhat.getText().toString(), ring);
                Toast.makeText(this, "闹钟已设置", Toast.LENGTH_SHORT).show();}
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
                calendar.set(Calendar.YEAR, year);
                editYear.setText(format(calendar.get(Calendar.YEAR)));
                editMonth.setText(format(calendar.get(Calendar.MONTH) + 1));
                editDayOfMonth.setText(format(calendar.get(Calendar.DAY_OF_MONTH)));


            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


}
