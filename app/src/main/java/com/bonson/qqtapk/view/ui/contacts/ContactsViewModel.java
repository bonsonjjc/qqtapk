package com.bonson.qqtapk.view.ui.contacts;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Contact;
import com.bonson.qqtapk.model.data.contacts.ContactsHelper;
import com.bonson.qqtapk.model.data.contacts.ContactsModel;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
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

    public final ObservableList<Contact> contacts = new ObservableArrayList<>();

    @Inject
    PhoneViewModel viewModel;

    @Inject
    SelectViewModel selectViewModel;

    @Inject
    public ContactsViewModel(Application application, ContactsModel contactsModel) {
        super(application);
        this.contactsModel = contactsModel;
    }

    public void contacts() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = contactsModel.contacts(Baby.baby.getFid(), 0, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        contacts.clear();
                        contacts.addAll(it.getBody());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public SelectViewModel importViewModel() {
        selectViewModel.setSingle(false);
        selectViewModel.setSelects(ContactsHelper.contacts(getApplication()));
        selectViewModel.setOnSaveListener(() -> {
            List<Contact> contacts = new ArrayList<>();
            selectViewModel.selected((index, select) -> {
                if (select.isChecked()) {
                    Contact contact = new Contact();
                    contact.setFname(select.getName());
                    contact.setFmobile(select.getValue());
                    contacts.add(contact);
                }
                return true;
            });
            if (contacts.isEmpty()) {
                view.toast("请选择联系人");
                return;
            }
            addAll(contacts);
        });
        selectViewModel.title.set("选择联系人");
        return selectViewModel;
    }

    public PhoneViewModel initFragment() {
        viewModel.mobile.set("");
        viewModel.name.set("");
        viewModel.title.set("添加联系人");
        viewModel.right.set("保存");
        viewModel.setOnPhoneListener(() -> {
            Contact contact = new Contact();
            contact.setBid(Baby.baby.getFid());
            contact.setFmobile(viewModel.mobile.get());
            contact.setFname(viewModel.name.get());
            add(contact);
        });
        return viewModel;
    }

    public PhoneViewModel initFragment(int position) {
        viewModel.title.set("修改联系人");
        viewModel.right.set("保存");
        Contact contact = this.contacts.get(position);
        contact.setBid(Baby.baby.getFid());
        viewModel.mobile.set(contact.getFmobile());
        viewModel.name.set(contact.getFname());
        viewModel.setOnPhoneListener(() -> {
            if (TextUtils.isEmpty(viewModel.name.get())) {
                view.toast("请输入名称");
                return;
            }
            if (TextUtils.isEmpty(viewModel.mobile.get())) {
                view.toast("请输入号码");
                return;
            }
            contact.setFname(viewModel.name.get());
            contact.setFmobile(viewModel.mobile.get());
            update(position, contact);
        });
        return viewModel;
    }

    public void add(Contact contact) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = contactsModel.add(contact)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    if (it.getCode().equals("0")) {
                        view.toast("添加" + it.getMsg());
                        contacts.add(it.getBody());
                        view.back();
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void delete(int position) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Contact contact = contacts.get(position);
        contact.setBid(Baby.baby.getFid());
        Disposable disposable = contactsModel.delete(contact)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    if (it.getCode().equals("0")) {
                        view.toast("删除" + it.getMsg());
                        contacts.remove(position);
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void update(int position, Contact contact) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = contactsModel.update(contact)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    if (it.getCode().equals("0")) {
                        view.toast("修改" + it.getMsg());
                        contacts.set(position, contact);
                        view.back();
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void addAll(List<Contact> contacts) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = contactsModel.add(Baby.baby.getFid(), contacts)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.back();
                        contacts();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
