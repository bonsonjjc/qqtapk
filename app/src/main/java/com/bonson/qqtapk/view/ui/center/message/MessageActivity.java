package com.bonson.qqtapk.view.ui.center.message;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivityMessageBinding;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.utils.binding.AdapterDataChangeFactory;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class MessageActivity extends BaseDaggerActivity<ActivityMessageBinding> {
    @Inject
    MessageViewModel viewModel;
    String type;
    @Inject
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_message);

        type = getIntent().getStringExtra("type");
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setTitle(getIntent().getStringExtra("title"));

        messageAdapter.setType(type);
        binding.recMessages.setAdapter(messageAdapter);
        AdapterDataChangeFactory.create(messageAdapter).attach(viewModel.messages);
        viewModel.list(type);
        messageAdapter.setOnItemClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName(this, Route.locmap);
            startActivity(intent);
        });
        messageAdapter.setOnFriendListener((p, type) -> {
            viewModel.friend(p, type);
        });
    }
}
