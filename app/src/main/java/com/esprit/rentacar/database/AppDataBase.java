package com.esprit.rentacar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.esprit.rentacar.dao.FeedBackDao;
import com.esprit.rentacar.entity.FeedBackModel;


@Database(entities = {FeedBackModel.class}, version = 1 , exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract FeedBackDao feedBackDao();

    private static AppDataBase instance;

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "feedbackdb")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }





}
