package com.bonson.qqtapk.view.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.resource.activity.BaseDaggerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjw on 2018/1/3.
 */

public class MenuHelper {
    public static List<Menu> createMenu() {
        List<Menu> menus = new ArrayList<>();
        for (String name : Baby.baby.getFmenus().split(",")) {
            Menu menu = new Menu();
            menu.setName(name);
            menus.add(menu);
            Activity activity = BaseDaggerActivity.activityTask.get(0);
            switch (name) {
                case "消息中心":
                    menu.setIcon("ico_menu_xxtx");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.center);
                        activity.startActivity(intent);
                    });
                    break;
                case "亲情号码":
                    menu.setIcon("ico_menu_qqhm");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.family);
                        activity.startActivity(intent);
                    });
                    break;
                case "安全区域":
                    menu.setIcon("ico_menu_aqqy");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.area);
                        activity.startActivity(intent);
                    });
                    break;
                case "上课静默":
                    menu.setIcon("ico_menu_skjm");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.lesson);
                        activity.startActivity(intent);
                    });
                    break;
                case "定位模式":
                    menu.setIcon("ico_menu_dwms");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.mode);
                        activity.startActivity(intent);
                    });
                    break;
                case "小红花":
                    menu.setIcon("ico_menu_xhh");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.flower);
                        activity.startActivity(intent);
                    });
                    break;
                case "运动睡眠":
                    menu.setIcon("ico_menu_ydsm");
                    break;
                case "通讯录":
                    menu.setIcon("ico_menu_txl");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.contacts);
                        activity.startActivity(intent);
                    });
                    break;
                case "宝贝信息":
                    menu.setIcon("ico_menu_bbxx");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.info);
                        activity.startActivity(intent);
                    });
                    break;

                case "其他":
                    break;

                case "家庭成员":
                    menu.setIcon("ico_menu_jtcy");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.member);
                        activity.startActivity(intent);
                    });
                    break;
                case "找手表":
                case "找终端":
                    menu.setIcon("ico_menu_zsb");
                    break;
                case "呼入限制":
                    menu.setIcon("ico_menu_hrxz");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.limit);
                        activity.startActivity(intent);
                    });
                    break;
                case "铃声设置":
                    menu.setIcon("ico_menu_lssz");
                    break;
                case "语音群聊":
                    menu.setIcon("ico_menu_yyql");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.voice);
                        activity.startActivity(intent);
                    });
                    break;
                case "路径查询":
                    menu.setIcon("ico_menu_ljcx");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.route);
                        activity.startActivity(intent);
                    });
                    break;
                case "软件设置":
                    menu.setIcon("ico_menu_xtsz");
                    menu.setRunnable(() -> {
                        Intent intent = new Intent();
                        intent.setClassName(activity, Route.setting);
                        activity.startActivity(intent);
                    });
                    break;
            }
        }
        return menus;
    }
}
