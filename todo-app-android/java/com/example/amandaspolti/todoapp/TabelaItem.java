package com.example.amandaspolti.todoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe resposavel pela criação da tabela de itens
 */

public class TabelaItem extends SQLiteOpenHelper {
    public static final String TABLE_ITENS = "itens";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEM = "item";
    public static final String COLUMN_DUEDATE = "dueDate";
    public static final String COLUMN_DONE = "done";
    public static final String COLUMN_NIVEL = "nivel";


    public static final String DATABASE_NAME = " itens.db";
    public static final int DATABASE_VERSION = 1;

    private static TabelaItem ourInstance;

    public static TabelaItem getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new TabelaItem(context);
        return ourInstance;
    }

    private TabelaItem(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public final String SQL = "CREATE TABLE " + TABLE_ITENS + " ( " + COLUMN_ID +
            " integer primary key autoincrement, " + COLUMN_ITEM + " text not null, " + COLUMN_NIVEL
            + " integer not null, " + COLUMN_DUEDATE + " text, " + COLUMN_DONE + " BOOLEAN )";

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase d) {
        d.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

    public SQLiteDatabase open() {
        return ourInstance.getWritableDatabase();
    }

    public void close() {
        ourInstance.close();
    }

}
