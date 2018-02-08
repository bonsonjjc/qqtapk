package com.bonson.qqtapk.view.ui.setting.map;

import java.math.BigDecimal;

public class MapUtils {
    public static String size(long dataSize) {
        BigDecimal filesize = new BigDecimal(dataSize);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 1, BigDecimal.ROUND_UP).floatValue();
        return returnValue + "M";
    }
}
