package com.bonson.qqtapk.view.ui.member;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMemberBinding;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.ActionSheetDialog;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class MemberActivity extends BaseDaggerActivity<ActivityMemberBinding> {
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
        setBindingLayout(R.layout.activity_member);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("家庭成员");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("邀请");
        binding.toolbar.getTvRight().setOnClickListener(view -> {

        });
        binding.recMember.setAdapter(memberAdapter);
        binding.recMember.addItemDecoration(itemDecoration);
        memberAdapter.setOnItemClickListener(p -> {
            if (!viewModel.isAdmin(p)) {
                toast("没有权限");
                return;
            } else if (!viewModel.isSelf(p)) {
                toast("只能修改");
                return;
            }
            inputFragment.setPhoneViewModel(viewModel.modifyViewModel(p));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, inputFragment)
                    .addToBackStack("member")
                    .commit();
        });

        viewModel.members();
    }

    public void delete(int p) {
        if (!viewModel.isAdmin(p)) {
            toast("没有权限");
            return;
        } else if (!viewModel.isSelf(p)) {
            toast("只能修改");
            return;
        }
        new ActionSheetDialog()
                .setTitle("是否要删除该呼入限制?")
                .setActionSheet(new String[]{"删除"}, Color.RED)
                .setOnItemClickListener(position -> viewModel.delete(p))
                .show(getSupportFragmentManager(), "delete");
    }
}
