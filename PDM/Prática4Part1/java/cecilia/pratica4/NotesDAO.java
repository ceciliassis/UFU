package cecilia.pratica4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cecilia on 10/23/16.
 */
public class NotesDAO {
    private SQLiteDatabase db;
    private String[] columns = {CustomSQLiteOpenHelper.COLUMN_ID,
            CustomSQLiteOpenHelper.COLUMN_NOTES};
    private CustomSQLiteOpenHelper sqLiteOpenHelper;

    public NotesDAO(Context c) {
        sqLiteOpenHelper =
                new CustomSQLiteOpenHelper(c);
    }

    public void open() throws SQLException {
        db = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteOpenHelper.close();
    }

    public Note create(String note) {
        ContentValues values = new ContentValues();
        values.put(CustomSQLiteOpenHelper.COLUMN_NOTES, note);
        long insertId = db.insert(CustomSQLiteOpenHelper.TABLE_NOTES, null,
                values);


        Cursor c = db.query(CustomSQLiteOpenHelper.TABLE_NOTES, columns,
                CustomSQLiteOpenHelper.COLUMN_ID + "=" +
                        insertId, null, null, null, null);
        c.moveToFirst();

        Note newNote = new Note();
        newNote.setId(c.getLong(0));
        newNote.setNote(c.getString(1));
        c.close();
        return newNote;
    }

    public void delete(Note note) {
        long id = note.getId();
        db.delete(CustomSQLiteOpenHelper.TABLE_NOTES,
                CustomSQLiteOpenHelper.COLUMN_ID + '=' + id, null);

    }

    public List<Note> getAll() {
        List<Note> notes = new ArrayList<>();
        Cursor c = db.query(CustomSQLiteOpenHelper.TABLE_NOTES, columns,
                null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Note n = new Note();
            n.setId(c.getLong(0));
            n.setNote(c.getString(1));
            notes.add(n);
            c.moveToNext();
        }
        c.close();
        return notes;
    }

}
