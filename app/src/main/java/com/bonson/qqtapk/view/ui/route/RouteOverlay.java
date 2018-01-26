package com.bonson.qqtapk.view.ui.route;

import android.graphics.Color;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.overlayutil.OverlayManager;
import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.model.bean.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteOverlay extends OverlayManager {
    private List<Route> results;

    public RouteOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }

    public void setData(List<Route> data) {
        this.results = data;
        index = -1;
        before = 0;
    }

    @Override
    public List<OverlayOptions> getOverlayOptions() {
        List<OverlayOptions> options = new ArrayList<>();
        if (results == null || results.isEmpty()) {
            return options;
        }
        List<LatLng> points = new ArrayList<>();
        int count = results.size();
        for (int i = 0; i < count; i++) {
            Route route = results.get(i);
            MarkerOptions markerOptions = new MarkerOptions();
            double x = NumberUtils.parseDouble(route.getFx());
            double y = NumberUtils.parseDouble(route.getFy());
            LatLng position = new LatLng(x, y);
            markerOptions.position(position);
            Bundle bundle = new Bundle();
            bundle.putString("time", route.getFctime());
            bundle.putString("address", route.getFshort());
            bundle.putInt("index", i);
            bundle.putParcelable("position", position);
            markerOptions.extraInfo(bundle);
            int icon;
            if (i == 0 && count > 1) {
                bundle.putInt("unIcon", R.drawable.ico_map_start_selected);
                icon = R.drawable.ico_map_start;
                bundle.putInt("icon", icon);
            } else if (i == count - 1 && count > 1) {
                bundle.putInt("unIcon", R.drawable.ico_map_end_selected);
                icon = R.drawable.ico_map_end;
                bundle.putInt("icon", icon);
            } else {
                bundle.putInt("unIcon", R.drawable.ico_map_selected);
                icon = R.drawable.ico_map_normal;
                bundle.putInt("icon", icon);
            }
            markerOptions.icon(BitmapDescriptorFactory.fromResource(icon));
            points.add(position);
            options.add(markerOptions);
        }
        if (points.size() > 1) {
            PolylineOptions polylineOptions = new PolylineOptions().points(points)
                    .width(10)
                    .color(Color.argb(178, 0, 78, 255))
                    .zIndex(0);
            options.add(polylineOptions);
        }
        return options;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        index = marker.getExtraInfo().getInt("index", 0);
        select();
        return true;
    }

    private OnMarkerClickListener onMarkerClickListener;

    public void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        this.onMarkerClickListener = onMarkerClickListener;
    }

    public interface OnMarkerClickListener {
        void onMarkerClick(String address, String time);
    }

    private int index = -1, before = 0;

    private void marker(Marker marker, boolean isSelect) {
        Bundle bundle = marker.getExtraInfo();
        String time = bundle.getString("time", "");
        String address = bundle.getString("address", "");
        LatLng latLng = bundle.getParcelable("position");
        marker.setIcon(BitmapDescriptorFactory.fromResource(bundle.getInt(isSelect ? "unIcon" : "icon")));
        if (isSelect && onMarkerClickListener != null) {
            onMarkerClickListener.onMarkerClick(address, time);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
        }
    }

    public void next() {
        index++;
        if (index == results.size()) {
            index = 0;
        }
        select();
    }

    private void select() {
        Overlay overlay = mOverlayList.get(index);
        if (overlay instanceof Marker) {
            Marker marker = (Marker) overlay;
            marker(marker, true);
        }
        if (index == before) {
            return;
        }
        overlay = mOverlayList.get(before);
        if (overlay instanceof Marker) {
            Marker marker = (Marker) overlay;
            marker(marker, false);
        }
        before = index;
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        return false;
    }
}
