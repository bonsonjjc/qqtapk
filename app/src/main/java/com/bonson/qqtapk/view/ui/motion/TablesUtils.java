package com.bonson.qqtapk.view.ui.motion;

import android.text.TextUtils;
import android.view.View;

import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Sleep;
import com.bonson.qqtapk.view.ui.motion.bean.Table;


public class TablesUtils {
    public static Table motion(Motion motion) {
        Table table = new Table();
        table.cell0_0.setValue1("全程里程");
        table.cell0_0.setValue2(motion.getFconsume() + "公里");
        table.cell0_0.setValue3(null);

        table.cell0_1.setValue1("全天步数");
        table.cell0_1.setValue2(motion.getFstepsNumber() + "步");
        table.cell0_1.setValue3(null);

        table.cell0_2.setValue1("全程消耗");
        table.cell0_2.setValue2(motion.getFdistance() + "千卡");
        table.cell0_2.setValue3(null);


        table.cell1_0.setValue1("行走里程");
        table.cell1_0.setValue2(motion.getFwalkmile() + "公里");
        table.cell1_0.setValue3(null);

        table.cell1_1.setValue1("行走时长");
        table.cell1_1.setValue2(motion.getFwalkingTimeLong() + "分");
        table.cell1_1.setValue3(null);

        table.cell1_2.setValue1("行走消耗");
        table.cell1_2.setValue2(motion.getFwalkcal() + "千卡");
        table.cell1_2.setValue3(null);


        table.cell2_0.setValue1("跑步里程");
        table.cell2_0.setValue2(motion.getFrunmile() + "公里");
        table.cell2_0.setValue3(null);

        table.cell2_1.setValue1("跑步时长");
        table.cell2_1.setValue2(motion.getFrunTimeLong() + "分");
        table.cell2_1.setValue3(null);

        table.cell2_2.setValue1("跑步消耗");
        table.cell2_2.setValue2(motion.getFrunkcal() + "千卡");
        table.cell2_2.setValue3(null);
        return table;
    }

    private static String[] heart = {"--", "--", "--"};

    public static String[] parseHeart(String result) {
        if (TextUtils.isEmpty(result)) {
            return heart;
        }
        return heart;
    }

    public static Table sleep(Sleep sleep) {
        Table table = new Table();

        table.cell0_0.setValue1("睡眠时长");
        table.cell0_0.setValue2(sleep.getFyestreenSL());
        table.cell0_0.setValue3(null);

        table.cell0_1.setValue1("深睡时长");
        table.cell0_1.setValue2(sleep.getFdeep());
        table.cell0_1.setValue3(null);

        table.cell0_2.setValue1("浅睡时长");
        table.cell0_2.setValue2(sleep.getFshallow());
        table.cell0_2.setValue3(null);


        table.cell1_0.setValue1("入睡时间");
        table.cell1_0.setValue2(sleep.getFsleepTime());
        table.cell1_0.setValue3(null);

        table.cell1_1.setValue1("醒来时间");
        table.cell1_1.setValue2(sleep.getFwakeTime());
        table.cell1_1.setValue3(null);

        table.cell1_2.setValue1("清醒时长");
        table.cell1_2.setValue2(sleep.getFwakeLength());
        table.cell1_2.setValue3(null);

        table.cell2_0.setVisible(View.GONE);
        table.cell2_1.setVisible(View.GONE);
        table.cell2_2.setVisible(View.GONE);
        return table;
    }
}
