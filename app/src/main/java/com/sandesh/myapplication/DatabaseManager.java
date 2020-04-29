package com.sandesh.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "studentdb";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "student";
    private static final String COLUMN_ROLLNO = "rollno";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SCORE = "score";


    DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
                "    " + COLUMN_ROLLNO + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_SCORE + "  varchar(200) NOT NULL\n" +
                ");";


        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }


    boolean addEmployee(String rollno, String name, String score) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ROLLNO, rollno);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SCORE, score);

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }


    Cursor getAllEmployees() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean dididExist(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] allColumns = { COLUMN_ROLLNO };
        Cursor cursor = db.query(TABLE_NAME, allColumns,
                COLUMN_ROLLNO + " = " + id, null, null, null,
                null);
        cursor.moveToFirst();
        boolean retBool = false;
        if (!cursor.isAfterLast()) {

            retBool = true;

        }
        return retBool;
    }

    boolean updateEmployee(int id, String rollno, String name, String score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ROLLNO, rollno);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SCORE, score);
        return db.update(TABLE_NAME, contentValues, COLUMN_ROLLNO + "=?", new String[]{String.valueOf(id)}) == 1;
    }


    boolean deleteEmployee(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ROLLNO + "=?", new String[]{String.valueOf(id)}) == 1;
    }
}