package com.bonson.qqtapk.view.ui.setting.about;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AboutModel {
    private FApiServer fApiServer;

    @Inject
    public AboutModel(FApiServer fApiServer) {
        this.fApiServer = fApiServer;
    }

    public Observable<Result<About>> about(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA022");
        map.put("fmobile", mobile);
        String json = EncodeUtils.encode(map);
        return fApiServer.about(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(routes -> {
                    Result<About> result = new Result<>();
                    About about = routes.get(0);
                    if (about.getFresult().equals("100")) {
                        result.setMsg("获取成功");
                        result.setCode("0");
                        result.setBody(about);
                    } else {
                        result.setCode("-1");
                        result.setMsg(about.getFmsg());
                    }
                    return result;
                });
    }
}
