package com.esprit.rentacar.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esprit.rentacar.Model.Voiture;

import java.util.ArrayList;
import java.util.List;

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

    // Dans DBHelper.java
    public List<Voiture> getListeVoitures() {
        List<Voiture> listeVoitures = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VOITURES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Voiture voiture = new Voiture();
                voiture.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                voiture.setNom(cursor.getString(cursor.getColumnIndex(COLUMN_NOM)));
                voiture.setModele(cursor.getString(cursor.getColumnIndex(COLUMN_MODELE)));

                listeVoitures.add(voiture);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listeVoitures;
    }



    // Dans DBHelper.java
    public void modifierVoiture(Voiture voiture) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, voiture.getNom());
        values.put(COLUMN_MODELE, voiture.getModele());

        // Mettez à jour la voiture avec l'ID correspondant
        db.update(TABLE_VOITURES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(voiture.getId())});

        db.close();
    }

    // Dans DBHelper.java
    public void supprimerVoiture(int voitureId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VOITURES, COLUMN_ID + " = ?", new String[]{String.valueOf(voitureId)});
        db.close();
    }




}
