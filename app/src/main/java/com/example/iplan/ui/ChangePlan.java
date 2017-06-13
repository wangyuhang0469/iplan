package com.example.iplan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.iplan.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.iplan.R.drawable.alarm_check01;

public class ChangePlan extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bunde = intent.getExtras();
        String hour = bunde.getString("hour");
        String min = bunde.getString("min");
        String title = bunde.getString("title");
        String year = bunde.getString("year");
        String mon = bunde.getString("mon");
        String day = bunde.getString("day");
        String text_alarm = bunde.getString("text_alarm");
        int a = Integer.parseInt(text_alarm);
        String time = hour + ":" + min;
        if (a == 1)
        isAlarm.setChecked(true);

        editDowhat.setText(title);
        editYear.setText(year);
        editMonth.setText(mon);
        editDayOfMonth.setText(day);
        chosetime.setText(time);

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
}
