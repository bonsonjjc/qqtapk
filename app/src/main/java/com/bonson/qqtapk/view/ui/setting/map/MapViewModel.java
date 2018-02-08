package com.bonson.qqtapk.view.ui.setting.map;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.bonson.library.utils.LogUtils;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class MapViewModel extends UserViewModel {
    public final ObservableList<OfflineMap> offlineSearchRecords = new ObservableArrayList<>();
    public final ObservableList<OfflineMap> updateElements = new ObservableArrayList<>();
    private int downloadingCount = 0;

    public final ObservableInt hostCount = new ObservableInt(0);

    @Inject
    public MapViewModel(Application application) {
        super(application);
    }

    @Inject
    MapModel mapModel;

    @Override
    public void onCreate() {
        super.onCreate();
        mapModel.init((i, cityID) -> {
            MKOLUpdateElement updateElement = mapModel.getUpdateElement(cityID);
            LogUtils.e("ratio:" + updateElement.ratio);
            update(cityID);
            if (updateElement.ratio == 100) {
                all();
            }
        });
        offlineSearchRecords.clear();
        OfflineMap current = mapModel.findByName("福州").get(0);
        current.cityType = 0;
        offlineSearchRecords.add(current);
        offlineSearchRecords.addAll(mapModel.hostCities());
        hostCount.set(offlineSearchRecords.size());
        offlineSearchRecords.addAll(mapModel.offlineCities());
        download();
    }

    private void update(int cityId) {
        for (int i1 = 0; i1 < updateElements.size(); i1++) {
            OfflineMap offlineMap = updateElements.get(i1);
            if (offlineMap.cityID == cityId) {
                offlineMap = mapModel.getUpdate(offlineMap);
                updateElements.set(i1, offlineMap);
            }
        }
    }

    public void update(int action, int row) {
        OfflineMap record = updateElements.get(row);
        switch (action) {
            case 1:
                if (record.status == MKOLUpdateElement.SUSPENDED) {
                    mapModel.start(record.cityID);
                } else {
                    mapModel.pause(record.cityID);
                }
                all();
                update(record.cityID);
                break;
            case 2:
                mapModel.update(record.cityID);
                all();
                update(record.cityID);
                break;
            case 3:
                mapModel.delete(record.cityID);
                updateElements.remove(row);
                all();
                break;
        }
    }

    public void all() {
        for (int i1 = 0; i1 < offlineSearchRecords.size(); i1++) {
            OfflineMap offlineMap = offlineSearchRecords.get(i1);
            offlineMap = mapModel.getUpdate(offlineMap);
            offlineSearchRecords.set(i1, offlineMap);
        }
    }

    public void download() {
        updateElements.clear();
        List<OfflineMap> downloaded = mapModel.downloaded();
        downloadingCount = downloaded.size();
        List<OfflineMap> downloading = mapModel.downloading();
        updateElements.addAll(downloading);
        updateElements.addAll(downloaded);
    }

    public void download(int row, int section) {
        OfflineMap record = offlineSearchRecords.get(row);
        OfflineMap child;
        if (section == -1) {
            updateElements.add(0, record);
            mapModel.start(record.cityID);
        } else {
            child = record.childMap.get(section);
            mapModel.start(child.cityID);
            updateElements.add(0, child);
        }
        record = mapModel.getUpdate(record);
        offlineSearchRecords.set(row, record);
    }
}
