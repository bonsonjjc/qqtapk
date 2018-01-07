package com.bonson.qqtapk.model.data.voice;

import com.bonson.library.convert.qqtfactory.Encode;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.bean.Voice;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public interface VoiceServer {

    @POST("data.html")
    @Encode(encoder = true, decoder = true)
    Observable<List<Voice>> voices(@Body Object requestBody);

    @POST("data.html")
    @Encode(encoder = true, decoder = true)
    Observable<String> upload(@Body Object requestBody);

    @POST("data.html")
    @Encode(encoder = true, decoder = true)
    Observable<List<Voice>> upVoice(@Body Object requestBody);

}
