package com.bonson.qqtapk.model.data.contacts;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Contact;
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

    public Observable<Result<List<Contact>>> contacts(String bid, int start, int pageSize) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", bid);
        map.put("start", start + "");
        map.put("end", pageSize + "");
        Object body = QQtBuilder.build("07", map);
        return contactsServer.contacts(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Contact>> result = new Result<>();
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

    private Observable<Result<Contact>> set(String type, Contact contact) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", contact.getFid());
        map.put("fbid", contact.getBid());
        map.put("fmobile", contact.getFmobile());
        map.put("fname", contact.getFname());
        map.put("foptype", type);
        Object args = QQtBuilder.build("16", map);
        return contactsServer.opelear(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(it -> {
                    Result<Contact> result = new Result<>();
                    Contact bean = it.get(0);
                    if ("0".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(contact);
                        result.setMsg("联系人成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(contact.getFresult()));
                    }
                    return result;
                });
    }

    public Observable<Result<Contact>> add(Contact contacts) {
        return set("1", contacts);
    }


    public Observable<Result<Contact>> update(Contact contacts) {
        return set("3", contacts);
    }

    public Observable<Result<Contact>> delete(Contact contacts) {
        return set("2", contacts);
    }

    public Observable<Result<Contact>> add(String bid, List<Contact> list) {
        StringBuilder builder = new StringBuilder();
        for (Contact contact : list) {
            String fname = contact.getFname();
            fname = fname.length() > 4 ? fname.substring(0, 4) : fname;
            String fmobile = contact.getFmobile();
            fmobile = fmobile.replaceAll("(\\+86)?-?", "");
            builder.append(fmobile);
            if (list.indexOf(contact) != list.size() - 1) {
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
                .map(contacts -> {
                    Result<Contact> result = new Result<>();
                    Contact contact = contacts.get(0);
                    if ("0".equals(contact.getFresult())) {
                        result.setCode("0");
                        result.setBody(contact);
                        result.setMsg("导入联系人成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(contact.getFresult()));
                    }
                    return null;
                });
    }

}
