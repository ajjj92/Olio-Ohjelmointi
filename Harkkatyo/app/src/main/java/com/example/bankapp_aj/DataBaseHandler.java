package com.example.bankapp_aj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.EOFException;
import java.io.IOException;

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



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_ID+" TEXT, "+COLUMN_PASSWORD+" TEXT,"+COLUMN_USERBYTES+" blop)");

        //create admin user with the database
        User user2 = new User ("admin", "admin");
        Bank.getInstance().setActiveuser(user2);
        Bank.getInstance().getActiveuser().addAccountToUser(new DailyAccount(2000));
        Bank.getInstance().getActiveuser().addAccountToUser(new SavingAccount(2000));
        adminAdder(sqLiteDatabase, user2);
        Bank.getInstance().setActiveuser(null);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Method to add new users from admin menu.
    public boolean adminAdder(SQLiteDatabase sqLiteDatabase,User user) {
        byte [] userinbytes = new byte[0];
        try {
            userinbytes = ObjecttoByte(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, user.getName());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_USERBYTES, userinbytes);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATION", "Database data added.....");
        //returns -1 if incorrect
        if(result == -1) {
            return false;
        } else {
            return  true;
        }

    }

    //Method for normal data adding.
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
        //returns -1 if incorrect
        if(result == -1) {
            return false;
        } else {
            return  true;
        }
    }

    //Method for converting an user object to byte array
    public byte[] ObjecttoByte(User user) throws IOException {
        ByteArrayOutputStream out = null;
        ObjectOutputStream os = null;
        try{
            out = new ByteArrayOutputStream();
            os = new ObjectOutputStream(out);
            Log.e("ObjectOutputStream", os.toString());
            os.writeObject(user);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os != null) {
                try {
                    os.close();

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e("ObjecttoByte", "FINISHED");
        return out.toByteArray();
    }

    //Method for converting byte array back to user object
    public User byteToObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = null;
        ObjectInputStream is = null;
        Object o = null;
            try {
                in = new ByteArrayInputStream(data);
                is = new ObjectInputStream(in);
                Log.e("ObjectInputStream", is.toString());
                while((o = is.readObject()) != null) {
                    if(o instanceof User) {
                        return  (User) o;
                    }
                }

            } catch (EOFException ex) {
                ex.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (OptionalDataException e) {
                e.printStackTrace();

            }finally {
                //Checks to make sure stream is closed properly. Might not be needed.
                if(is != null) {
                    try {
                        is.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }



    //Method for updating user data.
    public int updateUserdata(String oldname, User userdata) throws IOException {
        String[] whereArgs = {oldname};
        SQLiteDatabase database = this.getWritableDatabase();
        User user = userdata;
        byte [] data = new byte[0];

        data = ObjecttoByte(user);
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, user.getName());
        cv.put(COLUMN_PASSWORD, user.getPassword());
        cv.put(COLUMN_USERBYTES, data);
        int i = database.update("userdata", cv, "username" + " = ?", whereArgs);
        Log.e("DATABASE OPERATION", "Database updated");
        //returns -1 if incorrect
        return i;
    }

    //Method incase need to show all database data.
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
        cursor.close();
        return datastring;
    }

    public void deleteUserData(String name) {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "Delete FROM "+TABLE_NAME+" WHERE " + COLUMN_ID+ "='" + name + "'";
        database.execSQL(sql);
        Log.e("DATABASE OPERATION", "Database deleted");
    }

    //Main data base query method. Creates a user object from the database when
    //searching with username and password.
    public void mainQuery(String name, String pass) {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE " + COLUMN_ID+ "='" + name + "' AND " + COLUMN_PASSWORD + "='" + pass+"'";
        Cursor cursor = (Cursor) database.rawQuery(sql, null);
        byte [] data = new byte[0];
        cursor.moveToFirst();
        Log.e("Cursor", String.valueOf(cursor.getColumnCount()));
        Log.e("Cursor", cursor.getColumnName(3));

        try {
            //Get and convert data
            data = cursor.getBlob(3);
            User user = byteToObject(data);
            //Set active user to converted object
            Bank.getInstance().setActiveuser(user);
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    //Method to fill userlist for admin page
    public void fillAdminlist() {
        Bank.getInstance().getUserlist().clear();
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = (Cursor) database.rawQuery(sql, null);
        byte [] data = new byte[0];
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.e("Cursor", String.valueOf(cursor.getColumnCount()));
            Log.e("Cursor", cursor.getColumnName(3));
            try {
                data = cursor.getBlob(3);
                User user = byteToObject(data);
                Bank.getInstance().getUserlist().add(user);
                cursor.moveToNext();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (CursorIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
    }

    //Method for querying data without password
    public void queryNopass(String name) {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE " + COLUMN_ID+ "='" + name + "'";
        Cursor cursor = (Cursor) database.rawQuery(sql, null);
        byte [] data = new byte[0];
        cursor.moveToFirst();
        Log.e("Cursor", String.valueOf(cursor.getColumnCount()));
        Log.e("Cursor", cursor.getColumnName(3));

        try {
            data = cursor.getBlob(3);
            User user = byteToObject(data);
            cursor.close();
            Bank.getInstance().setTempuser(user);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }
    //Not used currently
    public void deletedata() {
        String sql = "delete from "+ TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(sql);
        Log.e("DATABASE OPERATION", "Database deleted");

    }
}




