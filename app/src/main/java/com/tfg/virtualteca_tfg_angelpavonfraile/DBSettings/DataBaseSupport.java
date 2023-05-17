package com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseSupport extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Virtualteca.db";
    public static final String TABLE_BOOKS = "Libros";
    public static final String TABLE_PARTNERS = "Socios";
    public static final String TABLE_LOANS = "Prestamos";

    public DataBaseSupport(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    /**
     * Creates tables
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_BOOKS + "(" +
                "id_book INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "ISBN INTEGER NOT NULL," +
                "author TEXT NOT NULL," +
                "language TEXT NOT NULL," +
                "genre TEXT NOT NULL," +
                "editorial TEXT NOT NULL," +
                "pbl_date TEXT NOT NULL," +
                "synopsis TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PARTNERS + "(" +
                "id_partner INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT NOT NULL UNIQUE," +
                "name TEXT NOT NULL," +
                "surname1 TEXT NOT NULL," +
                "surname2 TEXT NOT NULL," +
                "phone_num TEXT NOT NULL," +
                "email TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LOANS + "(" +
                "id_loan INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_partner INTEGER NOT NULL," +
                "id_book INTEGER NOT NULL," +
                "init_date TEXT NOT NULL," +
                "fin_date TEXT NOT NULL," +
                "devuelto INTEGER NOT NULL," +
                "FOREIGN KEY(id_partner) REFERENCES " + TABLE_PARTNERS + "(id_partner)," +
                "FOREIGN KEY(id_book) REFERENCES " + TABLE_BOOKS + "(id_book))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOANS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTNERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(sqLiteDatabase);
    }

    public void clearTables() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_LOANS);
        db.execSQL("DELETE FROM " + TABLE_PARTNERS);
        db.execSQL("DELETE FROM " + TABLE_BOOKS);
    }

}
