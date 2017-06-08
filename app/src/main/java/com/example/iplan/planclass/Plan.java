package com.example.iplan.planclass;

import java.io.Serializable;

/**
 * Created by 王先生 on 2017/5/18.
 */

public class Plan implements Serializable {


    private String who;
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int min;
    private boolean alarm;
    private String dowhat;
    private Long time;

//    public Plan(Long id,int dayOfMonth,){}



    public Plan(String who,int year, int month, int dayOfMonth, int hour, int min, String dowhat, boolean alarm){

        this.who=who;
        this.year=year;
        this.month=month;
        this.dayOfMonth=dayOfMonth;
        this.hour=hour;
        this.min=min;
        this.dowhat=dowhat;
        this.alarm=alarm;

    }


    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int id) {
        this.year = id;
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
