package com.bonson.qqtapk.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zjw on 2018/1/2.
 */

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUer(User user);

    @Query("select * from users")
    User user();

    @Delete
    int deleteUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertBaby(Baby... baby);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertBaby(List<Baby> baby);

    @Delete
    int deleteBaby(Baby baby);

    @Query("select * from babys where fid = :id")
    Baby baby(String id);

    @Query("select * from babys order by fid asc")
    Flowable<List<Baby>> babyList();
}
