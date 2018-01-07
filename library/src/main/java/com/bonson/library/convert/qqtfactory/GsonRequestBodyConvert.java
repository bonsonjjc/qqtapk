package com.bonson.library.convert.qqtfactory;

import android.util.Base64;
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

public class GsonRequestBodyConvert<T> implements Converter<T, RequestBody> {
  private Gson gson;
  private TypeAdapter<T> adapter;
  private Boolean encode;

  private MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
  private Charset UTF_8 = Charset.forName("UTF-8");

  GsonRequestBodyConvert(Gson gson, TypeAdapter<T> adapter, Boolean encode) {
    this.gson = gson;
    this.adapter = adapter;
    this.encode = encode;
  }

  @Override public RequestBody convert(T value) throws IOException {
    Buffer buffer = new Buffer();
    OutputStreamWriter writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
    JsonWriter jsonWriter = gson.newJsonWriter(writer);
    adapter.write(jsonWriter, value);
    jsonWriter.close();
    byte[] encodeArray = buffer.readByteArray();
    if (encode) {
      encodeArray = Base64.encode(encodeArray, Base64.DEFAULT);
    }
    return RequestBody.create(MEDIA_TYPE, encodeArray);
  }

  public static <T> GsonRequestBodyConvert<T> create(Gson gson, TypeAdapter<T> adapter, Boolean encode) {
    return new GsonRequestBodyConvert<T>(gson, adapter, encode);
  }
}
