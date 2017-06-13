package com.example.iplan.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iplan.R;
import com.example.iplan.planclass.Plan;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);
        ButterKnife.bind(this);

        Intent intent=this.getIntent();
        Bundle bunde = intent.getExtras();
        String hour = bunde.getString("hour");
        String min = bunde.getString("min");
        String title = bunde.getString("title");
        String year = bunde.getString("year");
        String mon = bunde.getString("mon");
        String day = bunde.getString("day");
        String time = hour+ ":" +min;
//        Toast.makeText(this,time,Toast.LENGTH_LONG).show();
//        editYear.setText();
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
//              Intent intent = getIntent();
//              //这里使用bundle绷带来传输数据
//              Bundle bundle = new Bundle();
////              bundle.putSerializable("a", a);
//              intent.putExtras(bundle);
//              setResult(RESULT_OK, intent);
//              finish();

          }
      });



    }
}
