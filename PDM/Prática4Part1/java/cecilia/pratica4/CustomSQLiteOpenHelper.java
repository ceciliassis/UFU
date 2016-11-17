package cecilia.pratica4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cecilia on 10/23/16.
 */
public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NOTES = " notes";
    public static final String COLUMN_ID = " _id";
    public static final String COLUMN_NOTES = " note";
    public static final String DATABASE_NAME = " notes.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table" +
            TABLE_NOTES + "(" + COLUMN_ID +
            " integer primary key autoincrement," + COLUMN_NOTES +
            " text not null)";

    public CustomSQLiteOpenHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase d) {
        d.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NOTES);
        onCreate(db);
    }


}
