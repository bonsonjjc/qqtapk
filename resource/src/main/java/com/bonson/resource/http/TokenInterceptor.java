package com.bonson.resource.http;

import com.bonson.resource.utils.EncodeUtils;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
  @Override
  public Response intercept(Chain chain) throws IOException {
    String[] head = { "Encode","true", "token",token() };
    Request request = chain.request().newBuilder().headers(Headers.of(head)).build();
    return chain.proceed(request);
  }

  private String token() {
    String token = System.currentTimeMillis() + "," + UUID.randomUUID().toString();
    try {
      token = EncodeUtils.encode(token.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return token;
  }
}
