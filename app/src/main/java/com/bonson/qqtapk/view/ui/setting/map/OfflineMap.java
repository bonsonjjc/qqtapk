package com.bonson.qqtapk.view.ui.setting.map;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;

import java.util.List;

public class OfflineMap extends MKOLUpdateElement {
    List<OfflineMap> childMap;
    public int cityType;
    public long dataSize;

    public boolean isDownload() {
        return ratio == 100;
    }

    public String getState() {
        if (ratio == 100) return "已下载";
        switch (status) {
            case FINISHED:
                return "已下载";
            case SUSPENDED:
                return "已暂停";
            case DOWNLOADING:
                return "下载中";
            default:
                return MapUtils.size(dataSize);
        }
    }

    public String getStateType() {
        if (ratio == 100) return "已下载";
        switch (status) {
            case FINISHED:
                return "已下载";
            case SUSPENDED:
                return "已暂停";
            case DOWNLOADING:
                return ratio + "%";
            default:
                return "已下载";
        }
    }


    public boolean isParent() {
        return cityType == 1;
    }
}
