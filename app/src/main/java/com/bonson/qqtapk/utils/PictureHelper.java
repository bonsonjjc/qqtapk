package com.bonson.qqtapk.utils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.inject.Inject;

public final class PictureHelper {
    public static final int TAKE_PICTURE = 11;
    public static final int PHOTO = 12;
    public static final int CUT_PHOTO = 13;

    private File temp, file;

    @Inject
    public PictureHelper() {
        File sdCard = Environment.getExternalStorageDirectory();
        String path = sdCard.getPath() + "/ydqqt/pic";
        temp = new File(path);
        if (!temp.exists()) {
            boolean mkdirs = temp.mkdirs();
        }
        temp = new File(path, "take_picture.png");
        file = temp;
    }

    public File getFile() {
        return file;
    }

    public void takePicture(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(temp));
        activity.startActivityForResult(intent, TAKE_PICTURE);// 启动裁剪程序
    }

    public void photo(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, PictureHelper.PHOTO);
    }

    public void cropPicture(Activity activity) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(temp), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-date", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(intent, CUT_PHOTO);// 启动裁剪程序
    }

    public void path(Activity activity, Uri uri) {
        Cursor c = activity.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        if (c.moveToFirst()) {
            int columnIndex = c.getColumnIndex(MediaStore.Images.Media.DATA);
            String imagePath = c.getString(columnIndex);
            temp = new File(imagePath);
        }
        c.close();
    }

    public byte[] data(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public void release() {
        temp = null;
    }
}
