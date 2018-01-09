package com.bonson.qqtapk.view.ui.member;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMemberBinding;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.view.adapter.MemberAdapter;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class MemberActivity extends BaseDaggerActivity {
    @Inject
    MemberViewModel viewModel;

    @Inject
    PhoneViewModel inputViewModel;

    @Inject
    PhoneFragment inputFragment;

    @Inject
    MemberAdapter memberAdapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMemberBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_member);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("家庭成员");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("邀请");
        binding.toolbar.getTvRight().setOnClickListener(view -> {

        });
        binding.recMember.setAdapter(memberAdapter);
        binding.recMember.addItemDecoration(itemDecoration);
        memberAdapter.setOnItemClickListener(position -> {
            if (!viewModel.isAdmin(position)) {
                toast("有权限");
            } else if (!viewModel.isSelf(position)) {
                toast("只能修改");
            }
            Member member = viewModel.members.get(position);
            inputFragment.setViewModel(inputViewModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, inputFragment)
                    .addToBackStack("member")
                    .commit();
            inputViewModel.title.set("编辑家庭成员");
            inputViewModel.right.set("保存");
            inputViewModel.mobileEnable.set(false);
            inputViewModel.mobileHint.set("输入手机号码");
            inputViewModel.mobile.set(member.getFmobile());
            inputViewModel.nameHint.set("输入名称");
            inputViewModel.name.set(member.getFname());
        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.members();
    }
}
