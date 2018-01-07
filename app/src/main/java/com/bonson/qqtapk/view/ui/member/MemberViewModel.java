package com.bonson.qqtapk.view.ui.member;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.data.member.MemberModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class MemberViewModel extends AndroidViewModel {

    private List<Member> members;

    private MemberModel memberModel;

    private BaseView view;

    @Inject
    public MemberViewModel(Application application, MemberModel memberModel) {
        super(application);
        this.memberModel = memberModel;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
        notifyChange();
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void members() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        memberModel.members(Baby.baby.getFid(), 0, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        setMembers(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
    }
}
