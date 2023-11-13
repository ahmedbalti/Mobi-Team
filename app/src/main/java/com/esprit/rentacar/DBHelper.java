package com.esprit.rentacar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DBNAME = "MyDB.db";
    private static final int DATABASE_VERSION = 1;

    static final String TABLE_NAME = "partsList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SERIALNUMBER = "serialNumber";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";

    DBHelper(Context context){
        super(context,DBNAME,null,DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (email TEXT primary key, password TEXT)");



                String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BRAND + " TEXT, " +
                COLUMN_YEAR + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SERIALNUMBER + " INTEGER, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRICE + " INTEGER);";
        MyDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(MyDB);

    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result==-1) {
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkuseremail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("Select * from users where email = ? ", new String[]{email});
        if (cursor.getCount()>0)
            return true;
            else
             return false;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from partsList", null);
        return cursor;
    }

    public Boolean authenticationAsAdmin(String email, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});

        if (cursor.moveToFirst()) {
            int userTypeIndex = cursor.getColumnIndex("user_type");

            // Check if the column exists in the cursor
            if (userTypeIndex != -1) {
                String userType = cursor.getString(userTypeIndex);

                // Check if the user is an admin
                if ("admin".equals(userType)) {
                    cursor.close();
                    return true;
                }
            }
        }

        cursor.close();
        return false;
    }










}
