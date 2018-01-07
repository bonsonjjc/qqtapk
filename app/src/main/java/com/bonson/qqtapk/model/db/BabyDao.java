package com.bonson.qqtapk.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bonson.qqtapk.model.bean.Baby;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by zjw on 2018/1/2.
 */

@Dao
public interface BabyDao {
    @Insert
    Long insert(Baby baby);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertOrUpdate(Baby baby);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Baby... baby);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<Baby> baby);

    @Query("select * from babys where fid = :id")
    Baby getById(String id);

    @Query("select * from babys where fuser = :userId")
    Flowable<List<Baby>> getByUserId(String userId);

    @Update
    int update(Baby baby);

    @Delete()
    int delete(Baby baby);
}
