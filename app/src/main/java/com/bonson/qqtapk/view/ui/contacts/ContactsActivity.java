package com.bonson.qqtapk.view.ui.contacts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityContactsBinding;
import com.bonson.qqtapk.utils.binding.AdapterDataChangeFactory;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.ActionSheetDialog;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class ContactsActivity extends BaseDaggerActivity<ActivityContactsBinding> {
    @Inject
    ContactsViewModel viewModel;

    @Inject
    PhoneFragment phoneFragment;

    @Inject
    SelectFragment selectFragment;

    @Inject
    ContactAdapter adapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    private ActionSheetDialog actionSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_contacts);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("通讯录");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            showAdd();
        });
        binding.recContacts.setAdapter(adapter);
        binding.recContacts.addItemDecoration(itemDecoration);
        AdapterDataChangeFactory.create(adapter).attach(viewModel.contacts);
        adapter.setOnItemClickListener(v -> {
            phoneFragment.setPhoneViewModel(viewModel.initFragment(v));
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, phoneFragment)
                    .addToBackStack("contacts")
                    .commit();
        });
        adapter.setOnItemLongClickListener(v -> {
            showDelete(v);
            return true;
        });
        viewModel.contacts();
    }

    private void showAdd() {
        if (actionSheetDialog == null) {
            actionSheetDialog = new ActionSheetDialog();
        }
        actionSheetDialog.setActionSheet(new String[]{"导入联系人", "手动添加"}, Color.RED);
        actionSheetDialog.setTitle("添加联系人");
        actionSheetDialog.setOnItemClickListener(position -> {
            if (position == 0) {
                selectFragment.setViewModel(viewModel.importViewModel());
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, selectFragment)
                        .addToBackStack("contacts")
                        .commit();
            } else {
                phoneFragment.setPhoneViewModel(viewModel.initFragment());
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, phoneFragment)
                        .addToBackStack("import")
                        .commit();
            }
        });
        actionSheetDialog.show(getSupportFragmentManager(), "add");
    }

    public void showDelete(int position) {
        if (actionSheetDialog == null) {
            actionSheetDialog = new ActionSheetDialog();
        }
        actionSheetDialog.setTitle("是否删除该联系人?");
        actionSheetDialog.setActionSheet(new String[]{"删除"}, Color.RED);
        actionSheetDialog.setOnItemClickListener(v -> viewModel.delete(position));
        actionSheetDialog.show(getSupportFragmentManager(), "delete");
    }
}
