package com.bonson.qqtapk.view.ui.setting.map;


import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.bonson.library.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MapModel {
    private MKOfflineMap mkOfflineMap;

    @Inject
    public MapModel(MKOfflineMap mkOfflineMap) {
        this.mkOfflineMap = mkOfflineMap;
    }

    public List<OfflineMap> hostCities() {
        ArrayList<MKOLSearchRecord> hotCityList = mkOfflineMap.getHotCityList();
        if (hotCityList == null) {
            return new ArrayList<>();
        }
        List<OfflineMap> maps = new ArrayList<>();
        for (MKOLSearchRecord record : hotCityList) {
            OfflineMap offlineMap = convert(record);
            maps.add(offlineMap);
        }
        return maps;
    }

    private OfflineMap convert(MKOLSearchRecord record) {
        OfflineMap offlineMap = new OfflineMap();
        offlineMap.cityID = record.cityID;
        offlineMap.cityName = record.cityName;
        offlineMap.cityType = record.cityType;
        offlineMap.dataSize = record.dataSize;
        return getUpdate(offlineMap);
    }

    public OfflineMap getUpdate(OfflineMap offlineMap) {
        MKOLUpdateElement updateElement = getUpdateElement(offlineMap.cityID);
        if (updateElement != null) {
            offlineMap.ratio = updateElement.ratio;
            offlineMap.update = updateElement.update;
            offlineMap.size = updateElement.size;
            offlineMap.geoPt = updateElement.geoPt;
            offlineMap.level = updateElement.level;
            offlineMap.status = updateElement.status;
        }
        return offlineMap;
    }

    public OfflineMap getUpdate(MKOLUpdateElement updateElement) {
        OfflineMap offlineMap = new OfflineMap();
        if (updateElement != null) {
            offlineMap.cityID = updateElement.cityID;
            offlineMap.cityName = updateElement.cityName;
            offlineMap.dataSize = updateElement.size;
            offlineMap.ratio = updateElement.ratio;
            offlineMap.update = updateElement.update;
            offlineMap.size = updateElement.size;
            offlineMap.geoPt = updateElement.geoPt;
            offlineMap.level = updateElement.level;
            offlineMap.status = updateElement.status;
        }
        return offlineMap;
    }

    public List<OfflineMap> offlineCities() {
        List<MKOLSearchRecord> all = mkOfflineMap.getOfflineCityList();
        if (all == null) {
            return new ArrayList<>();
        }
        List<OfflineMap> maps = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            MKOLSearchRecord record = all.get(i);
            maps.add(offlineMap(record));
        }
        return maps;
    }


    public OfflineMap offlineMap(MKOLSearchRecord searchRecord) {
        OfflineMap offlineMap;
        if (searchRecord.cityType != 1) {
            offlineMap = convert(searchRecord);
            offlineMap.cityType = 0;
            return offlineMap;
        }
        offlineMap = new OfflineMap();
        offlineMap.cityID = searchRecord.cityID;
        offlineMap.cityName = searchRecord.cityName;
        offlineMap.cityType = searchRecord.cityType;
        List<OfflineMap> records = new ArrayList<>();
        ArrayList<MKOLSearchRecord> childCities1 = searchRecord.childCities;
        if (searchRecord.cityType == 1) {
            for (int i = 0; i < childCities1.size(); i++) {
                records.add(convert(childCities1.get(i)));
            }
        }
        offlineMap.childMap = records;
        return offlineMap;
    }

    public List<OfflineMap> downloaded() {
        return updateElement((index, element) -> element.ratio == 100 || element.status == MKOLUpdateElement.FINISHED);
    }

    public List<OfflineMap> downloading() {
        return updateElement((index, element) -> element.ratio != 100);
    }

    public List<OfflineMap> updateElement(ListUtils.ListFilter<MKOLUpdateElement> filter) {
        List<MKOLUpdateElement> updateElements = mkOfflineMap.getAllUpdateInfo();
        if (updateElements == null) {
            return new ArrayList<>();
        }

        List<MKOLUpdateElement> filters = ListUtils.filters(updateElements, filter);
        if (filter == null)
            return new ArrayList<>();
        List<OfflineMap> maps = new ArrayList<>();
        for (MKOLUpdateElement mkolUpdateElement : filters) {
            maps.add(getUpdate(mkolUpdateElement));
        }
        return maps;
    }

    public MKOLUpdateElement getUpdateElement(int cityId) {
        return mkOfflineMap.getUpdateInfo(cityId);
    }

    public List<OfflineMap> findByName(String keyword) {
        List<OfflineMap> maps = new ArrayList<>();
        ArrayList<MKOLSearchRecord> searchRecords = mkOfflineMap.searchCity(keyword);
        for (MKOLSearchRecord searchRecord : searchRecords) {
            maps.add(convert(searchRecord));
        }
        return maps;
    }

    public void init(MKOfflineMapListener listener) {
        mkOfflineMap.init(listener);
    }

    public void start(int cityId) {
        mkOfflineMap.start(cityId);
    }

    public void pause(int cityId) {
        mkOfflineMap.pause(cityId);
    }

    public void delete(int cityId) {
        mkOfflineMap.remove(cityId);
    }

    public void update(int cityId) {
        mkOfflineMap.update(cityId);
    }

    public void release() {
        mkOfflineMap.destroy();
    }
}
