package com.bonson.qqtapk.model.data.contacts;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Contacts;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/5.
 */

public class ContactsModel {
    private ContactsServer contactsServer;

    @Inject
    ContactsModel(ContactsServer contactsServer) {
        this.contactsServer = contactsServer;
    }

    public Observable<Result<List<Contacts>>> contacts(String bid, int start, int pageSize) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("start", start + "");
        map.put("end", pageSize + "");
        Object body = QQtBuilder.build("07", map);
        return contactsServer.contacts(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Contacts>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        if (start == 0) {
                            result.setMsg("没有联系人");
                        } else {
                            result.setMsg("已全部加载");
                        }
                    } else {
                        result.setBody(it);
                        result.setCode("0");
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    private Observable<Result<Contacts>> set(String type, Contacts contacts) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", contacts.getFid());
        map.put("fbid", contacts.getBid());
        map.put("fmobile", contacts.getFmobile());
        map.put("fname", contacts.getFname());
        map.put("foptype", type);
        Object args = QQtBuilder.build("16", map);
        return contactsServer.opelear(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(contactsList -> {
                    Result<Contacts> result = new Result<>();
                    Contacts inLimit = contactsList.get(0);
                    if ("0".equals(inLimit.getFresult())) {
                        result.setCode("0");
                        result.setBody(inLimit);
                        result.setMsg("联系人成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(inLimit.getFresult()));
                    }
                    return null;
                });
    }

    public Observable<Result<Contacts>> add(Contacts contacts) {
        return set("1", contacts);
    }


    public Observable<Result<Contacts>> update(Contacts contacts) {
        return set("3", contacts);
    }

    public Observable<Result<Contacts>> delete(Contacts contacts) {
        return set("2", contacts);
    }

    public Observable<Result<Contacts>> add(String bid, List<Contacts> contacts) {
        StringBuilder builder = new StringBuilder();
        for (Contacts contact : contacts) {
            String fname = contact.getFname();
            fname = fname.length() > 4 ? fname.substring(0, 4) : fname;
            String fmobile = contact.getFmobile();
            fmobile = fmobile.replaceAll("(\\+86)?-?", "");
            builder.append(fmobile);
            if (contacts.indexOf(contact) != contacts.size() - 1) {
                builder.append("|");
            }
        }
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("contacts", builder.toString());
        Object args = QQtBuilder.build("17", map);
        return contactsServer.imports(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(contactsList -> {
                    Result<Contacts> result = new Result<>();
                    Contacts inLimit = contactsList.get(0);
                    if ("0".equals(inLimit.getFresult())) {
                        result.setCode("0");
                        result.setBody(inLimit);
                        result.setMsg("导入联系人成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(inLimit.getFresult()));
                    }
                    return null;
                });
    }


}
