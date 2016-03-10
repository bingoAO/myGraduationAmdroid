package com.example.companyuo;

import android.util.Log;

/**
 * Created by sheshihao385 on 16/1/15.
 *  有待拓展其它帮助性的日志信息
 */
public class LogUtils{

    private final static String Tag = "sheshihao";

    private static boolean allowLog = true;//控制是否可以输出日志

    public static void d(String message){
        if(allowLog) {
            Log.d(Tag, message);
        }
    }

    public static void d(int message){
        if(allowLog) {
            Log.d(Tag, "int:"+message);
        }
    }

    public static void i(String message){
        if(allowLog) {
            Log.i(Tag, message);
        }
    }

    public static void e(String message){
        if(allowLog) {
            Log.e(Tag, message);
        }
    }


}
