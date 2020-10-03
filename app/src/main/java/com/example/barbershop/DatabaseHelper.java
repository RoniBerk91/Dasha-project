package com.example.barbershop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "Clients.db";
    public static final String TABLE_NAME = "All_Clients";
    public static final String COL_1 = "IND";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PHONE";
    public static final String COL_4 = "DOG_NAME";
    public static final String COL_5 = "DATE";
    public static final String COL_6 = "PAYMENT";
    public static final String COL_7 = "DOG_STATUS";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table " + TABLE_NAME + "(IND INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT, PHONE TEXT, DOG_NAME TEXT, DATE TEXT, PAYMENT INTEGER, DOG_STATUS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNewClient(Client client)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = dateFormat.format(client.getDate().getTime());
        contentValues.put(COL_2, client.getName());
        contentValues.put(COL_3, client.getPhoneNumber());
        contentValues.put(COL_4, client.getDogName());
        contentValues.put(COL_5, date);
        contentValues.put(COL_6, client.getPayment());
        contentValues.put(COL_7, client.getDogStatus());
        long res = db.insert(TABLE_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }

    public boolean deleteClient(Client client)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String id = Integer.toString(client.getIndex());
        return db.delete(TABLE_NAME, "IND = ?", new String[] {id}) > 0;
    }

    public Cursor findByPhone(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_NAME + " WHERE PHONE = ?";
        return db.rawQuery(q, new String[] {phone});
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}







