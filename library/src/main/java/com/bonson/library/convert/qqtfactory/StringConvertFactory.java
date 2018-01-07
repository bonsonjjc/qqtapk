package com.bonson.library.convert.qqtfactory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by zjw on 2018/1/2.
 */

public class StringConvertFactory extends Converter.Factory {
  private Gson gson;

  private StringConvertFactory(Gson gson) {
    this.gson = gson;
  }

  @Nullable @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
      Retrofit retrofit) {
    Boolean decoder = false;
    for (Annotation annotation : annotations) {
      if (annotation instanceof Encode) {
        decoder = ((Encode) annotation).encoder();
      }
    }
    return StringResponseBodyConvert.create(decoder);
  }

  @Nullable @Override public Converter<?, RequestBody> requestBodyConverter(Type type,
      Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
    Boolean encoder = false;
    for (Annotation methodAnnotation : methodAnnotations) {
      if (methodAnnotation instanceof Encode) {
        encoder = ((Encode) methodAnnotation).decoder();
      }
    }
    TypeAdapter adapter = gson.getAdapter(TypeToken.get(type));
    return GsonRequestBodyConvert.create(gson, adapter, encoder);
  }

  public static StringConvertFactory create(Gson gson) {
    return new StringConvertFactory(gson);
  }

  public static StringConvertFactory create() {
    return create(new Gson());
  }
}
