package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseOperations extends SQLiteOpenHelper {
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PHONE = "USER_PHONE";
    public static final String COLUMN_USER_DESCRIPTION = "USER_DESCRIPTION";
    public static final String COLUMN_ACTIVE_USER = "ACTIVE_USER";
    public static final String COLUMN_ID = "ID";

    public DataBaseOperations(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_PHONE + " TEXT, " + COLUMN_USER_DESCRIPTION + " TEXT, " + COLUMN_ACTIVE_USER + " BOOL)";

        db.execSQL(createTable);
    }
    //when it changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, customerModel.getName());
        cv.put(COLUMN_USER_PHONE, customerModel.getPhoneNumber());
        cv.put(COLUMN_USER_DESCRIPTION, customerModel.getDescription());
        cv.put(COLUMN_ACTIVE_USER, customerModel.isActive());

        long insert = db.insert(USER_TABLE,null , cv);
        if(insert==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public List<CustomerModel> getEveryone(){

        List<CustomerModel> returningListView = new ArrayList<>();

        //получи дата

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop
            do{
                int userId = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userPhone = cursor.getString(2);
                String userDescription = cursor.getString(3);
                boolean userActive = cursor.getInt(4) == 1 ? true: false;

                CustomerModel newUser = new CustomerModel(userId, userName, userPhone, userDescription, userActive);
                returningListView.add(newUser);
            }while(cursor.moveToNext());
        }
        else{
            //при провал
        }
        //затваряне на дб за да може да се полва пак
        cursor.close();
        db.close();
        return returningListView;
    }

    public boolean deleteOne(CustomerModel customerModel){
        //намери потребител в базата данни и го изтрии
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USER_TABLE + " WHERE "+ COLUMN_ID + " = " + customerModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

}
