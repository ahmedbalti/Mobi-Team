package com.esprit.rentacar.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esprit.rentacar.Model.Voiture;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "voitures.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VOITURES = "voitures";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_MODELE = "modele";

    private static final String CREATE_TABLE_VOITURES = "CREATE TABLE " + TABLE_VOITURES +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOM + " TEXT, " +
            COLUMN_MODELE + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VOITURES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOITURES);
        onCreate(db);
    }

    public void ajouterVoiture(Voiture voiture) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, voiture.getNom());
        values.put(COLUMN_MODELE, voiture.getModele());

        db.insert(TABLE_VOITURES, null, values);
        db.close();
    }

}
