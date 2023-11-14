package com.esprit.rentacar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esprit.rentacar.model.Reservation;

import java.util.ArrayList;
import java.util.List;

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

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESERVATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Reservation reservation = new Reservation();
                reservation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                reservation.setDatePrise(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_PRISE)));
                reservation.setDateRemise(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REMISE)));
                reservation.setLieuPrise(cursor.getString(cursor.getColumnIndex(COLUMN_LIEU_PRISE)));
                reservation.setLieuRemise(cursor.getString(cursor.getColumnIndex(COLUMN_LIEU_REMISE)));

                reservations.add(reservation);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return reservations;
    }

    public Reservation getReservation(long reservationId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_RESERVATION,
                new String[]{COLUMN_ID, COLUMN_DATE_PRISE, COLUMN_DATE_REMISE, COLUMN_LIEU_PRISE, COLUMN_LIEU_REMISE},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(reservationId)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Reservation reservation = new Reservation();
        if (cursor != null) {
            reservation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
            reservation.setDatePrise(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_PRISE)));
            reservation.setDateRemise(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REMISE)));
            reservation.setLieuPrise(cursor.getString(cursor.getColumnIndex(COLUMN_LIEU_PRISE)));
            reservation.setLieuRemise(cursor.getString(cursor.getColumnIndex(COLUMN_LIEU_REMISE)));

            cursor.close();
        }

        return reservation;
    }

    // Méthode pour mettre à jour une réservation dans la base de données
    public boolean updateReservation(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE_PRISE, reservation.getDatePrise());
        values.put(COLUMN_DATE_REMISE, reservation.getDateRemise());
        values.put(COLUMN_LIEU_PRISE, reservation.getLieuPrise());
        values.put(COLUMN_LIEU_REMISE, reservation.getLieuRemise());

        // Mettez à jour la réservation
        int rowsAffected = db.update(TABLE_RESERVATION, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(reservation.getId())});

        return rowsAffected > 0;
    }
    public void supprimerReservation(long reservationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESERVATION, COLUMN_ID + " = ?", new String[]{String.valueOf(reservationId)});
        db.close();
    }

    public List<Reservation> getReservationsByLieuPrise(String lieuPrise) {
        List<Reservation> reservations = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESERVATION +
                " WHERE " + COLUMN_LIEU_PRISE + " LIKE '%" + lieuPrise + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Reservation reservation = new Reservation();
                reservation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                reservation.setDatePrise(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_PRISE)));
                reservation.setDateRemise(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REMISE)));
                reservation.setLieuPrise(cursor.getString(cursor.getColumnIndex(COLUMN_LIEU_PRISE)));
                reservation.setLieuRemise(cursor.getString(cursor.getColumnIndex(COLUMN_LIEU_REMISE)));

                reservations.add(reservation);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return reservations;
    }


}
