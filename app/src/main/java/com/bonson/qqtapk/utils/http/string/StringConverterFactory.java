package com.bonson.qqtapk.utils.http.string;

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

public final class StringConverterFactory extends Converter.Factory {
    private boolean encode;
    private Gson gson;

    private StringConverterFactory(boolean encode, Gson gson) {
        this.encode = encode;
        this.gson = gson;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new StringRequestBodyConvert<>(encode, gson, adapter);
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new StringResponseBodyConvert(encode);
    }

    public static StringConverterFactory create(Gson gson, boolean encode) {
        return new StringConverterFactory(encode, gson);
    }

    public static StringConverterFactory create() {
        return create(new Gson(), true);
    }
}
