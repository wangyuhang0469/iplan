package com.example.iplan.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.iplan.DemoMessageHandler;
import com.example.iplan.ui.fragment.HomepageFragment;

/**
 * Created by Administrator on 2017/5/24.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_TIME = "create table Time("+"id integer primary key autoincrement,"+"who set_plan_layout,"+"year set_plan_layout,"+"month set_plan_layout,"+"dayOfMonth set_plan_layout,"+"hour set_plan_layout,"+"min set_plan_layout,"+"dowhat set_plan_layout,"+"alarm set_plan_layout,"+"createTime set_plan_layout)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TIME);
        Toast.makeText(mContext,"创建成功",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase dn,int oldVersion,int newVersion){

    }
}
