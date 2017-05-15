package com.example.iplan.util;

/**
 * Created by 王先生 on 2017/5/8.
 */

import org.json.JSONObject;

import java.util.Map;

import cn.bmob.newim.bean.BmobIMMessage;

public class BmobIMExtraMessage2 extends BmobIMMessage {
    public static final String KEY_METADATA = "metaData";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_SIZE = "size";
    public static final String KEY_LATITUDE = "lat";
    public static final String KEY_LONGITUDE = "lng";
    public static final String KEY_FORMAT = "format";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_ISTRANSIENT = "isTransient";
    Map<String, Object> extraMap;

    public BmobIMExtraMessage2() {
    }

    public  String getExtra() {
        return this.extraMap != null && this.extraMap.size() > 0?(new JSONObject(this.extraMap)).toString():"";
    }
    public Map<String, Object> getExtra2(){
        return this.extraMap;
    }
    protected Map<String, Object> getExtraMap() {
        return this.extraMap;
    }

    public void setExtraMap(Map<String, Object> var1) {
        this.extraMap = var1;
    }
}
