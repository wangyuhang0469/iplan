package com.example.iplan.ui.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.iplan.R;
import com.example.iplan.adapter.MyDatabaseHelper;
import com.example.iplan.base.ParentWithNaviFragment;
import com.example.iplan.ui.MainActivity;
import com.example.iplan.ui.SendTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomepageFragment extends ParentWithNaviFragment {
    private MyDatabaseHelper dbHelper;
    private ListView listView;
    private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    private SimpleAdapter simpleAdapter;

//    @Bind(R.id.hour)
//    EditText hour;
//    @Bind(R.id.min)
//    EditText min;
//    @Bind(R.id.dowhat)
//    EditText dowhat;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.button2)
    Button show;
    @Bind(R.id.btn_last)
    Button btnLast;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected String title() {
        return "首页";
    }

    public HomepageFragment() {
        // Required empty public constructor
    }

    private Calendar c = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        int a = c.get(Calendar.YEAR);
        date.setText(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日");

        simpleAdapter = new SimpleAdapter(getActivity(),getData(),R.layout.listview_item,new String[]{"time","thing"},new int[]{R.id.show_time,R.id.title});

        listview.setAdapter(simpleAdapter);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SendTime.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

//    @OnClick(R.id.come)
//    public void onViewClicked() {
//    }

    @OnClick({R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_last:
                c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)-1));
                date.setText(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日");
                break;
            case R.id.btn_next:
                c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)+1));
                date.setText(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日");
                break;
        }
    }
    private List<Map<String, Object>> getData() {
        dbHelper = new MyDatabaseHelper(getActivity(),"Time.db",null,2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Time",null);
        //清空list
        list.clear();
        //查询到的数据添加到list集合
        while(cursor.moveToNext()){
            String time = cursor.getString(1);
            String title = cursor.getString(2);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("time",time);
            map.put("thing",title);
            list.add(map);
        }
        return list;
    }
}
