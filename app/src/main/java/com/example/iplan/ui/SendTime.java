package com.example.iplan.ui;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.iplan.R;
import com.example.iplan.adapter.MyDatabaseHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SendTime extends Activity {
    @Bind(R.id.edit_who)
    EditText editWho;
    @Bind(R.id.edit_year)
    EditText editYear;
    @Bind(R.id.edit_month)
    EditText editMonth;
    @Bind(R.id.edit_dayOfMonth)
    EditText editDayOfMonth;
    private MyDatabaseHelper dbHelper;
    Button button;
    ImageView button_back;
    EditText et;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        ButterKnife.bind(this);
        dbHelper = new MyDatabaseHelper(this, "Time.db", null, 2);

        button = (Button) findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.ET);
                et1 = (EditText) findViewById(R.id.ET1);
                String ET = et.getText().toString();
                String ET1 = et1.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("who",editWho.getText().toString());
                values.put("year",editYear.getText().toString());
                values.put("month",editMonth.getText().toString());
                values.put("dayOfMonth",editDayOfMonth.getText().toString());
                values.put("time", ET);
                values.put("thing", ET1);
                db.insert("Time", null, values);
                values.clear();

                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        button_back = (ImageView)findViewById(R.id.back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}