package com.bonson.resource.utils;

import com.bonson.library.utils.security.Aes;

public final class AesUtils {

    private static byte[] key = {1, 4, 6, 8, 3, 6, 2, 6, 7, 9, 2, 3, 3, 5, 4, 6};
    private static byte[] iv = {1, 5, 5, 7, 2, 4, 6, 5, 3, 5, 4, 3, 5, 5, 5, 4};
    private static byte[] s = {1, 2, 5, 4, 2, 4, 6, 0, 2, 5, 2, 3, 4, 0, 1, 4};

    public static byte[] encode(byte[] bytes) {
        try {
            byte[] encrypted = Aes.encode(bytes, diff(s, key), diff(s, iv));
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static byte[] decode(byte[] bytes) {
        try {
            byte[] encrypted = Aes.decode(bytes, diff(s, key), diff(s, iv));
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static String encodeBase64(String str) {
        try {
            return Aes.encodeBase64(str, diff(s, key), diff(s, iv));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decodeBase64(String encode) {
        try {
            return Aes.decodeBase64(encode, diff(s, key), diff(s, iv));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static byte[] diff(byte[] v1, byte[] v2) {
        byte[] bytes = new byte[v1.length];
        for (int i = 0; i < v1.length; i++) {
            bytes[i] = (byte) (v1[i] - v2[i]);
        }
        return bytes;
    }
}
