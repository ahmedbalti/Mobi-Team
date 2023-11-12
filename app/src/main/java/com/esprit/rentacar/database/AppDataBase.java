package com.esprit.rentacar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.esprit.rentacar.dao.PaymentDao;
import com.esprit.rentacar.entity.PaymentDetails;

@Database(entities = {PaymentDetails.class}, version = 2, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PaymentDao paymentDao();

    private static AppDataBase instance;
    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "rent_car_db")

                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE payment_details ADD COLUMN userId INTEGER NOT NULL");
        }
    };

}