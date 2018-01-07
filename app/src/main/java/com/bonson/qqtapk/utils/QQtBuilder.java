package com.bonson.qqtapk.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zjw on 2018/1/2.
 */

public class QQtBuilder {
    public static Object build(String task, Object... bean) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("task", task);
        map.put("body", bean);
        return map;
    }
}
