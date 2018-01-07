package com.bonson.qqtapk.view.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by zjw on 2018/1/4.
 */
@BindingMethods(value = {
        @BindingMethod(type = TextureMapView.class, attribute = "android:markerClick", method = "setOnMarkerClickListener"),
        @BindingMethod(type = TextureMapView.class, attribute = "android:mapClick", method = "setMapClickListener")
})
public abstract class MapBindingAdapter {

    @BindingAdapter("android:markerClick")
    public static void setMarKerClickListener(TextureMapView mapView, BaiduMap.OnMarkerClickListener marKerClickListener) {
        mapView.getMap().setOnMarkerClickListener(marKerClickListener);
    }

    @BindingAdapter("android:mapClick")
    public static void setMapClickListener(TextureMapView mapView, OnMarkerClickListener marKerClickListener) {
        mapView.getMap().setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (marKerClickListener != null) {
                    marKerClickListener.onMapClick(mapView, latLng);
                }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    public interface OnMarkerClickListener {
        void onMapClick(TextureMapView mapView, LatLng latLng);
    }
}
