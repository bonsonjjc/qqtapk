package com.bonson.qqtapk.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bonson.library.utils.FileUtils;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zjw on 2018/1/2.
 */
@Database(entities = {Baby.class, User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();

    public static AppDataBase build(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "db").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                try {
                    InputStream inputStream = context.getAssets().open("db.db");
                    String path = Environment.getDataDirectory().getPath()+"/data/" + context.getPackageName() + "/databases/city.db";
                    FileUtils.copy(inputStream, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        }).allowMainThreadQueries().build();
    }
}
