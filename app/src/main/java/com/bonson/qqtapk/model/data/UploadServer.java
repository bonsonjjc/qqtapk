package com.bonson.qqtapk.model.data;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UploadServer {
    @POST("file/upload.html")
    Observable<String> voice(@Query("timespace") String time, @Query("token") String token, @Body RequestBody body);

    @Headers({"Connection:Keep-Alive", "uploadType:pic", "User-Agent:1", "Charset:UTF-8"})
    @POST("uploadImage.html")
    Observable<String> picture(@Query("type") String token, @Body RequestBody requestBody);
}
