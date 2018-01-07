package com.bonson.qqtapk.view.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.model.bean.Contacts;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.qqtapk.view.adapter.ContactsAdapter;
import com.bonson.qqtapk.view.adapter.FamilyAdapter;
import com.bonson.qqtapk.view.adapter.FlowerAdapter;
import com.bonson.qqtapk.view.adapter.LessonAdapter;
import com.bonson.qqtapk.view.adapter.LimitAdapter;
import com.bonson.qqtapk.view.adapter.MemberAdapter;
import com.bonson.qqtapk.view.adapter.MenuAdapter;

import java.util.List;

/**
 * Created by zjw on 2018/1/5.
 */

public class RecyclerViewBindingAdapter {

    @BindingAdapter("android:menus")
    public static void setMenus(RecyclerView recyclerView, List<Menu> menus) {
        MenuAdapter menuAdapter = new MenuAdapter(recyclerView.getContext(), menus);
        recyclerView.setAdapter(menuAdapter);
    }

    @BindingAdapter("android:families")
    public static void setFamilies(RecyclerView recyclerView, List<Family> families) {
        FamilyAdapter menuAdapter = new FamilyAdapter(recyclerView.getContext(), families);
        recyclerView.setAdapter(menuAdapter);
    }

    @BindingAdapter("android:lessons")
    public static void setLessons(RecyclerView recyclerView, List<Lesson> lessons) {
        LessonAdapter menuAdapter = new LessonAdapter(recyclerView.getContext(), lessons);
        recyclerView.setAdapter(menuAdapter);
    }

    @BindingAdapter("android:members")
    public static void setMembers(RecyclerView recyclerView, List<Member> members) {
        MemberAdapter menuAdapter = new MemberAdapter(recyclerView.getContext(), members);
        recyclerView.setAdapter(menuAdapter);
    }

    @BindingAdapter("android:flowers")
    public static void setFlowers(RecyclerView recyclerView, List<Flower> flowers) {
        FlowerAdapter menuAdapter = new FlowerAdapter(recyclerView.getContext(), flowers);
        recyclerView.setAdapter(menuAdapter);
    }

    @BindingAdapter("android:limits")
    public static void setLimits(RecyclerView recyclerView, List<Limit> limits) {
        LimitAdapter menuAdapter = new LimitAdapter(recyclerView.getContext(), limits);
        recyclerView.setAdapter(menuAdapter);
    }

    @BindingAdapter("android:contacts")
    public static void setContacts(RecyclerView recyclerView, List<Contacts> families) {
        ContactsAdapter menuAdapter = new ContactsAdapter(recyclerView.getContext(), families);
        recyclerView.setAdapter(menuAdapter);
    }
}
