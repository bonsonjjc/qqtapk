package com.bonson.qqtapk.app;

import android.os.Environment;

public class Const {
    public static final String PREFERENCES_FILE = "preferences";

    public static final String NOTIFY_KEY = "notify";
    public static final String VOICE_KEY = "voice";
    public static final String VIBRATE_KEY = "vibrate";

    public static final String DATA_PATH = Environment.getExternalStorageDirectory() + "/ydqqt/";
    public static final String PATH = "http://wap.bfsafe.com:7070";
    public static final String ICON_PATH = "http://wap.bfsafe.com:7070/file/";
    public static final String API_PATH = PATH + "/api/";
    public static final String QQT_PATH = "http://qqt.bfsafe.com/";
    public static final String VOICE_PATH = "http://amr.bfsafe.com/";


    public static final String IMEI_VALIDATE = "^\\d{15,18}$";
    public static String CHANGE_IMEI = "com.bonson.action.change";
}
