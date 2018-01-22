package com.bonson.qqtapk.model.data.member;

import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.Result;

import java.util.List;

import io.reactivex.Observable;

interface MemberDataSource {
    Observable<Result<List<Member>>> members(String bid, final int start, int pageSize);

    Observable<Result<Member>> update(Member member);

    Observable<Result<Member>> delete(Member member);

    Observable<Result<Member>> add(String mobile, String bid);
}
