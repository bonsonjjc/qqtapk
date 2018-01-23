package com.bonson.qqtapk.view.ui.index;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.TextureMapView;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivityIndexBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/2.
 */

public class IndexActivity extends BaseDaggerActivity<ActivityIndexBinding> {
    @Inject
    IndexViewModel viewModel;

    @Inject
    BabyAdapter babyAdapter;

    @Inject
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_index);
        binding.setViewModel(viewModel);
        binding.setMainViewModel(viewModel.viewModel);
        setViewModel(viewModel, viewModel.viewModel);

        binding.mapView.onCreate(this, savedInstanceState);

        binding.recBabyList.setAdapter(babyAdapter);
        viewModel.babies();
        binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View menu, float slideOffset) {
                int m = menu.getRight() < binding.content.getRight() ? 1 : -1;
                binding.content.setTranslationX(slideOffset * menu.getWidth() * m);
            }
        });

        binding.recMenus.setAdapter(menuAdapter);
        viewModel.initMenu();

        babyAdapter.setOnItemClickListener((v) -> {
            if (viewModel.babies.size() - 1 == v) {
                start(Route.scan);
                return;
            }
            viewModel.change(v);
        });
        viewModel.device();
    }

    public void icon(View view) {
        toggle(Gravity.START);
    }

    public void menu(View view) {
        toggle(Gravity.END);
    }

    public void toggle(int gravity) {
        boolean open = binding.drawerLayout.isDrawerOpen(gravity);
        if (open) {
            binding.drawerLayout.closeDrawer(gravity);
        } else {
            binding.drawerLayout.openDrawer(gravity);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.mapView.onDestroy();
    }
}
