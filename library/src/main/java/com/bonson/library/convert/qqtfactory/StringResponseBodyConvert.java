package com.bonson.library.convert.qqtfactory;

import android.util.Base64;

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

public class StringResponseBodyConvert implements Converter<ResponseBody, String> {
    private Boolean encode;

    private StringResponseBodyConvert(Boolean encode) {
        this.encode = encode;
    }

    @Override
    public String convert(ResponseBody value) throws IOException {
        String json = "";
        if (encode) {
            json = new String(Base64.decode(value.bytes(), Base64.DEFAULT));
        } else {
            json = new String(value.bytes());
        }
        try {
            return json;
        } finally {
            value.close();
        }
    }

    public static <T> StringResponseBodyConvert create(Boolean encode) {
        return new StringResponseBodyConvert( encode);
    }
}
