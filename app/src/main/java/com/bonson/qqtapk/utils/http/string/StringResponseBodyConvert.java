package com.bonson.qqtapk.utils.http.string;

import com.bonson.resource.utils.EncodeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zjw on 2018/1/2.
 */
final class StringResponseBodyConvert implements Converter<ResponseBody, String> {
    private boolean decode;

    StringResponseBodyConvert(boolean decode) {
        this.decode = decode;
    }

    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            if (decode) {
                response = EncodeUtils.decode(response);
            }
            return response;
        } finally {
            value.close();
        }
    }
}
