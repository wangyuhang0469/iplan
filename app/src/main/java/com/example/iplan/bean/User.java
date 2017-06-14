package com.example.iplan.bean;


import com.example.iplan.db.NewFriend;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * @author :smile
 * @project:User
 * @date :2016-01-22-18:11
 */
public class User extends BmobUser {

    private String avatar;
    private String sex;

    public User(){}

    public User(NewFriend friend){
        setObjectId(friend.getUid());
        setUsername(friend.getName());
        setAvatar(friend.getAvatar());
    }

    public String getAvatar() {
        return avatar;
    }



    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public BmobFile PicUser() {
        return null;
    }
}
