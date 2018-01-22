package com.bonson.qqtapk.model.data.voice;

import android.text.TextUtils;

import com.bonson.library.utils.DateUtils;
import com.bonson.library.utils.security.Md5Utils;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Voice;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.UploadServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class VoiceModel implements VoiceDataSource {
    private ApiServer voiceServer;

    private UploadServer uploadServer;

    String time = "";

    @Inject
    public VoiceModel(ApiServer voiceServer, UploadServer uploadServer) {
        this.voiceServer = voiceServer;
        this.uploadServer = uploadServer;
    }

    @Override
    public Observable<Result<List<Voice>>> voices(String fuser, String bid, int start, int pageSize) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fuser", fuser);
        map.put("ftuser", bid);
        map.put("ftype", "1");
        map.put("fctime", time);
        map.put("start", start + "");
        map.put("end", pageSize + "");
        Object obj = QQtBuilder.build("57", map);
        return voiceServer.voices(obj)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Voice>> result = new Result<>();
                    if (it.isEmpty()) {
                        if (start == 0) {
                            result.setMsg("没有语音数据");
                            result.setCode("-1");
                        } else {
                            result.setMsg("已全部加载");
                            result.setCode("-2");
                        }
                    } else {
                        result.setMsg("加载成功");
                        result.setCode("0");
                        result.setBody(it);
                        time = it.get(it.size() - 1).getFctime();
                    }
                    return result;
                });
    }

    @Override
    public Observable<Result<String>> upload(File file) {
        String time = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        String token = Md5Utils.toMD5(time + Md5Utils.toMD5("BonsonJva"));
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("file2", "1.amr", requestBody)
                .build();
        return uploadServer.voice(time, token, multipartBody)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    if (TextUtils.isEmpty(it)) {
                        result.setCode("-1");
                        result.setMsg("上传失败");
                    } else {
                        result.setCode("0");
                        result.setMsg("上传成功");
                        result.setBody(it);
                    }
                    return result;
                });

    }

    @Override
    public Observable<Result<String>> addVoice(Voice voice) {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("ftype", voice.getFtype());
        body.put("fuser", voice.getFuser());
        body.put("ftuser", voice.getFtuser());
        body.put("fpath", voice.getFpath());
        body.put("fcontent", voice.getFcontent());
        body.put("ftime", voice.getFtime());
        Object obj = QQtBuilder.build("55", body);
        return voiceServer.voices(obj)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    Voice v = it.get(0);
                    if ("0".equals(v.getFresult())) {
                        result.setMsg("发送成功");
                        result.setCode("0");
                        result.setBody("2");
                    } else {
                        result.setMsg("发送失败");
                        result.setCode("-1");
                        result.setBody("1");
                    }
                    return result;
                });
    }
}
