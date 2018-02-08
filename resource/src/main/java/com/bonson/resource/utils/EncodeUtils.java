package com.bonson.resource.utils;

import com.bonson.library.utils.LogUtils;
import com.bonson.library.utils.security.Base64;
import com.google.gson.Gson;

public class EncodeUtils {
    public static byte[] encodeAes(byte[] bytes) {
        return AesUtils.encode(bytes);
    }

    public static byte[] decodeAes(byte[] bytes) {
        return AesUtils.decode(bytes);
    }

    public static String encodeAesToString(String str) {
        return new String(AesUtils.encode(str.getBytes()));
    }

    public static String decodeAesToString(String str) {
        return new String(AesUtils.decode(str.getBytes()));
    }


    public static byte[] encodeBase64(byte[] bytes) {
        return Base64.encode(bytes).getBytes();
    }

    public static byte[] decodeBase64(byte[] bytes) {
        byte[] decode = Base64.decode(new String(bytes));
        return decode;
    }

    public static String encodeBase64ToString(String str) {
        String encode = Base64.encode(str.getBytes());
        return encode;
    }

    public static String encodeBase64ToString(byte[] bytes) {
        String encode = Base64.encode(bytes);
        return encode;
    }

    public static String decodeBase64ToString(String str) {
        byte[] decode = Base64.decode(str);
        return new String(decode);
    }

    public static String decodeBase64ToString(byte[] bytes) {
        return Base64.decodeString(new String(bytes));
    }

    public static String encode(String str) {
        LogUtils.e("加密前:" + str);
        return AesUtils.encodeBase64(str);
    }


    public static Gson gson = new Gson();

    public static String encode(Object object) {
        return encode(gson.toJson(object));
    }

    public static String decode(String encode) {
        String decode = AesUtils.decodeBase64(encode);
        LogUtils.e("解码:" + decode);
        return decode;
    }

}
