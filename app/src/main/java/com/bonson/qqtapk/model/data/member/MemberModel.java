package com.bonson.qqtapk.model.data.member;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 18/1/4.
 */

public class MemberModel {
    private ApiServer memberServer;

    @Inject
    MemberModel(ApiServer memberServer) {
        this.memberServer = memberServer;
    }

    public Observable<Result<List<Member>>> members(String bid, final int start, int pageSize) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", bid);
        map.put("start", start + "");
        map.put("end", pageSize + "");
        Object args = QQtBuilder.build("09", map);
        return memberServer.members(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(members -> {
                    Result<List<Member>> result = new Result<>();
                    if (members.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有家庭成员");
                    } else {
                        result.setCode("0");
                        result.setBody(members);
                        result.setMsg("获取家庭成员成功");
                    }
                    return result;
                });
    }

    public Observable<Result<Member>> update(Member member) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fuid", member.getFuid());
        map.put("fmobile", member.getFmobile());
        map.put("fname", member.getFname());
        map.put("fbid", member.getFbid());
        Object args = QQtBuilder.build("36", map);
        return memberServer.base(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(members -> {
                    Result<Member> result = new Result<>();
                    Base member1 = members.get(0);
                    if ("0".equals(member1.getFresult())) {
                        result.setCode("0");
                        result.setBody(member);
                        result.setMsg("修改家庭成员成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(member1.getFresult()));
                    }
                    return result;
                });
    }

    public Observable<Result<Member>> delete(Member member) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fuid", member.getFuid());
        map.put("fbid", member.getFbid());
        Object args = QQtBuilder.build("28", map);
        return memberServer.base(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(members -> {
                    Result<Member> result = new Result<>();
                    Base member1 = members.get(0);
                    if ("0".equals(member1.getFresult())) {
                        result.setCode("0");
                        result.setBody(member);
                        result.setMsg("删除家庭成员成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(member1.getFresult()));
                    }
                    return result;
                });
    }

    public Observable<Result<Member>> add(String mobile, String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("fmobile", mobile);
        Object args = QQtBuilder.build("50", map);
        return memberServer.members(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(members -> {
                    Result<Member> result = new Result<>();
                    Member member = members.get(0);
                    if ("0".equals(member.getFresult())) {
                        result.setCode("0");
                        result.setBody(member);
                        result.setMsg("修改呼入限制成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(member.getFresult()));
                    }
                    return result;
                });
    }
}
