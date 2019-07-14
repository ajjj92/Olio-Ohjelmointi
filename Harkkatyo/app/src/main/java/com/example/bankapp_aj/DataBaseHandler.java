package com.example.bankapp_aj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;

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
            ByteArrayInputStream bais = new ByteArrayInputStream(userbytes);
            return userbytes;


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public User returnObject(byte[] data) {
        try {

            //convert byte array back to object
            ByteArrayInputStream baip = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(baip);
            User dataobj = (User ) ois.readObject();
            return dataobj;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> queryUserlist() throws IOException {
        String sql = "SELECT * from userdata";
        database = this.getReadableDatabase();
        ArrayList<User> userlist = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            byte[] data = cursor.getBlob(2);
            User user = returnObject(data);
            System.out.println(user.getPassword());
            cursor.moveToNext();

        }
        cursor.close();
        return userlist;

    }
}
