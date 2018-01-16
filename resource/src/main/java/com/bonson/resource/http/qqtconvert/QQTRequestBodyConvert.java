package com.bonson.resource.http.qqtconvert;

import android.util.Base64;

import com.bonson.resource.utils.EnCodeUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zjw on 2018/1/2.
 */

final class QQTRequestBodyConvert<T> implements Converter<T, RequestBody> {
    private MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private Charset UTF_8 = Charset.forName("UTF-8");

    private Gson gson;
    private TypeAdapter<T> adapter;

    private QQTRequestBodyConvert(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        OutputStreamWriter writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        byte[] encodeArray = buffer.readByteArray();
        encodeArray = EnCodeUtils.encode(encodeArray);
        return RequestBody.create(MEDIA_TYPE, encodeArray);
    }
}
