package com.bonson.qqtapk.view.ui.center.locmap;

import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLocMapBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class LocMapActivity extends BaseDaggerActivity<ActivityLocMapBinding> {
    @Inject
    LocMapViewModel viewModel;
    Marker overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_loc_map);
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        viewModel.position.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                LatLng latLng = viewModel.position.get();
                if (overlay == null) {
                    MarkerOptions icon = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ico_mark));
                    icon.position(latLng);
                    overlay = (Marker) binding.mapView.getMap().addOverlay(icon);
                } else {
                    overlay.setPosition(latLng);
                }
                binding.mapView.getMap().animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(latLng, 15f));
            }
        });
        binding.mapView.getMap().setOnMarkerClickListener(marker -> {
            show(marker.getPosition());
            return true;
        });
        viewModel.map("121");
    }

    InfoWindow infoWindow;

    private void show(LatLng latLng) {
        if (infoWindow == null) {
            View view = View.inflate(this, R.layout.window_loc_map, null);
            TextView tvAddress = view.findViewById(R.id.tv_address);
            TextView tvTime = view.findViewById(R.id.tv_time);
            tvAddress.setText("位置:[G]福建福州晋安五四北泰禾广场");
            tvTime.setText("2012-12-12 20:39:23");
            infoWindow = new InfoWindow(view, latLng, 0);
        }
        binding.mapView.getMap().hideInfoWindow();
        binding.mapView.getMap().showInfoWindow(infoWindow);
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
