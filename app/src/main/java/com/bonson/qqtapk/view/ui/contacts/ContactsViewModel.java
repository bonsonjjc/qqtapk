package com.bonson.qqtapk.view.ui.contacts;

import android.app.Application;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Contact;
import com.bonson.qqtapk.model.data.contacts.ContactsModel;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class ContactsViewModel extends AndroidViewModel {
    private ContactsModel contactsModel;
    private BaseView view;

    private List<Contact> contacts = new ArrayList<>();

    private PhoneViewModel phoneViewModel;

    @Inject
    ContactsViewModel(Application application, ContactsModel contactsModel, PhoneViewModel phoneViewModel) {
        super(application);
        this.phoneViewModel = phoneViewModel;
        this.contactsModel = contactsModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts.addAll(contacts);
        notifyChange();
    }

    public void contacts() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = contactsModel.contacts(Baby.baby.getFid(), 0, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        setContacts(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public PhoneViewModel initFragment() {
        phoneViewModel.mobileHint.set("输入号码");
        phoneViewModel.nameHint.set("输入名称");
        phoneViewModel.mobile.set("");
        phoneViewModel.name.set("");

        phoneViewModel.title.set("添加联系人");
        phoneViewModel.right.set("保存");
        phoneViewModel.setOnPhoneListener(() -> {
            if (TextUtils.isEmpty(phoneViewModel.name.get())) {

            }
            Contact contact = new Contact();
            contact.setBid(Baby.baby.getFid());
            contact.setFmobile(phoneViewModel.mobile.get());
            contact.setFname(phoneViewModel.name.get());
            add(contact);
        });
        return phoneViewModel;
    }

    public PhoneViewModel initFragment(int position) {
        phoneViewModel.mobileHint.set("输入号码");
        phoneViewModel.nameHint.set("输入名称");
        phoneViewModel.title.set("修改联系人");
        phoneViewModel.right.set("保存");
        Contact contact = this.contacts.get(position);
        contact.setBid(Baby.baby.getFid());
        phoneViewModel.mobile.set(contact.getFmobile());
        phoneViewModel.name.set(contact.getFname());
        phoneViewModel.what = position;
        phoneViewModel.setOnPhoneListener(() -> update(position, contact));
        return phoneViewModel;
    }

    public void add(Contact contact) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = contactsModel.add(contact)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        view.toast("添加" + it.getMsg());
                        contacts.add(it.getBody());
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void delete(int position) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = contactsModel.delete(contacts.get(position))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        view.toast("删除" + it.getMsg());
                        contacts.remove(position);
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void update(int position, Contact contact) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = contactsModel.update(contact)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        view.toast("修改" + it.getMsg());
                        contacts.set(position, contact);
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
