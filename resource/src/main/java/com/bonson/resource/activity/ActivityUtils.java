package com.bonson.resource.activity;

import android.app.Activity;

import java.util.Stack;

public final class ActivityUtils {
    public static Stack<Activity> activities = new Stack<>();

    private ActivityUtils() {
    }

    public static void push(Activity activity) {
        activities.push(activity);
    }

    public static void pop(Activity activity) {
        activities.remove(activity);
    }

    public static Activity peek() {
        return activities.peek();
    }

    public static void clear() {
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            activities.get(i).finish();
        }
        activities.clear();
    }
}
