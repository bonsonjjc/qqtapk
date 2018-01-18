package com.bonson.resource.http.qqtconvert;

import android.util.Log;

import com.bonson.library.utils.JsonUtils;
import com.bonson.library.utils.LogUtils;
import com.bonson.resource.utils.EncodeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zjw on 2018/1/2.
 */
final class QQTResponseBodyConvert<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private TypeToken<T> typeToken;

    QQTResponseBodyConvert(Gson gson, TypeToken<T> typeToken) {
        this.gson = gson;
        this.typeToken = typeToken;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String decode = EncodeUtils.decode(value.string());
            JsonObject jsonObject = gson.fromJson(decode, JsonObject.class);
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
}
