package com.bonson.qqtapk.model.data.center;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Contact;
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

public class MessageModel implements MessageDataSource {
    private ApiServer apiServer;

    @Inject
    public MessageModel(ApiServer apiServer) {
        this.apiServer = apiServer;
    }

    @Override
    public Observable<Result<List<Message>>> message(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
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

    @Override
    public Observable<Result<List<Message>>> messageOfType(String task, String bid, int start, int pageSize) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", bid);
        map.put("start", start + "");
        map.put("end", pageSize + "");
        Object body = QQtBuilder.build(task, map);
        return apiServer.messages(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Message>> result = new Result<>();
                    if (it.isEmpty()) {
                        if (start == 0) {
                            result.setCode("-1");
                            result.setMsg("没有消息");
                        } else {
                            result.setCode("-2");
                            result.setMsg("已全部加载");
                        }
                    } else {
                        result.setCode("0");
                        result.setMsg("获取成功");
                        result.setBody(it);
                    }
                    return result;
                });
    }

    public Observable<Result<Base>> friend(String fid, String bid, String state) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", fid);
        map.put("fbid", bid);
        map.put("fstate", state);
        Object body = QQtBuilder.build("64", map);
        return apiServer.messages(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Base> result = new Result<>();
                    Base bean = it.get(0);
                    if ("0".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(bean);
                        result.setMsg("操作成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(bean.getMsg());
                    }
                    return result;
                });
    }

    public Observable<Result<Message>> mapDetail(String id) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", id);
        Object body = QQtBuilder.build("52", map);
        return apiServer.messages(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Message> result = new Result<>();
                    Message bean = it.get(0);
                    if ("0".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(bean);
                        result.setMsg("操作成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(bean.getMsg());
                    }
                    return result;
                });
    }
}
