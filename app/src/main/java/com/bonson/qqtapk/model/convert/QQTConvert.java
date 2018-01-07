package com.bonson.qqtapk.model.convert;

import com.bonson.library.utils.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.reactivex.functions.Function;

/**
 * Created by zjw on 2018/1/2.
 */
public final class QQTConvert implements Function<String, Object> {
    private final Class cls;

    public QQTConvert(Class cls) {
        this.cls = cls;
    }

    public Object apply(String json) {
        JsonObject jsonObject = JsonUtils.fromJson(json, JsonObject.class);
        JsonArray body = jsonObject.getAsJsonArray("body");
        JsonElement jsonElement = body.get(0);
        return JsonUtils.fromJson(jsonElement, cls);
    }

}

