package com.bonson.qqtapk.view.ui.route;

import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRouteBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class RouteActivity extends BaseDaggerActivity<ActivityRouteBinding> {
    @Inject
    RouteViewModel viewModel;

    RouteTimeDialog timeDialog;

    RouteOverlay routeOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_route);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);
        binding.mapView.showZoomControls(false);
        binding.mapView.showScaleControl(false);
        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());

        viewModel.visible.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.visible.get()) {
                    routeOverlay.setData(viewModel.routes);
                    routeOverlay.addToMap();
                    routeOverlay.zoomToSpan();
                    routeOverlay.next();
                } else {
                    routeOverlay.removeFromMap();
                }
            }
        });
        routeOverlay = new RouteOverlay(binding.mapView.getMap());
        binding.mapView.getMap().setOnMarkerClickListener(routeOverlay);
        routeOverlay.setOnMarkerClickListener((address, time) -> {
            viewModel.time.set(time);
            viewModel.address.set(address);
        });
        viewModel.routes("", "");
    }

    public void showDialog(View view) {
        if (timeDialog == null) {
            timeDialog = new RouteTimeDialog();
            timeDialog.setOnSaveListener((start, end) -> {
                viewModel.routes(start, end);
                timeDialog.dismiss();
            });
        }
        if (!timeDialog.isShowing()) {
            timeDialog.show(getSupportFragmentManager(), "time");
        }
    }

    public void next(View view) {
        routeOverlay.next();
    }
}
