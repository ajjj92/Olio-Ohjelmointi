package com.example.bankapp_aj;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 7/12/19
 * AtteJantunen
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appdata.db";
    private static final String TABLE_NAME = "userdata";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERBYTES = "objectBytes";


    SQLiteDatabase database;

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+COLUMN_ID+" TEXT, "+COLUMN_PASSWORD+" TEXT,"+COLUMN_USERBYTES+" blop)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean addData(User user) {
        byte [] userinbytes = makeUserObjecttoBytes(user);
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, user.getName());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_USERBYTES, userinbytes);
        long result = database.insert(TABLE_NAME, null, contentValues);
        //returns -1 if incorrect

        if(result == -1) {
            return false;
        } else {
            return  true;
        }
    }

    public byte[] makeUserObjecttoBytes(User user) {
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(user);
            oos.flush();
            oos.close();
            byte [] userbytes = bos.toByteArray();
            return userbytes;


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public User returnUserObjectfromBytes(byte[] data) {
        try {
            ByteArrayInputStream baip = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(baip);
            User user = (User) ois.readObject();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
