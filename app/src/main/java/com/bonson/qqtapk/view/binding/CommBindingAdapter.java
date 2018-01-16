package com.bonson.qqtapk.view.binding;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjw on 2018/1/3.
 */

public abstract class CommBindingAdapter {

    @BindingAdapter("android:icon")
    public static void setIcon(ImageView imageView, String img) {
        if (TextUtils.isEmpty(img))
            return;
        Resources resources = imageView.getResources();
        int resId = resources.getIdentifier(img, "drawable", imageView.getContext().getPackageName());
        imageView.setImageResource(resId);
    }

    @BindingAdapter("android:url")
    public static void setUrl(CircleImageView imageView, String url) {
//        if (TextUtils.isEmpty(url))
//            return;
//        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("android:border_color")
    public static void setBorderColor(CircleImageView imageView, ColorDrawable color) {
        imageView.setBorderColor(color.getColor());
    }

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }
}
