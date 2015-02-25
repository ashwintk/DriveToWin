package DatabaseAccessor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ashwin Kumar on 25-02-2015.
 */
public class APIForSQLiteDB extends SQLiteOpenHelper{
    private static final String db_name="DriveToWinData";
    public APIForSQLiteDB(Context context){
        super(context,db_name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
