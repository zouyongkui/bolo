package com.zyk.bolo.utils;

import com.alibaba.fastjson.JSONObject;
import com.zyk.bolo.Constant;

public class ResultUtils {
    public static JSONObject suc() {
        JSONObject object = new JSONObject();
        object.put("code", Constant.CODE_SUC);
        object.put("msg", Constant.MSG_SUC);
        object.put("data", "");
        return object;
    }

    public static JSONObject suc(String msg) {
        JSONObject object = new JSONObject();
        object.put("code", Constant.CODE_SUC);
        object.put("msg", msg);
        object.put("data", "");
        return object;
    }

    public static JSONObject suc(String msg, Object data) {
        JSONObject object = new JSONObject();
        object.put("code", Constant.CODE_SUC);
        object.put("msg", msg);
        object.put("data", data);
        return object;
    }


    public static JSONObject fail() {
        JSONObject object = new JSONObject();
        object.put("code", Constant.CODE_FAIL);
        object.put("msg", Constant.MSG_FAIL);
        object.put("data", "");
        return object;
    }

    public static JSONObject fail(String msg) {
        JSONObject object = new JSONObject();
        object.put("code", Constant.CODE_FAIL);
        object.put("msg", msg);
        object.put("data", "");
        return object;
    }


    public static JSONObject fail(String msg, Object data) {
        JSONObject object = new JSONObject();
        object.put("code", Constant.CODE_FAIL);
        object.put("msg", msg);
        object.put("data", data);

        return object;
    }
}
