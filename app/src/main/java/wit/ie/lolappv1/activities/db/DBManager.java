package wit.ie.lolappv1.activities.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.activities.db.DBContractClass;
import wit.ie.lolappv1.activities.db.DBDesigner;
import wit.ie.lolappv1.models.SingleGame;

/**
 * Created by laptop user on 03/04/2017.
 */

public class DBManager {
    private SQLiteDatabase database;
    private DBDesigner dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBDesigner(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void insert(SingleGame sg) {
        ContentValues values = new ContentValues();
        values.put(DBContractClass.COLUMN_NAME_MATCH_ID, sg.getMatchID());
        Log.v("BEFOREINSERT: ", values.get(DBContractClass.COLUMN_NAME_MATCH_ID).toString());
        values.put(DBContractClass.COLUMN_NAME_REGION, sg.getRegion());
        values.put(DBContractClass.COLUMN_NAME_HIGHEST_RANK, sg.getHighestRank());
        values.put(DBContractClass.COLUMN_NAME_MATCH_RESULT, sg.getMatchResult());
        values.put(DBContractClass.COLUMN_NAME_CHAMP_USED, sg.getChampUsed());

        long insertId = database.insert(DBContractClass.TABLE_NAME, null,
                values);
        Log.v("THE ID AFTER INSERT:", Long.toString(insertId));
    }

    public void delete(String id) {

        Log.v("DB", "Coffee deleted with id: " + id);
        database.delete(DBContractClass.TABLE_NAME,
                DBContractClass.COLUMN_NAME_MATCH_ID + " = " + id, null);
    }

    public void update(SingleGame pojo) {
        // TODO Auto-generated method stub

        ContentValues values = new ContentValues();
        values.put(DBContractClass.COLUMN_NAME_REGION, pojo.getRegion());
        values.put(DBContractClass.COLUMN_NAME_HIGHEST_RANK, pojo.getHighestRank());
        values.put(DBContractClass.COLUMN_NAME_MATCH_RESULT, pojo.getMatchResult());
        values.put(DBContractClass.COLUMN_NAME_CHAMP_USED, pojo.getChampUsed());


        long insertId = database
                .update(DBContractClass.TABLE_NAME,
                        values,
                        DBContractClass.COLUMN_NAME_MATCH_ID + " = "
                                + pojo.getMatchID(), null);

    }

    public void empty(){
    //database.execSQL(DBContractClass.CLEAR_TABLE);

    database.delete(DBContractClass.TABLE_NAME, null,null);

    }

    public List<SingleGame> getAll() {
        List<SingleGame> games = new ArrayList<SingleGame>();
        Cursor cursor = database.rawQuery("SELECT * FROM "
                + DBContractClass.TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SingleGame pojo = toGame(cursor);
            games.add(pojo);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return games;
    }

    public SingleGame get(String id) {
        SingleGame pojo = null;

        Cursor cursor = database.rawQuery("SELECT * FROM "
                + DBContractClass.TABLE_NAME + " WHERE "
                + DBContractClass.COLUMN_NAME_MATCH_ID + " = " + id, null);
        Log.v("COLUMNS IN CURSOR", Integer.toString(cursor.getColumnCount()));
        Log.v("ROWS IN CURSOR", Integer.toString(cursor.getCount()));
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {//I think this part might be evaluating to false, causing this whole bit to be skipped
            //but if this is always false, then there must be nothing to read in our Cursor...drat.
            SingleGame temp = toGame(cursor);
            Log.v("TEMP FROM CURSOR:",temp.matchID);
            pojo = temp;
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        //Log.v("JUSTAFTERGOTTEN",pojo.matchID); //--> THIS is null. 'pojo' is never properly retrieved from the db
        return pojo;
    }

    public SingleGame getGameByPosition(int pos){
        List<SingleGame> games = getAll();
        SingleGame pojo = games.get(pos);
        return pojo;
    }
    private SingleGame toGame(Cursor cursor) {
        SingleGame pojo = new SingleGame();
        pojo.matchID = cursor.getString(0);
        pojo.region = cursor.getString(1);
        pojo.highestRank = cursor.getString(2);
        pojo.matchResult = cursor.getString(3);
        pojo.champUsed = cursor.getString(4);
        return pojo;
    }
    }

