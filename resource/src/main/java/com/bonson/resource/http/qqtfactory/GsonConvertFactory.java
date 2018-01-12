package com.bonson.resource.http.qqtfactory;

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

public class GsonConvertFactory extends Converter.Factory {
  private Gson gson;

  private GsonConvertFactory(Gson gson) {
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
    return GsonResponseBodyConvert.create(gson, TypeToken.get(type), decoder);
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

  public static GsonConvertFactory create(Gson gson) {
    return new GsonConvertFactory(gson);
  }

  public static GsonConvertFactory create() {
    return create(new Gson());
  }
}
