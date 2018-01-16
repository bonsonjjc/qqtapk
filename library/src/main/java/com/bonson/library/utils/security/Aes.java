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

  public static byte[] encode(byte[] srcBytes, byte[] key, byte[] newIv) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
    IvParameterSpec iv = new IvParameterSpec(newIv);
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
    byte[] encrypted = cipher.doFinal(srcBytes);
    return encrypted;
  }

  public static String encode(String sSrc, byte[] key, byte[] newIv) throws Exception {
    byte[] srcBytes = sSrc.getBytes("utf-8");
    byte[] encrypted = encode(srcBytes, key, newIv);
    return Base64.encode(encrypted);
  }

  public static byte[] decode(byte[] srcBytes, byte[] key, byte[] newIv) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
    IvParameterSpec iv = new IvParameterSpec(newIv);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    byte[] encrypted = cipher.doFinal(srcBytes);
    return encrypted;
  }

  public static String decode(String sSrc, byte[] key, byte[] newIv) throws Exception {
    byte[] srcBytes = Base64.decode(sSrc);
    byte[] decrypted = decode(srcBytes, key, newIv);
    return new String(decrypted, "utf-8");
  }

}
