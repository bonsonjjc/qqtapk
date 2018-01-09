package com.bonson.qqtapk.view.ui.contacts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityContactsBinding;
import com.bonson.qqtapk.view.adapter.ContactAdapter;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class ContactsActivity extends BaseDaggerActivity {
    @Inject
    ContactsViewModel viewModel;

    @Inject
    PhoneFragment inputFragment;

    @Inject
    ContactAdapter adapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts);
        binding.toolbar.setTitle("通讯录");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            inputFragment.setViewModel(viewModel.initFragment());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, inputFragment)
                    .addToBackStack("contacts")
                    .commit();
        });
        binding.recContacts.setAdapter(adapter);
        binding.recContacts.addItemDecoration(itemDecoration);
        adapter.setOnItemClickListener(v -> {
                    inputFragment.setViewModel(viewModel.initFragment(v));
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("contacts")
                            .commit();
                }
        );
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.contacts();
    }
}
