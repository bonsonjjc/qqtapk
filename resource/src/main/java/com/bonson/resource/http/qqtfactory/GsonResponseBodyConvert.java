package com.bonson.resource.http.qqtfactory;

import android.util.Base64;
import android.util.Log;

import com.bonson.library.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zjw on 2018/1/2.
 */

public class GsonResponseBodyConvert<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private TypeToken<T> typeToken;
    private Boolean encode;

    private GsonResponseBodyConvert(Gson gson, TypeToken<T> typeToken, Boolean encode) {
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
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            if (jsonObject.has("body")) {
                JsonElement body = jsonObject.get("body");
                if (body.isJsonArray()) {
                    return gson.fromJson(body.getAsJsonArray(), typeToken.getType());
                }
            }
            return gson.fromJson(jsonObject, typeToken.getType());
        } finally {
            value.close();
        }
    }

    public static <T> GsonResponseBodyConvert create(Gson gson, TypeToken<T> typeToken,Boolean encode) {
        return new GsonResponseBodyConvert<T>(gson, typeToken, encode);
    }
}
