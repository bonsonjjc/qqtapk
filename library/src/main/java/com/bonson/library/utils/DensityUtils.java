package com.bonson.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class DensityUtils {
  public static int dp(Resources resources, float px) {
    float dp =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.getDisplayMetrics());
    return (int) (dp + 0.5);
  }

  public static int dp(Context context, float px) {
    return dp(context.getResources(), px);
  }

  public static int width(Context context) {
    return context.getResources().getDisplayMetrics().widthPixels;
  }

  public static int height(Context context) {
    return context.getResources().getDisplayMetrics().heightPixels;
  }
}
