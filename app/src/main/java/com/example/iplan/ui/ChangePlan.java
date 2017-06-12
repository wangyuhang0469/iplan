package com.example.iplan.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iplan.R;

import butterknife.Bind;

public class ChangePlan extends AppCompatActivity {

    @Bind(R.id.edit_year)
    TextView editYear;
    @Bind(R.id.edit_month)
    TextView editMonth;
    @Bind(R.id.edit_dayOfMonth)
    TextView editDayOfMonth;
    @Bind(R.id.edit_dowhat)
    EditText editDowhat;
    @Bind(R.id.date_tv)
    TextView date_tv;


    private String hour = "null";
    private String min="null";
    private String title="null";
    private String time="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);

        Intent intent=this.getIntent();
        Bundle bunde = intent.getExtras();
        hour = bunde.getString("hour");
        min = bunde.getString("min");
        title = bunde.getString("title");
        time = hour+ ":" +min;
        Toast.makeText(this,time,Toast.LENGTH_LONG).show();

        date_tv.setText(time);

    }
}
