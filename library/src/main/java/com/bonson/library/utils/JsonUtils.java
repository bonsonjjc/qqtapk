package com.bonson.library.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zjw on 2018/1/2.
 */

public class JsonUtils {
    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    public static <T> T fromJson(JsonObject jsonObject, Class<T> tClass) {
        return gson.fromJson(jsonObject, tClass);
    }

    public static <T> T fromJson(JsonElement jsonElement, Class<T> tClass) {
        return gson.fromJson(jsonElement, tClass);
    }

    public static <T> List<T> fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> List<T> fromJson(JsonArray jsonObject, Type type) {
        return gson.fromJson(jsonObject, type);
    }
//
//    public static <T> List<T> fromJson(JsonElement jsonElement, Type type) {
//        return gson.fromJson(jsonElement, type);
//    }
}
