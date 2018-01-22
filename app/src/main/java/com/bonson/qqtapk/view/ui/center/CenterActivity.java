package com.bonson.qqtapk.view.ui.center;

import android.content.Intent;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivityCenterBinding;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.view.adapter.CenterAdapter;
import com.bonson.qqtapk.view.binding.AdapterDataChangeFactory;
import com.bonson.resource.activity.BaseDaggerActivity;

import java.util.List;

import javax.inject.Inject;

public class CenterActivity extends BaseDaggerActivity<ActivityCenterBinding> {
    @Inject
    CenterViewModel viewModel;

    @Inject
    List<Message> messageList;

    @Inject
    CenterAdapter centerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_center);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("消息中心");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.recCenters.setAdapter(centerAdapter);
        AdapterDataChangeFactory.create(centerAdapter).attach(viewModel.centers);

        centerAdapter.setOnItemClickListener(position -> {
            Message message = viewModel.centers.get(position);
            Intent intent = new Intent();
            intent.setClassName(CenterActivity.this, Route.message);
            intent.putExtra("cycleType", message.getFtype());
            intent.putExtra("title", message.getTitle());
            startActivity(intent);
        });
        viewModel.centers.addAll(messageList);
        viewModel.message();
    }
}
