package com.bonson.qqtapk.view.ui.index;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityIndexBinding;
import com.bonson.qqtapk.view.ui.main.MainFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/2.
 */

public class IndexActivity extends BaseDaggerActivity {
  @Inject IndexViewModel viewModel;

  @Inject MainFragment mainFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityIndexBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_index);
    getSupportFragmentManager().beginTransaction().add(R.id.content, mainFragment).commit();
    binding.setViewModel(viewModel);
  }
}