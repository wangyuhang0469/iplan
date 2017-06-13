package com.example.iplan.ui;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.iplan.R;
import com.example.iplan.adapter.MyDatabaseHelper;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.iplan.R.drawable.alarm_check01;

public class ChangePlan extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    @Bind(R.id.tv_left)
    ImageView back;
    @Bind(R.id.edit_year)
    TextView editYear;
    @Bind(R.id.edit_month)
    TextView editMonth;
    @Bind(R.id.edit_dayOfMonth)
    TextView editDayOfMonth;
    @Bind(R.id.edit_dowhat)
    EditText editDowhat;
    @Bind(R.id.chose_time)
    TextView chosetime;
    @Bind(R.id.change)
    Button change;
    @Bind(R.id.isAlarm)
    ToggleButton isAlarm;

    Calendar calendar;
    private int ring = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);
        dbHelper = new MyDatabaseHelper(this,"Time.db",null,2);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        Intent intent = this.getIntent();
        Bundle bunde = intent.getExtras();
        String hour = bunde.getString("hour");
        String min = bunde.getString("min");
        String title = bunde.getString("title");
        String year = bunde.getString("year");
        String mon = bunde.getString("mon");
        String day = bunde.getString("day");
        String text_alarm = bunde.getString("text_alarm");
        int A = Integer.parseInt(mon)-1;
        final String arr []= {hour,min,title,year,A+"",day};
        //设置数组保存现有的数据
        int a = Integer.parseInt(text_alarm);
        if (a == 1)
        isAlarm.setChecked(true);

        calendar.set(Calendar.HOUR_OF_DAY, informat(hour));
        calendar.set(Calendar.MINUTE, informat(min));
        String time = format(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + format(calendar.get(Calendar.MINUTE));

        Toast.makeText(this,mon,Toast.LENGTH_SHORT).show();
        editDowhat.setText(title);
        editYear.setText(year);
        editMonth.setText(mon);
        editDayOfMonth.setText(day);
        chosetime.setText(time);

        chosetime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(ChangePlan.this, android.R.style.Theme_DeviceDefault_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // TODO Auto-generated method stub

                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        String tmps = format(hourOfDay) + ":" + format(minute);
                        chosetime.setText(tmps);


                    }
                }, hour, minute, true).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              editDowhat.getText();
//              editYear.getText();
//              editMonth.getText();
//              editDayOfMonth.getText();
//              chosetime.getText();
//
//

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String hour1 = calendar.get(Calendar.HOUR_OF_DAY)+"";
                String min1 = calendar.get(Calendar.MINUTE)+"";
                String title1 = editDowhat.getText().toString();
//                String text_alarm = tv4.getText().toString();
                values.put("hour",hour1);
                values.put("min",min1);
                values.put("dowhat",title1);
//                values.put("text_alarm",text_alarm);

                db.update("Time",values,"hour = ? and min = ? and dowhat = ? and year = ? and month = ? and dayOfMonth = ?",arr);


                Intent intent = getIntent();
                //这里使用bundle绷带来传输数据
                Bundle bundle = new Bundle();
//              bundle.putSerializable("a", a);
                intent.putExtras(bundle);
                setResult(2, intent);
                finish();

            }
        });


    }
    private int informat(String string){
        int a = Integer.parseInt(string);
        return a;
    }


    private String format(int time) {
        String str = "" + time;
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }
}
