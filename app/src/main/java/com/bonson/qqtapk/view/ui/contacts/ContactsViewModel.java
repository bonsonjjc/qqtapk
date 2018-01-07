package com.bonson.qqtapk.view.ui.contacts;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Contacts;
import com.bonson.qqtapk.model.data.contacts.ContactsModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class ContactsViewModel extends AndroidViewModel {

    private ContactsModel contactsModel;
    private BaseView view;

    private List<Contacts> contacts;


    @Inject
    ContactsViewModel(Application application, ContactsModel contactsModel) {
        super(application);
        this.contactsModel = contactsModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
        notifyChange();
    }

    public void contacts() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        contactsModel.contacts(Baby.baby.getFid(), 0, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        setContacts(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
    }

}
