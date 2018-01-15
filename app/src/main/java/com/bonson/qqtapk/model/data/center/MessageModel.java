package com.bonson.qqtapk.model.data.center;

import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MessageModel {
    private ApiServer apiServer;

    @Inject
    public MessageModel(ApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<List<Message>>> message() {
        Map<String, String> map = new LinkedHashMap<>();
        Object body = QQtBuilder.build("11", map);
        return apiServer.messages(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Message>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有消息");
                    } else {
                        result.setCode("0");
                        result.setMsg("获取成功");
                        result.setBody(it);
                    }
                    return result;
                });
    }
}
