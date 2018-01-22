package com.bonson.qqtapk.model.data.mode;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Mode;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 18/1/4.
 */

public class ModeModel implements ModeDataSource {
    private ApiServer modeServer;

    @Inject
    public ModeModel(ApiServer modeServer) {
        this.modeServer = modeServer;
    }

    @Override
    public Observable<Result<Mode>> model(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        Object args = QQtBuilder.build("06", map);
        return modeServer.mode(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(modes -> {
                    Result<Mode> result = new Result<>();
                    Mode mode = modes.get(0);
//                    if ("0".equals(mode.getFresult())) {
                    result.setCode("0");
                    result.setBody(mode);
                    result.setMsg("获取定位模式成功");
//                    } else {
//                        result.setCode("-1");
//                        result.setMsg(ErrorCode.message(mode.getFresult()));
//                    }
                    return result;
                });
    }

    @Override
    public Observable<Result<Mode>> update(Mode mode) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", mode.getBid());
        map.put("fsavepower", mode.getFsavepower());
        map.put("flmode", mode.getFlmode());
        map.put("floctype", mode.getFloctype());
        map.put("flocation", mode.getFlocation());
        Object args = QQtBuilder.build("27", map);
        return modeServer.mode(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(modes -> {
                    Result<Mode> result = new Result<>();
                    Mode mode1 = modes.get(0);
                    if ("0".equals(mode1.getFresult())) {
                        result.setCode("0");
                        result.setBody(mode1);
                        result.setMsg("设置定位模式成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(mode1.getFresult()));
                    }
                    return result;
                });
    }


}
