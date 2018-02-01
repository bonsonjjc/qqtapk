package com.bonson.qqtapk.view.ui.index;

import com.bonson.fjqqt.Path;
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
    public static List<Menu> createMenu(String menustr,String tag) {
        List<Menu> menus = new ArrayList<>();
        for (String name : menustr.split(",")) {
            Menu menu = new Menu();
            menu.setName(name);
            menus.add(menu);
            if (tag.equals("L08")) {
                setActon1(menu);
            } else {
                setActon2(menu);
            }
        }
        return menus;
    }

    private static void setActon2(Menu menu) {
        switch (menu.getName()) {
            case "亲情号码":
                menu.setIcon("ico_menu_qqhm");
                menu.setAction(Path.family);
                break;
            case "安全区域":
                menu.setIcon("ico_menu_aqqy");
                menu.setAction(Path.area);
                break;
            case "上课静默":
                menu.setIcon("ico_menu_skjm");
                menu.setAction(Path.lesson);
                break;
            case "定位模式":
                menu.setIcon("ico_menu_dwms");
                menu.setAction(Route.mode);
                break;
            case "终端信息":
                menu.setIcon("ico_menu_bbxx");
                menu.setAction(Route.info);
                break;
            case "终端设置":
                menu.setIcon("ico_menu_zdsz");
                menu.setAction(Path.terminal);
                break;
            case "呼入限制":
                menu.setIcon("ico_menu_hrxz");
                menu.setAction(Route.limit);
                break;
            case "路径查询":
                menu.setIcon("ico_menu_ljcx");
                menu.setAction(Path.route);
                break;
            case "软件设置":
                menu.setIcon("ico_menu_xtsz");
                menu.setAction(Route.setting);
                break;
        }
    }

    private static void setActon1(Menu menu) {
        switch (menu.getName()) {
            case "消息中心":
                menu.setIcon("ico_menu_xxtx");
                menu.setAction(Route.center);
                break;
            case "亲情号码":
                menu.setIcon("ico_menu_qqhm");
                menu.setAction(Route.family);
                break;
            case "安全区域":
                menu.setIcon("ico_menu_aqqy");
                menu.setAction(Route.area);
                break;
            case "上课静默":
                menu.setIcon("ico_menu_skjm");
                menu.setAction(Route.lesson);
                break;
            case "定位模式":
                menu.setIcon("ico_menu_dwms");
                menu.setAction(Route.mode);
                break;
            case "小红花":
                menu.setIcon("ico_menu_xhh");
                menu.setAction(Route.flower);
                break;
            case "运动睡眠":
                menu.setIcon("ico_menu_ydsm");
                menu.setAction(Route.motion);
                break;
            case "通讯录":
                menu.setIcon("ico_menu_txl");
                menu.setAction(Route.contacts);
                break;
            case "宝贝信息":
                menu.setIcon("ico_menu_bbxx");
                menu.setAction(Route.info);
                break;

            case "其他":
                break;
            case "家庭成员":
                menu.setIcon("ico_menu_jtcy");
                menu.setAction(Route.member);
                break;
            case "找手表":
            case "找终端":
                menu.setIcon("ico_menu_zsb");
                break;
            case "呼入限制":
                menu.setIcon("ico_menu_hrxz");
                menu.setAction(Route.limit);
                break;
            case "铃声设置":
                menu.setIcon("ico_menu_lssz");
                menu.setAction(Route.ring);
                break;
            case "语音群聊":
                menu.setIcon("ico_menu_yyql");
                menu.setAction(Route.voice);
                break;
            case "路径查询":
                menu.setIcon("ico_menu_ljcx");
                menu.setAction(Route.route);
                break;
            case "软件设置":
                menu.setIcon("ico_menu_xtsz");
                menu.setAction(Route.setting);
                break;
        }
    }
}
