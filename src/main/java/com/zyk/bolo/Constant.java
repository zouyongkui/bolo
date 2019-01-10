package com.zyk.bolo;

public class Constant {

    //appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String WX_APP_ID = "wx519e609fbe7dee42";
    public static final String WX_MCH_ID = "暂时没有申请下来！";
    public static final String WX_APP_SECRET = "78e435d3b0d12ee5791089fd0aedd067";
    public static final String WX_GRANT_TYPE = "authorization_code";


    public static final int CODE_SUC = 0;
    public static final int CODE_FAIL = -1;
    public static final int CODE_LOGIN_REQUIRE = -2;


    public static final String MSG_SUC = "suc";
    public static final String MSG_FAIL = "fail";
    public static final String MSG_INVALID_ID = "请检查id合法性！";

}
