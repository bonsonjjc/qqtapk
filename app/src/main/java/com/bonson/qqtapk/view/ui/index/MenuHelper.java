package com.bonson.qqtapk.view.ui.index;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.resource.activity.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjw on 2018/1/3.
 */

public class MenuHelper {
    public static List<Menu> createMenu(BaseView view) {
        List<Menu> menus = new ArrayList<>();
        for (String name : Baby.baby.getFmenus().split(",")) {
            Menu menu = new Menu();
            menu.setName(name);
            menus.add(menu);
            if (Baby.baby.getFtag().equals("L08")) {
                setActon1(view, menu);
            } else {
                setActon2(view, menu);
            }
        }
        return menus;
    }
    private static void setActon2(BaseView view, Menu menu) {
        switch (menu.getName()) {
            case "亲情号码":
                menu.setIcon("ico_menu_qqhm");
                menu.setRunnable(() -> {
                    view.start(Route.family);
                });
                break;
            case "安全区域":
                menu.setIcon("ico_menu_aqqy");
                menu.setRunnable(() -> {
                    view.start(Route.area);
                });
                break;
            case "上课静默":
                menu.setIcon("ico_menu_skjm");
                menu.setRunnable(() -> {
                    view.start(Route.lesson);
                });
                break;
            case "定位模式":
                menu.setIcon("ico_menu_dwms");
                menu.setRunnable(() -> {
                    view.start(Route.mode);
                });
                break;
            case "通讯录":
                menu.setIcon("ico_menu_txl");
                menu.setRunnable(() -> {
                    view.start(Route.contacts);
                });
                break;
            case "终端信息":
                menu.setIcon("ico_menu_bbxx");
                menu.setRunnable(() -> {
                    view.start(Route.info);
                });
                break;
            case "终端设置":
                menu.setIcon("ico_menu_zdsz");
                menu.setRunnable(() -> {
                    view.start(Route.terminal);
                });
                break;
            case "呼入限制":
                menu.setIcon("ico_menu_hrxz");
                menu.setRunnable(() -> {
                    view.start(Route.limit);
                });
                break;
            case "路径查询":
                menu.setIcon("ico_menu_ljcx");
                menu.setRunnable(() -> {
                    view.start(Route.route2);
                });
                break;
            case "软件设置":
                menu.setIcon("ico_menu_xtsz");
                menu.setRunnable(() -> {
                    view.start(Route.setting);
                });
                break;
        }
    }
    private static void setActon1(BaseView view, Menu menu) {
        switch (menu.getName()) {
            case "消息中心":
                menu.setIcon("ico_menu_xxtx");
                menu.setRunnable(() -> {
                    view.start(Route.center);
                });
                break;
            case "亲情号码":
                menu.setIcon("ico_menu_qqhm");
                menu.setRunnable(() -> {
                    view.start(Route.family);

                });
                break;
            case "安全区域":
                menu.setIcon("ico_menu_aqqy");
                menu.setRunnable(() -> {
                    view.start(Route.area);
                });
                break;
            case "上课静默":
                menu.setIcon("ico_menu_skjm");
                menu.setRunnable(() -> {
                    view.start(Route.lesson);
                });
                break;
            case "定位模式":
                menu.setIcon("ico_menu_dwms");
                menu.setRunnable(() -> {
                    view.start(Route.mode);
                });
                break;
            case "小红花":
                menu.setIcon("ico_menu_xhh");
                menu.setRunnable(() -> {
                    view.start(Route.flower);
                });
                break;
            case "运动睡眠":
                menu.setIcon("ico_menu_ydsm");
                menu.setRunnable(() -> {
                    view.start(Route.motion);
                });
                break;
            case "通讯录":
                menu.setIcon("ico_menu_txl");
                menu.setRunnable(() -> {
                    view.start(Route.contacts);
                });
                break;
            case "宝贝信息":
                menu.setIcon("ico_menu_bbxx");
                menu.setRunnable(() -> {
                    view.start(Route.info);
                });
                break;

            case "其他":
                break;
            case "家庭成员":
                menu.setIcon("ico_menu_jtcy");
                menu.setRunnable(() -> {
                    view.start(Route.member);
                });
                break;
            case "找手表":
            case "找终端":
                menu.setIcon("ico_menu_zsb");
                break;
            case "呼入限制":
                menu.setIcon("ico_menu_hrxz");
                menu.setRunnable(() -> {
                    view.start(Route.limit);
                });
                break;
            case "铃声设置":
                menu.setIcon("ico_menu_lssz");
                menu.setRunnable(() -> {
                    view.start(Route.ring);
                });
                break;
            case "语音群聊":
                menu.setIcon("ico_menu_yyql");
                menu.setRunnable(() -> {
                    view.start(Route.voice);
                });
                break;
            case "路径查询":
                menu.setIcon("ico_menu_ljcx");
                menu.setRunnable(() -> {
                    view.start(Route.route);
                });
                break;
            case "软件设置":
                menu.setIcon("ico_menu_xtsz");
                menu.setRunnable(() -> {
                    view.start(Route.setting);
                });
                break;
        }
    }
}
