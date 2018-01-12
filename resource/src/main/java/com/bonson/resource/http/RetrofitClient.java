package com.bonson.resource.http;

import com.bonson.resource.http.qqtfactory.GsonConvertFactory;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zjw on 2017/12/22.
 */

public class RetrofitClient {
  private static OkHttpClient client;

  public final OkHttpClient httpClient() {
    OkHttpClient client = (new OkHttpClient.Builder()).addInterceptor(new HttpLoggingInterceptor())
        .writeTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(1L, TimeUnit.MINUTES)
        .build();
    return client;
  }

  public final Retrofit retrofit(String baseUrl) {
    Retrofit build = (new retrofit2.Retrofit.Builder()).baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConvertFactory.create(new Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return build;
  }

  public final Retrofit retrofit(String baseUrl, Converter.Factory convertFactory) {
    Retrofit build = (new retrofit2.Retrofit.Builder()).baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(convertFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return build;
  }

  public final <T> T model(Retrofit retrofit, Class<T> server) {
    return retrofit.create(server);
  }
}
