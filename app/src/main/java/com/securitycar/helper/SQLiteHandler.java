package com.securitycar.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "secutity_car";

    // Login table name
    private static final String TABLE_USER = "user";
    // Login Table Columns names
    private static final String USER_ID = "id";
    private static final String USER_UID = "uid";
    private static final String USER_NOM = "nombre";
    private static final String USER_EMAIL = "email";
    private static final String USER_TEL = "tel";
    private static final String USER_STATUS = "status";
    private static final String USER_TOKEN = "token";
    private static final String USER_FECHA = "fecha";
    private static final String USER_PASS = "pass";

    private static final String TABLE_TUTO = "tuto";
    private static final String TUTO_ID = "tuto_id";
    private static final String TUTO_UID = "tuto_uid";
    private static final String TUTO_NOM = "tuto_nom";
    private static final String TUTO_STATUS = "tuto_status";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER
                + "("
                + USER_ID + " INTEGER PRIMARY KEY,"     //0
                + USER_UID + " TEXT,"                   //1
                + USER_NOM + " TEXT,"                   //2
                + USER_EMAIL + " TEXT,"                 //3
                + USER_TEL + " TEXT,"                   //4
                + USER_STATUS + " TEXT,"                //5
                + USER_TOKEN + " TEXT,"                 //6
                + USER_FECHA + " TEXT,"                 //7
                + USER_PASS + " TEXT"                  //8
                + ")";

        db.execSQL(CREATE_LOGIN_TABLE);
        Log.d(TAG, "Database tables created");
        Log.d("DataUser", "Database User created");

        String CREATE_TUTO_TABLE = "CREATE TABLE " + TABLE_TUTO
                + "("
                + TUTO_ID + " INTEGER PRIMARY KEY,"     //0
                + TUTO_UID + " TEXT,"                   //1
                + TUTO_NOM + " TEXT,"                   //2
                + TUTO_STATUS + " TEXT"                 //3
                + ")";

        db.execSQL(CREATE_TUTO_TABLE);
        Log.d(TAG, "Database tables created");
        Log.d("DataTuto", "Database tuto created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Create tables again
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(String uid, String nom, String email, String tel, String fecha, String status, String token, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_UID, uid);
        values.put(USER_NOM, nom); //
        values.put(USER_EMAIL, email); //;
        values.put(USER_TEL, tel);
        values.put(USER_STATUS, status);
        values.put(USER_TOKEN, token);
        values.put(USER_FECHA, fecha);
        values.put(USER_PASS, pass);
        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
        Log.d("AddUser", "Nuevo User Creado " + id);
    }

    public void addTuto(String idTuto, String nombre, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TUTO_UID, idTuto);
        values.put(TUTO_NOM, nombre);
        values.put(TUTO_STATUS, status);

        long id = db.insert(TABLE_TUTO, null, values);
        db.close(); // Closing database connection
        Log.d("AddTuto", "Nuevo tutorial creado " + id);

    };


    public void updateTuto(String idTuto, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = TUTO_ID +"="+ idTuto;
        ContentValues values = new ContentValues();
        values.put(TUTO_STATUS, status);

        long id = db.update(TABLE_TUTO,values, updateQuery, null);
        Log.d(TAG, "Update foto: " + id);

    }

    public HashMap<String, String> getUserDetalle() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            user.put("uid", cursor.getString(1));
            user.put("nombre", cursor.getString(2));
            user.put("email", cursor.getString(3));
            user.put("tel", cursor.getString(4));
            user.put("status", cursor.getString(5));
            user.put("token", cursor.getString(6));
            user.put("fecha", cursor.getString(7));
            user.put("pass", cursor.getString(8));

        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    public HashMap<String, String> getTuto(String idTuto) {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_TUTO + " WHERE " + TUTO_UID + " = " + idTuto;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("tuto_id", cursor.getString(1));
            user.put("tuto_nom", cursor.getString(2));
            user.put("tuto_status", cursor.getString(3));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching tuto from Sqlite: " + user.toString());

        return user;
    }


    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user info from sqlite");
    }

    public void deleteTuto() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TUTO, null, null);
        db.close();
        Log.d(TAG, "Deleted all tutoriales info from sqlite");
    }


}
