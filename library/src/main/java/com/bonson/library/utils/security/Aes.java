package com.bonson.library.utils.security;

/**
 * Created by zjw on 2017/12/26.
 */

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES static function for different key and iv
 * mode: AES/CBC/PKCS5Padding
 * text input encoding: utf-8
 * text output encoding: base64
 */
public class Aes {

    private Aes() {
    }

    public static byte[] encode(byte[] bytes, byte[] key, byte[] newIv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(newIv);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encodeBytes = cipher.doFinal(bytes);
        return encodeBytes;
    }

    public static String encodeBase64(String bytes, byte[] key, byte[] newIv) throws Exception {
        byte[] encodeBytes = encode(bytes.getBytes("utf-8"), key, newIv);
        return Base64.encode(encodeBytes);
    }

    public static byte[] decode(byte[] bytes, byte[] key, byte[] newIv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(newIv);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(bytes);
        return encrypted;
    }

    public static String decodeBase64(String encodeStr, byte[] key, byte[] newIv) throws Exception {
        byte[] encodeBytes = Base64.decode(encodeStr);
        byte[] decodeBytes = decode(encodeBytes, key, newIv);
        return new String(decodeBytes, "utf-8");
    }

}
