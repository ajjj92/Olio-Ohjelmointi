package com.example.bankapp_aj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.EOFException;
import java.io.IOException;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;


/**
 * 7/12/19
 * AtteJantunen
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appdata.db";
    private static final String TABLE_NAME = "userdata";
    private static final String _id="_id";
    private static final String COLUMN_ID = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERBYTES = "objectbytes";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATION", "Database created / opened.....");
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_ID+" TEXT, "+COLUMN_PASSWORD+" TEXT,"+COLUMN_USERBYTES+" blop)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean addData(User user) {
        byte [] userinbytes = new byte[0];
        try {
            userinbytes = ObjecttoByte(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, user.getName());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_USERBYTES, userinbytes);
        long result = database.insert(TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATION", "Database data added.....");
        database.close();
        //returns -1 if incorrect
        if(result == -1) {
            return false;
        } else {
            return  true;
        }
    }




    public byte[] ObjecttoByte(User user) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(user);
        return out.toByteArray();
    }
    public User byteToObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        while (true) {
            try {
                User user = (User) is.readObject();
                return user;

            } catch (EOFException ex) {
                is.close();
                break;
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (OptionalDataException e) {
                e.printStackTrace();

            }
        }
            return null;
        }




    public User queryUserdata(String name, String pass) throws IOException, ClassNotFoundException {
        String sql = "SELECT * FROM userdata";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        byte [] data = new byte[0];

        while (!cursor.isAfterLast()) {
            if (cursor.getCount()>0) {
                data = cursor.getBlob(3);
                User user = byteToObject(data);
                if(cursor.getString(1).equals(name) && cursor.getString(2).equals(pass)) {

                    Bank.getInstance().setActiveuser_id(cursor.getColumnIndex("_id"));
                    Bank.getInstance().setActiveuser(user);
                    database.close();
                    return  user;


                }
            }

            cursor.moveToNext();
        }
        database.close();
        cursor.close();
        return null;
    }

    public int updateUserdata(String oldname) throws IOException {
        String[] whereArgs = {oldname};
        SQLiteDatabase database = this.getWritableDatabase();
        User user = Bank.getInstance().getActiveuser();
        byte [] data = new byte[0];

        data = ObjecttoByte(user);
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, Bank.getInstance().getActiveuser().getName());
        cv.put(COLUMN_PASSWORD, Bank.getInstance().getActiveuser().getPassword());
        cv.put(COLUMN_USERBYTES, data);
        int i = database.update("userdata", cv, "username" + " = ?", whereArgs);
        Log.e("DATABASE OPERATION", "Database updated");
        //returns -1 if incorrect
        database.close();
        return i;
    }


    public String getData() {
        String sql = "SELECT * FROM userdata";
        String datastring= "";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getCount()>0) {
                datastring += "\n"+cursor.getString(0)+" : "+cursor.getString(1)+" : "+cursor.getString(2)+"\n";
            }
            cursor.moveToNext();
        }
        database.close();
        return datastring;
    }
    public byte[] makeUserObjecttoBytes(User user) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(user);
            byte [] userbytes = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(userbytes);
            out.flush();
            return userbytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void deletedata() {
        String sql = "delete from "+ TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(sql);
        database.close();
        Log.e("DATABASE OPERATION", "Database deleted");

    }
}




