package com.bonson.qqtapk.utils.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

import com.bonson.fjqqt.model.bean.RouteTime;
import com.bonson.fjqqt.view.route.RouteTimeAdapter;
import com.bonson.fjqqt.view.terminal.alarm.Alarm;
import com.bonson.fjqqt.view.terminal.alarm.AlarmAdapter;
import com.bonson.fjqqt.view.terminal.timer.Timer;
import com.bonson.fjqqt.view.terminal.timer.TimerAdapter;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Contact;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.bean.Voice;
import com.bonson.qqtapk.view.ui.center.message.MessageAdapter;
import com.bonson.qqtapk.view.ui.index.BabyAdapter;
import com.bonson.qqtapk.view.ui.center.CenterAdapter;
import com.bonson.qqtapk.view.ui.contacts.ContactAdapter;
import com.bonson.qqtapk.view.ui.family.FamilyAdapter;
import com.bonson.qqtapk.view.ui.flower.FlowerAdapter;
import com.bonson.qqtapk.view.ui.lesson.LessonAdapter;
import com.bonson.qqtapk.view.ui.limits.LimitAdapter;
import com.bonson.qqtapk.view.ui.member.MemberAdapter;
import com.bonson.qqtapk.view.ui.index.MenuAdapter;
import com.bonson.qqtapk.view.ui.motion.TableAdapter;
import com.bonson.qqtapk.view.ui.motion.bean.Table;
import com.bonson.qqtapk.view.ui.setting.map.CityAdapter;
import com.bonson.qqtapk.view.ui.setting.map.DownloadAdapter;
import com.bonson.qqtapk.view.ui.setting.map.OfflineMap;
import com.bonson.qqtapk.view.ui.voice.VoiceAdapter;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectAdapter;
import com.bonson.qqtapk.view.widget.DividerItemDecoration;

import java.util.List;

/**
 * Created by zjw on 2018/1/5.
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter("android:divider")
    public static void setDivider(RecyclerView recyclerView, Drawable drawable) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable);
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @BindingAdapter("android:menus")
    public static void setMenus(RecyclerView recyclerView, List<Menu> menus) {
        MenuAdapter adapter = (MenuAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(menus);
    }

    @BindingAdapter("android:messages")
    public static void setMessages(RecyclerView recyclerView, List<Message> messages) {
        MessageAdapter adapter = (MessageAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(messages);
    }

    @BindingAdapter("android:families")
    public static void setFamilies(RecyclerView recyclerView, List<Family> families) {
        FamilyAdapter adapter = (FamilyAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(families);
    }

    @BindingAdapter("android:lessons")
    public static void setLessons(RecyclerView recyclerView, List<Lesson> lessons) {
        LessonAdapter adapter = (LessonAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(lessons);
    }

    @BindingAdapter("android:members")
    public static void setMembers(RecyclerView recyclerView, List<Member> members) {
        MemberAdapter adapter = (MemberAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(members);
    }

    @BindingAdapter("android:flowers")
    public static void setFlowers(RecyclerView recyclerView, List<Flower> flowers) {
        FlowerAdapter adapter = (FlowerAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(flowers);
    }

    @BindingAdapter("android:limits")
    public static void setLimits(RecyclerView recyclerView, List<Limit> limits) {
        LimitAdapter adapter = (LimitAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(limits);
    }

    @BindingAdapter("android:contacts")
    public static void setContacts(RecyclerView recyclerView, List<Contact> families) {
        ContactAdapter adapter = (ContactAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(families);
    }

    @BindingAdapter("android:selects")
    public static void setSelects(RecyclerView recyclerView, List<Select> selects) {
        SelectAdapter adapter = (SelectAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(selects);
    }

    @BindingAdapter("android:babies")
    public static void setBabies(RecyclerView recyclerView, List<Baby> babies) {
        BabyAdapter adapter = (BabyAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(babies);
    }

    @BindingAdapter("android:voices")
    public static void setVoices(RecyclerView recyclerView, List<Voice> voices) {
        VoiceAdapter adapter = (VoiceAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(voices);
    }

    @BindingAdapter("android:centers")
    public static void setCenters(RecyclerView recyclerView, List<Message> centers) {
        CenterAdapter adapter = (CenterAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(centers);
    }

    @BindingAdapter("android:times")
    public static void setTimes(RecyclerView recyclerView, List<RouteTime> centers) {
        RouteTimeAdapter adapter = (RouteTimeAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(centers);
    }

    @BindingAdapter("android:timers")
    public static void setTimers(RecyclerView recyclerView, List<Timer> timers) {
        TimerAdapter adapter = (TimerAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(timers);
    }

    @BindingAdapter("android:alarms")
    public static void setAlarms(RecyclerView recyclerView, List<Alarm> alarms) {
        AlarmAdapter adapter = (AlarmAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(alarms);
    }


    @BindingAdapter("android:tables")
    public static void setTables(RecyclerView recyclerView, List<Table> tables) {
        TableAdapter adapter = (TableAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(tables);
    }


    @BindingAdapter("android:cities")
    public static void setCities(RecyclerView recyclerView, List<OfflineMap> cities) {
        CityAdapter adapter = (CityAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(cities);
    }

    @BindingAdapter("android:downloads")
    public static void setDownloads(RecyclerView recyclerView, List<OfflineMap> downloads) {
        DownloadAdapter adapter = (DownloadAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(downloads);
    }
//
//    @BindingAdapter("android:downloads")
//    public static void setDownloads(RecyclerView recyclerView, List<MKOLSearchRecord> downloads) {
//        TableAdapter adapter = (TableAdapter) recyclerView.getAdapter();
//        recyclerView.setAdapter(adapter);
//        adapter.setBeans(downloads);
//    }
}
