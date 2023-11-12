package com.esprit.rentacar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esprit.rentacar.model.Reservation;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "car_rental_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RESERVATION = "reservation";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE_PRISE = "date_prise";
    private static final String COLUMN_DATE_REMISE = "date_remise";
    private static final String COLUMN_LIEU_PRISE = "lieu_prise";
    private static final String COLUMN_LIEU_REMISE = "lieu_remise";

    private static final String CREATE_TABLE_RESERVATION = "CREATE TABLE " + TABLE_RESERVATION + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DATE_PRISE + " TEXT,"
            + COLUMN_DATE_REMISE + " TEXT,"
            + COLUMN_LIEU_PRISE + " TEXT,"
            + COLUMN_LIEU_REMISE + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESERVATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        onCreate(db);
    }

    public long addReservation(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE_PRISE, reservation.getDatePrise());
        values.put(COLUMN_DATE_REMISE, reservation.getDateRemise());
        values.put(COLUMN_LIEU_PRISE, reservation.getLieuPrise());
        values.put(COLUMN_LIEU_REMISE, reservation.getLieuRemise());

        long id = db.insert(TABLE_RESERVATION, null, values);
        db.close();
        return id;
    }
}
