package com.example.iplan.planclass;

import java.io.Serializable;

/**
 * Created by 王先生 on 2017/5/18.
 */

public class Plan implements Serializable {



    private int id;
    private int dayOfMonth;
    private int hour;
    private int min;
    private boolean alarm;
    private String dowhat;
    private Long time;

//    public Plan(Long id,int dayOfMonth,){}

    public Plan(int id){
        this.id = id;
    };

    public Plan(int id,int dayOfMonth,int hour,int min,String dowhat,boolean alarm){
        this.id=id;
        this.dayOfMonth=dayOfMonth;
        this.hour=hour;
        this.min=min;
        this.dowhat=dowhat;
        this.alarm=alarm;

    }



    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public String getDowhat() {
        return dowhat;
    }

    public void setDowhat(String dowhat) {
        this.dowhat = dowhat;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
