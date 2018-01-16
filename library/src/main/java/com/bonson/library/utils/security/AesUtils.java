package com.bonson.library.utils.security;

/**
 * Created by zjw on 2017/12/26.
 */

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES instance with one key and one iv for preformance.
 * mode: AES/CBC/PKCS5Padding
 * text input encoding: utf-8
 * text output encoding: base64
 */
public class AesUtils {

  private AesUtils(byte[] key, byte[] iv) throws Exception {
    this.encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    this.decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
    IvParameterSpec ivc = new IvParameterSpec(iv);
    this.encCipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivc);
    this.decCipher.init(Cipher.DECRYPT_MODE, skeySpec, ivc);
  }

  private Cipher encCipher = null;
  private Cipher decCipher = null;

  public byte[] encBytes(byte[] srcBytes) throws Exception {
    byte[] encrypted = encCipher.doFinal(srcBytes);
    return encrypted;
  }

  public byte[] decBytes(byte[] srcBytes) throws Exception {
    byte[] decrypted = decCipher.doFinal(srcBytes);
    return decrypted;
  }

  public String encText(String srcStr) throws Exception {
    byte[] srcBytes = srcStr.getBytes("utf-8");
    byte[] encrypted = encBytes(srcBytes);
    return Base64.encode(encrypted);
  }

  public String decText(String srcStr) throws Exception {
    byte[] srcBytes = Base64.decode(srcStr);
    byte[] decrypted = decBytes(srcBytes);
    return new String(decrypted, "utf-8");
  }

  public static AesUtils create(byte[] key, byte[] iv) {
    AesUtils sub = null;
    try {
      sub = new AesUtils(key, iv);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sub;
  }

  public static AesUtils create() {
    AesUtils sub = null;
    try {
      sub = new AesUtils(diff(s, key), diff(s, iv));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sub;
  }

  private static byte[] diff(byte[] v1, byte[] v2) {
    byte[] bytes = new byte[v1.length];
    for (int i = 0; i < v1.length; i++) {
      bytes[i] = (byte) (v1[i] - v2[i]);
    }
    return bytes;
  }

  private static byte[] key = { 1, 4, 6, 8, 3, 6, 2, 6, 7, 9, 2, 3, 3, 5, 4, 6 };
  private static byte[] iv = { 1, 5, 5, 7, 2, 4, 6, 5, 3, 5, 4, 3, 5, 5, 5, 4 };
  private static byte[] s = { 1, 2, 5, 4, 2, 4, 6, 0, 2, 5, 2, 3, 4, 0, 1, 4 };
}
