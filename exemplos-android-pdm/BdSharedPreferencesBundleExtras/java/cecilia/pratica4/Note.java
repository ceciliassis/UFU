package cecilia.pratica4;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by cecilia on 10/23/16.
 */
public class Note {
    long id;
    String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
