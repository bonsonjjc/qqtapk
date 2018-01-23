package com.bonson.qqtapk.view.ui.route;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.overlayutil.OverlayManager;
import com.bonson.qqtapk.app.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteOverlay extends OverlayManager {
    List<Route> data;

    public RouteOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }

    public void setData(List<Route> data) {
        this.data = data;
    }

    @Override
    public List<OverlayOptions> getOverlayOptions() {
        List<OverlayOptions> options = new ArrayList<>();
        return options;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        return false;
    }
}
