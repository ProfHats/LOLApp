package wit.ie.lolappv1.activities.db;

import android.os.FileUriExposedException;
import android.provider.BaseColumns;

/**
 * Created by laptop user on 27/03/2017.
 */

public final class DBContractClass {
    private DBContractClass(){}
//not meant to be instantiated - every value is static final and you just access them through that
    //this is acceptable because none of this data is supposed to change, or if it does, then it should
    //only do so when I actually change it myself. The Database's structure isn't something that should
    //change at runtime :p


        public static final String DB_NAME = "uData.db";
    public static final String TABLE_NAME = "UserData";
        public static final String COLUMN_NAME_MATCH_ID = "MATCHID";
        public static final String COLUMN_NAME_REGION = "REGION";
    public static final String COLUMN_NAME_HIGHEST_RANK= "HIGHESTRANK";
        public static final String COLUMN_NAME_MATCH_RESULT= "MATCHRESULT";
        public static final String COLUMN_NAME_CHAMP_USED= "CHAMPUSED";
        public static final String CLEAR_TABLE="DELETE FROM " + TABLE_NAME;


    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_MATCH_ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_REGION + " TEXT," +
                    COLUMN_NAME_HIGHEST_RANK + " TEXT," +
                    COLUMN_NAME_MATCH_RESULT + " TEXT, " +
                    COLUMN_NAME_CHAMP_USED + " TEXT " +
                    ")";

    public static String insert(int id, String region, String highest_rank, String match_result, String champ_used) {
         String SQL_ADD_MATCH =
                "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME_MATCH_ID + ", " + COLUMN_NAME_REGION + ", " + COLUMN_NAME_HIGHEST_RANK + ", " + COLUMN_NAME_MATCH_RESULT + ", " + COLUMN_NAME_CHAMP_USED + ")" +
                        "VALUES " + " (" + id + ", " + region + ", " + highest_rank + ", " + match_result + ", " + champ_used + ");";
        return SQL_ADD_MATCH;
    }
    //This might be wrong, but I reckoned that this thing needed to have data passed into it in order to work (since it needs particular champ data) and thus could not be final.

}
