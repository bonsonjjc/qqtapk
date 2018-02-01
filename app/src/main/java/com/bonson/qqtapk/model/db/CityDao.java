package com.bonson.qqtapk.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bonson.qqtapk.model.bean.Area;

import java.util.ArrayList;
import java.util.List;

public class CityDao {
    private CitySQLiteHelper citySQLiteHelper;

    public CityDao(Context context) {
        citySQLiteHelper = new CitySQLiteHelper(context, "city.db", null, 1);
    }

    public int count(String id) {
        SQLiteDatabase readableDatabase = citySQLiteHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query("city", null, "tid=? or id=?", new String[]{id,"-1"}, null, null, "id");
        int count = cursor.getCount();
        cursor.close();
        readableDatabase.close();
        return count;
    }

    public List<Area> city(String id) {
        SQLiteDatabase readableDatabase = citySQLiteHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query("city", null, "tid=? or id=?", new String[]{id,"-1"}, null, null, "id");
        List<Area> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            list.add(parse(cursor));
        }
        cursor.close();
        readableDatabase.close();
        return list;
    }

    private Area parse(Cursor cursor) {
        Area area = new Area();
        for (String columnName : cursor.getColumnNames()) {
            int columnIndex = cursor.getColumnIndex(columnName);
            switch (columnName) {
                case "id":
                    area.setId(cursor.getString(columnIndex));
                    break;
                case "name":
                    area.setName(cursor.getString(columnIndex));
                    break;
                case "lev":
                    area.setLev(cursor.getString(columnIndex));
                    break;
                default:
                    area.setTid(cursor.getString(columnIndex));
                    break;
            }
        }
        return area;
    }
}
