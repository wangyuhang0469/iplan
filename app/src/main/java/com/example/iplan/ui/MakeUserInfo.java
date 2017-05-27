package com.example.iplan.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.iplan.R;
import com.example.iplan.model.UserModel;

public class MakeUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_user_info);

//        String bbb = UserModel.getInstance().getCurrentUser().setEmail();
    }
}
