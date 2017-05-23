package com.example.iplan.planclass;

import java.util.List;

/**
 * Created by 王先生 on 2017/5/18.
 */

public class Schedule {
    private int dayOfMonth;
    private List<Plan> arrPlan;

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public List<Plan> getArrPlan() {
        return arrPlan;
    }

    public void setArrPlan(List<Plan> arrPlan) {
        this.arrPlan = arrPlan;
    }
}

