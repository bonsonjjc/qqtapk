package com.bonson.qqtapk.model.data.contacts;

import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Contact;
import com.bonson.qqtapk.model.bean.Result;

import java.util.List;

import io.reactivex.Observable;

interface ContactDataSource {
    Observable<Result<List<Contact>>> contacts(String bid, int start, int pageSize);

    Observable<Result<Contact>> add(Contact contacts);

    Observable<Result<Contact>> update(Contact contacts);

    Observable<Result<Contact>> delete(Contact contacts);

    Observable<Result<Base>> add(String bid, List<Contact> list);
}
