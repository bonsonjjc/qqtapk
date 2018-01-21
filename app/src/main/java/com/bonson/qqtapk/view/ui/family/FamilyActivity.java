package com.bonson.qqtapk.view.ui.family;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityFamilyBinding;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.view.adapter.FamilyAdapter;
import com.bonson.qqtapk.view.binding.AdapterDataChangeFactory;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.resource.activity.BaseDaggerActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class FamilyActivity extends BaseDaggerActivity {
    @Inject
    FamilyViewModel viewModel;
    @Inject
    PhoneFragment inputFragment;

    @Inject
    FamilyAdapter familyAdapter;

    @Inject
    List<Family> familyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFamilyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_family);
        binding.recNumbers.setAdapter(familyAdapter);
        binding.toolbar.setTitle("亲情号码");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        AdapterDataChangeFactory.create(familyAdapter).attach(viewModel.families);
        familyAdapter.setOnItemClickListener(position -> {
            inputFragment.setViewModel(viewModel.updateViewModel(position));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, inputFragment)
                    .addToBackStack("family")
                    .commit();
        });
        viewModel.setView(this);
        viewModel.families.addAll(familyList);
        binding.setViewModel(viewModel);
        viewModel.families();
    }
}
