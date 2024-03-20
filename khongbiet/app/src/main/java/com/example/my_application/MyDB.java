package com.example.my_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {
    public static final String TableName = "ContactTable";
    public static final String Id = "Id";
    public static final String Name = "FullName";
    public static final String Phone = "Phonenumber";
    public static final String Images = "Images";
    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists " + TableName + "("

                + Id + " integer primary key,"
                + Name + " text,"
                + Phone + " text,"
                + Images + " text"
                + ")";
        db.execSQL(sqlCreate);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TableName);

        onCreate(db);
    }

    public ArrayList<Contact> getData() {
        ArrayList<Contact> list = new ArrayList<>();

        String sql = "Select * from " + TableName;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor!=null){
            while (cursor.moveToNext()){
                Contact contact = new Contact(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                list.add(contact);
            }
            //cursor.close();
        }
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String name = cursor.getString(1);
//                String phone = cursor.getString(2);
//                String image = cursor.getString(3);
//                Contact contact = new Contact(id, image, name, phone);
//                list.add(contact);
//            } while (cursor.moveToNext());
//        }

        return list;
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, contact.getId());
        value.put(Name, contact.getName());
        value.put(Phone, contact.getPhone());
        value.put(Images, contact.getImages());
        db.insert(TableName, null, value);
        db.close();
    }

    public void updateContact(int id ,Contact contact){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, contact.getId());
        value.put(Name, contact.getName());
        value.put(Phone, contact.getPhone());
        value.put(Images, contact.getImages());
        db.update(TableName, value, Id + "=" + id, null);
        db.close();
    }

    public void deleteContact(int id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Delete From " + TableName + " where " + Id + "=" + id;
        db.execSQL(sql);
        db.close();
    }
}
