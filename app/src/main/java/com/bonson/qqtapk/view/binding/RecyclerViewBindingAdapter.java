package com.bonson.qqtapk.view.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Contact;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.qqtapk.view.adapter.BabyAdapter;
import com.bonson.qqtapk.view.adapter.ContactAdapter;
import com.bonson.qqtapk.view.adapter.FamilyAdapter;
import com.bonson.qqtapk.view.adapter.FlowerAdapter;
import com.bonson.qqtapk.view.adapter.LessonAdapter;
import com.bonson.qqtapk.view.adapter.LimitAdapter;
import com.bonson.qqtapk.view.adapter.MemberAdapter;
import com.bonson.qqtapk.view.adapter.MenuAdapter;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectAdapter;

import java.util.List;

/**
 * Created by zjw on 2018/1/5.
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter("android:menus")
    public static void setMenus(RecyclerView recyclerView, List<Menu> menus) {
        MenuAdapter adapter = (MenuAdapter) recyclerView.getAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setBeans(menus);
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
}
