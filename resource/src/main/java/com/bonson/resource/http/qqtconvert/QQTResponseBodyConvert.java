package com.bonson.resource.http.qqtconvert;

import android.util.Base64;
import android.util.Log;

import com.bonson.library.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zjw on 2018/1/2.
 */

public final class QQTResponseBodyConvert<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private TypeToken<T> typeToken;
    private Boolean encode;

    private QQTResponseBodyConvert(Gson gson, TypeToken<T> typeToken, Boolean encode) {
        this.gson = gson;
        this.typeToken = typeToken;
        this.encode = encode;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = "";
        if (encode) {
            json = new String(Base64.decode(value.bytes(), Base64.DEFAULT));
        } else {
            json = new String(value.bytes());
        }
        Log.e("response", json);
        try {
            JsonObject jsonObject = JsonUtils.fromJson(json, JsonObject.class);
            JsonArray body = jsonObject.getAsJsonArray("body");
            if (body == null) {
                return gson.fromJson(jsonObject, typeToken.getType());
            }
            return gson.fromJson(body, typeToken.getType());
        } finally {
            value.close();
        }
    }

    public static <T> QQTResponseBodyConvert create(Gson gson, TypeToken<T> typeToken, Boolean encode) {
        return new QQTResponseBodyConvert<T>(gson, typeToken, encode);
    }
}
