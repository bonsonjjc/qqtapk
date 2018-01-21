package com.bonson.qqtapk.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bonson.qqtapk.model.bean.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zjw on 2018/1/2.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);
//
//    @Query("select * from users where userId = :id")
//    User getById(String id);

    @Query("select * from users")
    User user();

    @Query("select * from users")
    List<User> users();

    @Query("select * from users limit 1")
    User userFirst();


    @Delete
    int delete(User user);
    @Delete
    void delete(List<User> users);
}
