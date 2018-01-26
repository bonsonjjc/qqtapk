package com.bonson.fjqqt.view.area;

import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.baidu.mapapi.map.Circle;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Stroke;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivitySafeAreaBinding;
import com.bonson.qqtapk.view.ui.area.SafeAreaViewModel;
import com.bonson.qqtapk.view.ui.index.LocationViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class SafeAreaActivity extends BaseDaggerActivity<ActivitySafeAreaBinding> {
    @Inject
    SafeAreaViewModel viewModel;
    Circle overlay;

    @Inject
    LocationViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_safe_area);
        binding.setViewModel(viewModel);
        binding.setLocViewModel(locationViewModel);
        setViewModel(viewModel, locationViewModel);
        viewModel.setLocationViewModel(locationViewModel);
        binding.rgType.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    viewModel.type.set("2");
                    break;
                case R.id.rb_school:
                    viewModel.type.set("1");
                    break;
                default:
                    viewModel.type.set("3");
                    break;
            }
            viewModel.init();
        });

        binding.llSearch.setVisibility(View.VISIBLE);
        binding.rgType.setVisibility(View.VISIBLE);
        binding.toolbar.getTvTitle().setVisibility(View.GONE);

        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> viewModel.save());

        Observable.OnPropertyChangedCallback propertyChangedCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                overlay.setRadius(viewModel.radius.get() + 200);
                overlay.setFillColor(viewModel.state.get() ? color(R.color.area_fill_on) : color(R.color.area_fill_off));
                overlay.setStroke(new Stroke(3, viewModel.state.get() ? color(R.color.area_boder_on) : color(R.color.area_boder_off)));
                overlay.setCenter(viewModel.position.get());
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(viewModel.position.get(), 16.0f);
                binding.mapView.getMap().animateMapStatus(mapStatusUpdate);
            }
        };
        viewModel.state.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.position.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.radius.addOnPropertyChangedCallback(propertyChangedCallback);
        overlay = (Circle) binding.mapView.getMap().addOverlay(new CircleOptions().center(viewModel.position.get()));
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
