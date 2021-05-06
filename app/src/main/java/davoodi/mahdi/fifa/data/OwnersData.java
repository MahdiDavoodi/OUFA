package davoodi.mahdi.fifa.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import davoodi.mahdi.fifa.components.Owner;

public class OwnersData extends SQLiteOpenHelper {


    private static final String DB_NAME = "owners-db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_OWNERS = "owners";
    private static final String CMD_CREATE = "CREATE TABLE IF NOT EXISTS '" + TABLE_OWNERS + "' " + "( '"
            + Owner.KEY_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '"
            + Owner.KEY_NAME + "' TEXT, '"
            + Owner.KEY_PASSWORD + "' INTEGER, '"
            + Owner.KEY_WEALTH + "' INTEGER, '"
            + Owner.KEY_WIN + "' INTEGER, '"
            + Owner.KEY_LOSS + "' INTEGER, '"
            + Owner.KEY_DRAW + "' INTEGER, '"
            + Owner.KEY_CUPS + "' INTEGER"
            + ")";

    public OwnersData(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CMD_CREATE);
        Log.i("database", "Table '" + TABLE_OWNERS + "' created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // We should get backups from old data.
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OWNERS);
        Log.i("database", "Table '" + TABLE_OWNERS + "' dropped!");
        onCreate(database);
        // We should restore database.
    }
}
