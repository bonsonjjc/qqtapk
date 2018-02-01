package com.bonson.qqtapk.utils.http.qqtconvert;

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

public final class QQTConverterFactory extends Converter.Factory {
    final private Gson gson;

    private QQTConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new QQTRequestBodyConvert<>(gson, adapter);
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new QQTResponseBodyConvert<>(gson, TypeToken.get(type));
    }

    public static QQTConverterFactory create(Gson gson) {
        return new QQTConverterFactory(gson);
    }

    public static QQTConverterFactory create() {
        return create(new Gson());
    }
}
