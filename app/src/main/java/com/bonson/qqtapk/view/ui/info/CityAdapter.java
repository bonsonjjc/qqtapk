package com.bonson.qqtapk.view.ui.info;

import com.bonson.qqtapk.model.bean.Area;
import com.bonson.qqtapk.model.db.CityDao;
import com.bonson.resource.dialog.CityPickerDialog;

import java.util.List;

import javax.inject.Inject;

public class CityAdapter implements CityPickerDialog.CityAdapter {
    private List<Area> provinces;
    private List<Area> citys;
    private List<Area> districts;

    private CityDao cityDao;
    @Inject
    public CityAdapter(CityDao cityDao) {
        this.cityDao = cityDao;
        provinces = cityDao.city("0");
        citys = cityDao.city(provinces.get(0).getId());
        districts = cityDao.city(citys.get(0).getId());
    }

    public List<Area> getProvinces() {
        return provinces;
    }

    public List<Area> getCitys() {
        return citys;
    }

    public List<Area> getDistricts() {
        return districts;
    }

    @Override
    public int provinceSize() {
        return provinces.size() - 1;
    }

    @Override
    public int citySize(int index) {
        Area area = provinces.get(index);
        citys = cityDao.city(area.getId());
        return citys.size() - 1;
    }

    @Override
    public int districtSize(int index) {
        Area area = citys.get(index);
        districts = cityDao.city(area.getId());
        return districts.size() - 1;
    }

    @Override
    public String province(int index) {
        return provinces.get(index).getName();
    }

    @Override
    public String city(int index) {
        return citys.get(index).getName();
    }

    @Override
    public String district(int index) {
        return districts.get(index).getName();
    }
}