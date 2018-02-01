package com.bonson.qqtapk.utils.binding;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Const;
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
    public static void setUrl(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url))
            return;
        Picasso.with(imageView.getContext())
                .load(Const.PATH + url)
                .error(R.drawable.ico_boy)
                .placeholder(R.drawable.ico_boy)
                .into(imageView);
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
