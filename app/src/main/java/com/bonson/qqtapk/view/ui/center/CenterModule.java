package com.bonson.qqtapk.view.ui.center;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import java.util.ArrayList;
import java.util.List;

@Module
public abstract class CenterModule {
    @ActivityScope
    @Binds
    abstract UserViewModel viewModel(CenterViewModel centerViewModel);

    @ActivityScope
    @Provides
    static List<Message> providesCenter() {
        List<Message> messages = new ArrayList<>();
        String[] titles = {"定位记录", "监听记录", "区域告警", "电量提醒", "系统消息", "脱落告警", "交友消息"};
        String[] types = {"25", "18", "24", "19", "20", "61", "63"};
        String[] icons = {"ico_dsdw", "ico_jtjl", "ico_qygj", "ico_dl", "ico_xtxx", "ico_tlgj", "ico_hyxx"};
        for (int i = 0; i < titles.length; i++) {
            Message message = new Message();
            message.setTitle(titles[i]);
            message.setFtype(types[i]);
            message.setImg(icons[i]);
            message.setFshort("暂无消息");
            message.setFtext("暂无消息");
            messages.add(message);
        }
        return messages;
    }
}
