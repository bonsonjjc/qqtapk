package com.bonson.qqtapk.view.ui.member;

import android.app.Application;
import android.databinding.ObservableArrayList;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.member.MemberModel;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.resource.activity.ActivityUtils;
import com.bonson.resource.activity.BaseView;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class MemberViewModel extends UserViewModel {

    public final List<Member> members = new ObservableArrayList<>();

    private MemberModel memberModel;

    private PhoneViewModel viewModel;

    @Inject
    MemberViewModel(Application application, MemberModel memberModel, PhoneViewModel viewModel) {
        super(application);
        this.viewModel = viewModel;
        this.memberModel = memberModel;
    }

    public void members() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = memberModel.members(user().getBabyId(), 0, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        members.addAll(it.getBody());
                        notifyChange();
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void update(int position, Member member) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = memberModel.update(member)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    view.dismiss();
                    if (it.getCode().equals("0")) {
                        members.set(position, member);
                        notifyChange();
                        view.back();
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
        Disposable disposable = memberModel.update(members.get(position))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    view.dismiss();
                    if (it.getCode().equals("0")) {
                        members.remove(position);
                        notifyChange();
                        view.back();
                        if (isSelf(position)) {
                            view.start(Route.login);
                            ActivityUtils.clear();
                        }
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public boolean isSelf(int position) {
        Member member = members.get(position);
        return member.getAdmin().equals(user().getUserId());
    }

    public boolean isAdmin(int position) {
        Member member = members.get(position);
        return member.getFuid().equals(user().getUserId());
    }

    public PhoneViewModel modifyViewModel(int position) {
        Member member = members.get(position);
        viewModel.title.set("编辑家庭成员");
        viewModel.right.set("保存");
        viewModel.mobileEnable.set(false);
        viewModel.mobile.set(member.getFmobile());
        viewModel.name.set(member.getFname());
        viewModel.setOnPhoneListener(() -> {
            member.setFname(viewModel.name.get());
            member.setFbid(user().getBabyId());
            update(position, member);
        });
        return viewModel;
    }
}
