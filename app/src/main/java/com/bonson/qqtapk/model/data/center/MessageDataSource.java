package com.bonson.qqtapk.model.data.center;

import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.bean.Result;

import java.util.List;

import io.reactivex.Observable;

interface MessageDataSource {
    Observable<Result<List<Message>>> message(String id);

    Observable<Result<List<Message>>> messageOfType(String task, String bid, int start, int pageSize);
}
