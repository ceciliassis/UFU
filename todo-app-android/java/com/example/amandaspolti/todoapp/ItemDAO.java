package com.example.amandaspolti.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

/**
 * Created by amandaspolti on 01/12/16.
 */
public class ItemDAO{
    private SQLiteDatabase db;
    private String[] columns = {TabelaItem.COLUMN_ID, TabelaItem.COLUMN_ITEM,
            TabelaItem.COLUMN_NIVEL, TabelaItem.COLUMN_DUEDATE, TabelaItem.COLUMN_DONE};

    private TabelaItem mTIHelper;

    private static ItemDAO ourInstance;

    public static ItemDAO getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new ItemDAO(context);
        return ourInstance;
    }

    private ItemDAO(Context c) {
        mTIHelper = TabelaItem.getInstance(c);
        db = mTIHelper.open();
    }

    public long create(String text, String dueDate, int nivel) {
        ContentValues values = new ContentValues();

        values.put(TabelaItem.COLUMN_ITEM, text);
        values.put(TabelaItem.COLUMN_NIVEL, nivel);
        values.put(TabelaItem.COLUMN_DUEDATE, dueDate);
        values.put(TabelaItem.COLUMN_DONE, false);

        return db.insert(TabelaItem.TABLE_ITENS, null, values);
    }

    public int delete(Item item) {
        long id = item.get_id();
       return db.delete(TabelaItem.TABLE_ITENS,
                TabelaItem.COLUMN_ID + " = ?" ,new String[] {Long.toString(id)} );
    }

    public int update(Item item) {// retorna 1 se achou, 0 caso nao
        ContentValues values = new ContentValues();

        long id = item.get_id();

        values.put(TabelaItem.COLUMN_ITEM, item.getText());
        values.put(TabelaItem.COLUMN_NIVEL, item.getNivel());
        values.put(TabelaItem.COLUMN_DUEDATE, item.getDueDate());
        values.put(TabelaItem.COLUMN_DONE, item.isDone());

        String[] whereargs = new String[] {String.valueOf(id)};

        return db.update(TabelaItem.TABLE_ITENS, values, TabelaItem.COLUMN_ID + " = ?", whereargs);

    }

    public List<String> getAll() {
        List<String> itens = new ArrayList<>();
        Cursor c = db.query(TabelaItem.TABLE_ITENS, columns,
                null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String data = c.getString(3);
            data = data.equals("") ? "null" : data;

            String item = Long.toString(c.getLong(0)) + "||" + c.getString(1) + "||" + c.getInt(2)
                    + "||" + data + "||" + c.getInt(4);

            itens.add(item);
            c.moveToNext();
        }
        c.close();
        return itens;
    }

    public List<String> getData(){
        List<String> itens = new ArrayList<>();
        Cursor c = db.query(TabelaItem.TABLE_ITENS, columns,
                null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String data = c.getString(3);
            itens.add(data);
            c.moveToNext();
        }
        c.close();
        return itens;
    }
}

