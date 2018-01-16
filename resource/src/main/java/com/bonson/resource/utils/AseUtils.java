package com.bonson.resource.utils;

import com.bonson.library.utils.security.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AseUtils {

    private static byte[] key = {1, 4, 6, 8, 3, 6, 2, 6, 7, 9, 2, 3, 3, 5, 4, 6};
    private static byte[] iv = {1, 5, 5, 7, 2, 4, 6, 5, 3, 5, 4, 3, 5, 5, 5, 4};
    private static byte[] s = {1, 2, 5, 4, 2, 4, 6, 0, 2, 5, 2, 3, 4, 0, 1, 4};

//
//    public static byte[] encode(byte[] bytes) throws Exception {
//        byte[] encrypted = A;
//        return encrypted;
//    }
//
//    public static byte[] decode(byte[] bytes) throws Exception {
//        instance();
//        byte[] decrypted = instance.decCipher.doFinal(bytes);
//        return decrypted;
//    }
}
