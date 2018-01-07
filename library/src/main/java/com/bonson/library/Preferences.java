package com.bonson.library;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zjw on 2017/12/22.
 */

public class Preferences {
  private SharedPreferences preferences;
  private SharedPreferences.Editor editor;

  private Preferences(Context context, String fileName) {
    preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
  }

  public Preferences put(String key, Object value) {
    if (value instanceof String) {
      editor.putString(key, (String) value);
    } else if (value instanceof Integer) {
      editor.putInt(key, (Integer) value);
    } else if (value instanceof Float) {
      editor.putFloat(key, (Float) value);
    } else if (value instanceof Long) {
      editor.putLong(key, (Long) value);
    } else if (value instanceof Boolean) {
      editor.putBoolean(key, (Boolean) value);
    } else {
      throw new RuntimeException("this type can't be save");
    }
    return this;
  }

  public Preferences commit() {
    editor.apply();
    return this;
  }

  public int get(String key, int defaultValue) {
    return preferences.getInt(key, defaultValue);
  }

  public String get(String key, String defaultValue) {
    return preferences.getString(key, defaultValue);
  }

  public long get(String key, long defaultValue) {
    return preferences.getLong(key, defaultValue);
  }

  public float get(String key, float defaultValue) {
    return preferences.getFloat(key, defaultValue);
  }

  public boolean get(String key, boolean defaultValue) {
    return preferences.getBoolean(key, defaultValue);
  }

  public static Preferences create(Context context, String fileName) {
    return new Preferences(context, fileName);
  }
}
