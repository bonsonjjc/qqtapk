package com.bonson.qqtapk.model.data.index;

import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.bean.Location;

import java.util.List;

public class Index {
    private List<Device> ternimal;
    private List<Location> location;

    public List<Device> getTernimal() {
        return ternimal;
    }

    public void setTernimal(List<Device> ternimal) {
        this.ternimal = ternimal;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
}
