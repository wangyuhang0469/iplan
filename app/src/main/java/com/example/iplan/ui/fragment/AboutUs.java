package com.example.iplan.ui.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iplan.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutUs extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_left)
    ImageView tv_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        String a="关于我们";
        tv_title.setText(a);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
