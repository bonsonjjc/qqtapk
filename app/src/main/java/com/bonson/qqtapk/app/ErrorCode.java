package com.bonson.qqtapk.app;

import com.bonson.qqtapk.R;

public class ErrorCode {
//	/** 没电关机 */
//	public final static String AUTO_SHUTDOWN = "0;
//	/** 手机关机 */
//	public final static String HAND_SHUTDOWN = "1;
    /**
     * 用户名或密码错误
     */
    public final static String LOGIN_ERROR = "-1";

    /**
     * 系统繁忙
     */
    public final static String SYS_ERROR = "-2";

    /**
     * 验证码错误
     */
    public final static String CODE_ERROR = "-3";

    /**
     * 验证码已失效
     */
    public final static String CODE_LONG_TIME = "-4";

    /**
     * 验证码发送次数超过限制
     */
    public final static String CODE_MAX_COUNT = "-5";

    /**
     * 旧密码错误
     */
    public final static String OLD_PWD_ERROR = "-6";

    /**
     * 数据异常，请重试
     */
    public final static String DATA_ERROR = "-7";

    /**
     * 已经绑定过了，请不要重复扫描
     */
    public final static String NO_REPET_ADD = "-8";

    /**
     * 手机号码已存在
     */
    public final static String MOBILE_EXSIT = "-9";

    /**
     * 用户已被禁用
     */
    public final static String CANT_LOGIN = "-10";

    /**
     * 手机号码尚未注册
     */
    public final static String MOBILE_NOTEXSIT = "-11";

    /**
     * 定位失败，请稍后再试
     */
    public final static String LOCATE_FAIL = "-12";

    /**
     * 通讯录设置最多为50个
     */
    public final static String CONTACT_TOO_MORE = "-13";

    /**
     * 终端未同步，请保持终端通讯正常后再试！
     */
    public final static String NO_SYN = "-14";

    /**
     * 终端未同步，请保持终端通讯正常后再试！
     */
    public final static String NOT_ADMIN_OPERATE = "-15";

    /**
     * 您没有绑定权限，请联系终端的管理员邀请您为家庭成员
     */
    public final static String NO_RIGHT_TO_BIND = "-17";

    /**
     * 操作失败，家庭成员人数已达到上限
     */
    public final static String BEYOND_MAX_FAMILY_MEMBER = "-18";

    /**
     * 该设备已被其他用户绑定
     */
    public final static String ALREADY_BIND = "-19";

    /**
     * 超过呼入限制联系人上限人数
     */
    public final static String BEYOND_MAX_CALL_IN_LIMIT = "-20";

    /**
     * 串号不存在
     */
    public final static String IMEI_NOT_EXIST = "-21";

    /**
     * 宝贝手机已被绑定
     */
    public final static String BABY_PHONE_ALREADY_BINDING = "-22";

    /**
     * 内置定义异常信息提示
     *
     * @param errno
     * @return
     */
    public static String message(String errno) {
        switch (errno) {
            case LOGIN_ERROR:
                return App.context.getString(R.string.result_error_1);
            case SYS_ERROR:
                return App.context.getString(R.string.result_error_2);
            case CODE_ERROR:
                return App.context.getString(R.string.result_error_3);
            case CODE_LONG_TIME:
                return App.context.getString(R.string.result_error_4);
            case CODE_MAX_COUNT:
                return App.context.getString(R.string.result_error_5);
            case OLD_PWD_ERROR:
                return App.context.getString(R.string.result_error_6);
            case DATA_ERROR:
                return App.context.getString(R.string.result_error_7);
            case NO_REPET_ADD:
                return App.context.getString(R.string.result_error_8);
            case MOBILE_EXSIT:
                return App.context.getString(R.string.result_error_9);
            case CANT_LOGIN:
                return App.context.getString(R.string.result_error_10);
            case MOBILE_NOTEXSIT:
                return App.context.getString(R.string.result_error_11);
            case LOCATE_FAIL:
                return App.context.getString(R.string.result_error_12);
            case CONTACT_TOO_MORE:
                return App.context.getString(R.string.result_error_13);
            case NO_SYN:
                return App.context.getString(R.string.result_error_14);
            case NOT_ADMIN_OPERATE:
                return App.context.getString(R.string.result_error_15);
            case NO_RIGHT_TO_BIND:
                return App.context.getString(R.string.result_error_16);
            case ALREADY_BIND:
                return App.context.getString(R.string.result_error_17);
            case BEYOND_MAX_CALL_IN_LIMIT:
                return App.context.getString(R.string.result_error_18);
            case BEYOND_MAX_FAMILY_MEMBER:
                return App.context.getString(R.string.result_error_19);
            case IMEI_NOT_EXIST:
                return App.context.getString(R.string.result_error_20);
            default:
                return App.context.getString(R.string.result_error_21);
        }
    }
}
