package com.bonson.qqtapk.view.binding;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

/**
 * Created by zjw on 2018/1/3.
 */

public abstract class CommBindingAdapter {

    @BindingAdapter("android:src")
    public static void setIcon(ImageView imageView, String img) {
        if (TextUtils.isEmpty(img))
            return;
        Resources resources = imageView.getResources();
        int resId = resources.getIdentifier(img, "drawable", imageView.getContext().getPackageName());
        imageView.setImageResource(resId);
    }
}
