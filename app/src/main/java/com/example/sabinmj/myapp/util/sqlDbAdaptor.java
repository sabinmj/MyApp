package com.example.sabinmj.myapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqlDbAdaptor {

    public static final String KEY_ROWID = "_id"; //key for id
    public static final String KEY_NAME = "name";  //key for name
    public static final String KEY_DISCRIPTION = "dis";
    public static final String KEY_PRICE = "price";//key for e mail
    public static final String KEY_QUANTITY = "quantity";
    private static final String TAG = "DBAdapter1";
    private static final String DATABASE_NAME = "MyDBs12";  //database name
    private static final String DATABASE_TABLE = "store";  //table name
    private static final int DATABASE_VERSION = 2;
    /* private static final String DATABASE_CREATE =
             "create table contacts (_id integer primary key autoincrement, "
                     + "name text not null, email text not null);";//table creation*/
    private static final String TABLE_CREATE_QUERY = "CREATE TABLE IF NOT EXISTS " +DATABASE_TABLE + " ( "+
            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT,  " + KEY_DISCRIPTION + " TEXT,  " + KEY_PRICE + " TEXT,  " + KEY_QUANTITY+" TEXT)";




    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public sqlDbAdaptor(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(TABLE_CREATE_QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS store");
            onCreate(db);
        }
    }


    //---opens the database---
    public sqlDbAdaptor open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


    //---insert a contact into the database---
    public long insertContact(String name, String discr, String price, String qua)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_DISCRIPTION, discr);
        initialValues.put(KEY_PRICE, price);
        initialValues.put(KEY_QUANTITY, qua);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }


    //---deletes a particular contact---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }


    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,KEY_DISCRIPTION},
                null, null, null, null, null);
    }


    //---retrieves a particular contact---
    public Cursor getContact(String rowId) throws SQLException
    {
        /*Cursor mCursor =db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                        KEY_NAME, KEY_EMAIL}, KEY_NAME + "=?" + rowId, null,
                null, null, null, null);*/
        Cursor mCursor = db.rawQuery("SELECT _id, name, dis, price ,quantity FROM "+DATABASE_TABLE+" WHERE Name like '"+rowId+"'", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



    //---updates a contact---
    public boolean updateContact(long rowId, String name, String dis, String price, String quant)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_DISCRIPTION, dis);
        args.put(KEY_PRICE, price);
        args.put(KEY_QUANTITY, quant);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


}
