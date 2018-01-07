package com.bonson.qqtapk.view.ui.contacts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityContactsBinding;
import com.bonson.qqtapk.view.ui.contacts.input.InputFragment;
import com.bonson.qqtapk.view.ui.contacts.input.InputViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class ContactsActivity extends BaseDaggerActivity {
    @Inject
    ContactsViewModel viewModel;

    @Inject
    InputFragment inputFragment;

    @Inject
    InputViewModel inputViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("通讯录");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            inputFragment.setViewModel(inputViewModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, inputFragment)
                    .addToBackStack("contacts")
                    .commit();
            inputViewModel.title.set("添加联系人");
            inputViewModel.right.set("保存");
        });
        viewModel.setView(this);
        viewModel.contacts();
    }
}
