package com.bonson.qqtapk.view.ui.member;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.text.TextUtils;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.member.MemberModel;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class MemberViewModel extends AndroidViewModel {

    public final List<Member> members = new ObservableArrayList<>();

    private MemberModel memberModel;

    private BaseView view;

    private PhoneViewModel viewModel;

    @Inject
    MemberViewModel(Application application, MemberModel memberModel, PhoneViewModel viewModel) {
        super(application);
        this.viewModel = viewModel;
        this.memberModel = memberModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void members() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = memberModel.members(Baby.baby.getFid(), 0, 10)
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
        Disposable disposable = memberModel.update(member)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        members.set(position, member);
                        notifyChange();
                        view.back();
//                        if (isAdmin(position)) {
//                            view.start(Route.login);
//                        }
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public boolean isSelf(int position) {
        Member member = members.get(position);
        return member.getAdmin().equals(User.user.getUserId());
    }

    public boolean isAdmin(int position) {
        Member member = members.get(position);
        return member.getFuid().equals(User.user.getUserId());
    }

    public PhoneViewModel modify(int position) {
        Member member = members.get(position);
        viewModel.title.set("编辑家庭成员");
        viewModel.right.set("保存");
        viewModel.mobileEnable.set(false);
        viewModel.mobile.set(member.getFmobile());
        viewModel.name.set(member.getFname());
        viewModel.setOnPhoneListener(() -> {
            member.setFname(viewModel.name.get());
            member.setFbid(Baby.baby.getFid());
            update(position, member);
        });
        return viewModel;
    }
}
