package com.bonson.qqtapk.view.ui.center;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.view.adapter.CenterAdapter;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import java.util.ArrayList;
import java.util.List;

@Module
public abstract class CenterModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(CenterViewModel centerViewModel);

    @ActivityScope
    @Provides
    static List<Message> providesCenter() {
        List<Message> messages = new ArrayList<>();
        String[] titles = new String[]{"定位记录", "监听记录", "区域告警", "电量提醒", "系统消息", "脱落告警", "交友消息"};
        String[] types = new String[]{"1", "2", "3", "4", "5", "8", "9"};
        String[] icons = new String[]{
                "ico_dsdw", "ico_jtjl", "ico_qygj", "ico_dl", "ico_xtxx", "ico_tlgj", "ico_hyxx"
        };
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
