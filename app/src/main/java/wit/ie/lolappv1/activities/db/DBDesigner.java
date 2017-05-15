package wit.ie.lolappv1.activities.db;

/**
 * Created by laptop user on 03/04/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBDesigner extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;

//the DBContractClass contains all the static, permanent Strings we need, while this actually instantiates the DB using them
public DBDesigner(Context context) {
    super(context, DBContractClass.DB_NAME, null, DATABASE_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DBContractClass.SQL_CREATE_TABLE);
        Log.v("DBSTATUS", "The table was created successfully with the statement " + DBContractClass.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBDesigner.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DBContractClass.TABLE_NAME);
        onCreate(db);
    }
}
