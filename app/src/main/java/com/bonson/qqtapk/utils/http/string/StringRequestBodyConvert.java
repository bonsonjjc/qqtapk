package com.bonson.qqtapk.utils.http.string;

import com.bonson.resource.utils.EncodeUtils;
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

/**
 * Created by zjw on 2018/1/2.
 */

final class StringRequestBodyConvert<T> implements Converter<T, RequestBody> {
    private MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private Charset UTF_8 = Charset.forName("UTF-8");

    private boolean encode;

    private Gson gson;
    private TypeAdapter<T> adapter;

    StringRequestBodyConvert(boolean encode, Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.encode = encode;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        OutputStreamWriter writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        String content = buffer.readString(Charset.forName("utf-8"));
        if (!encode) {
            return RequestBody.create(MEDIA_TYPE, content);
        }
        String encodeArray = EncodeUtils.encode(content);
        return RequestBody.create(MEDIA_TYPE, encodeArray);
    }
}
