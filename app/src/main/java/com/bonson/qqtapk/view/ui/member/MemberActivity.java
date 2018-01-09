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
    PhoneFragment inputFragment;

    @Inject
    MemberAdapter memberAdapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMemberBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_member);
        binding.toolbar.setTitle("家庭成员");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("邀请");
        binding.toolbar.getTvRight().setOnClickListener(view -> {

        });
        binding.recMember.setAdapter(memberAdapter);
        binding.recMember.addItemDecoration(itemDecoration);
        memberAdapter.setOnItemClickListener(p -> {
            if (!viewModel.isAdmin(p)) {
                toast("有权限");
            } else if (!viewModel.isSelf(p)) {
                toast("只能修改");
            }
            inputFragment.setViewModel(viewModel.modify(p));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, inputFragment)
                    .addToBackStack("member")
                    .commit();
        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.members();
    }
}
