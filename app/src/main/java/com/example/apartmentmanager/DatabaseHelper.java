package com.example.apartmentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "apartment.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_APARTMENTS = "apartments";
    
    private static final String COL_ID = "apartmentID";
    private static final String COL_UNIT = "unitNumber";
    private static final String COL_FOOTAGE = "squareFootage";
    private static final String COL_RENT = "rentAmount";
    private static final String COL_AIRBNB = "isAirBnb";
    private static final String COL_PETS = "allowsPets";
    private static final String COL_LOCATION = "location";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_APARTMENTS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_UNIT + " TEXT,"
                + COL_FOOTAGE + " REAL,"
                + COL_RENT + " REAL,"
                + COL_AIRBNB + " INTEGER,"
                + COL_PETS + " INTEGER,"
                + COL_LOCATION + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APARTMENTS);
        onCreate(db);
    }

    public long addApartment(Apartment apartment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_UNIT, apartment.getUnitNumber());
        values.put(COL_FOOTAGE, apartment.getSquareFootage());
        values.put(COL_RENT, apartment.getRentAmount());
        values.put(COL_AIRBNB, apartment.getIsAirBnb() ? 1 : 0);
        values.put(COL_PETS, apartment.getAllowsPets() ? 1 : 0);
        values.put(COL_LOCATION, apartment.getLocation());
        long id = db.insert(TABLE_APARTMENTS, null, values);
        db.close();
        return id;
    }

    public int updateApartment(Apartment apartment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_UNIT, apartment.getUnitNumber());
        values.put(COL_FOOTAGE, apartment.getSquareFootage());
        values.put(COL_RENT, apartment.getRentAmount());
        values.put(COL_AIRBNB, apartment.getIsAirBnb() ? 1 : 0);
        values.put(COL_PETS, apartment.getAllowsPets() ? 1 : 0);
        values.put(COL_LOCATION, apartment.getLocation());
        int rows = db.update(TABLE_APARTMENTS, values, COL_ID + "=?", 
                new String[]{String.valueOf(apartment.getApartmentID())});
        db.close();
        return rows;
    }

    public void deleteApartment(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APARTMENTS, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Apartment> getAllApartments() {
        List<Apartment> apartments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APARTMENTS, null);
        
        if (cursor.moveToFirst()) {
            do {
                Apartment apartment = new Apartment();
                apartment.setApartmentID(cursor.getInt(0));
                apartment.setUnitNumber(cursor.getString(1));
                apartment.setSquareFootage(cursor.getFloat(2));
                apartment.setRentAmount(cursor.getDouble(3));
                apartment.setIsAirBnb(cursor.getInt(4) == 1);
                apartment.setAllowsPets(cursor.getInt(5) == 1);
                apartment.setLocation(cursor.getString(6));
                apartments.add(apartment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return apartments;
    }

    public Apartment getApartment(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_APARTMENTS, null, COL_ID + "=?", 
                new String[]{String.valueOf(id)}, null, null, null);
        
        Apartment apartment = null;
        if (cursor != null && cursor.moveToFirst()) {
            apartment = new Apartment();
            apartment.setApartmentID(cursor.getInt(0));
            apartment.setUnitNumber(cursor.getString(1));
            apartment.setSquareFootage(cursor.getFloat(2));
            apartment.setRentAmount(cursor.getDouble(3));
            apartment.setIsAirBnb(cursor.getInt(4) == 1);
            apartment.setAllowsPets(cursor.getInt(5) == 1);
            apartment.setLocation(cursor.getString(6));
            cursor.close();
        }
        db.close();
        return apartment;
    }
}
