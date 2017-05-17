package com.example.iplan.bean;

import cn.bmob.newim.bean.BmobIMExtraMessage;

/**
 * Created by 王先生 on 2017/5/17.
 */

public class ScheduleMessage extends BmobIMExtraMessage {


    public ScheduleMessage(){}
    @Override
    public String getMsgType() {
        return "Schedule";
    }

    @Override
    public boolean isTransient() {
        //设置为true,表明为暂态消息，那么这条消息并不会保存到本地db中，SDK只负责发送出去
        //设置为false,则会保存到指定会话的数据库中
        return true;
    }

}