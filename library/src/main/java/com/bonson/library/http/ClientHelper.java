package com.bonson.library.http;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zjw on 2017/12/22.
 */

public class ClientHelper {
  private OkHttpClient client;

  public final OkHttpClient httpClient() {
    OkHttpClient client = (new OkHttpClient.Builder()).addInterceptor(new HttpLoggingInterceptor())
        .writeTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(1L, TimeUnit.MINUTES)
        .build();
    return client;
  }
}
