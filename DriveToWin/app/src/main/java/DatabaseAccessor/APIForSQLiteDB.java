package DatabaseAccessor;

import android.content.ContentValues;
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
        String query = "create table customer (device_id TEXT, first_name TEXT, last_name TEXT, "
        +"e_mail TEXT, password TEXT, phone_number TEXT, car_year INTEGER, car_make TEXT,"
        +"car_model TEXT, car_vin TEXT, car_tag TEXT);";
        db.execSQL(query);
        query ="create table emergency_contacts (name TEXT,e_mail TEXT,phone_number TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists emergency_contacts");
        onCreate(db);
    }

    public long addCustomer(Customer cust){
        ContentValues cv = new ContentValues();
        cv.put("device_id",cust.Get_DEVICE_ID());
        cv.put("first_name",cust.Get_FIRST_NAME());
        cv.put("last_name",cust.Get_LAST_NAME());
        cv.put("e_mail",cust.Get_E_MAIL());
        cv.put("password",cust.Get_PASSWORD());
        cv.put("phone_number",cust.Get_PHONE_NUMBER());
        cv.put("car_year",cust.Get_CAR_YEAR());
        cv.put("car_make",cust.Get_CAR_MAKE());
        cv.put("car_model",cust.Get_CAR_MODEL());
        cv.put("car_vin",cust.Get_VIN_NUMBER());
        cv.put("car_tag",cust.Get_CAR_TAG());
        SQLiteDatabase database = getWritableDatabase();
        long result = database.insert("customer",null,cv);
        return result;
    }

    public long addEmergencyContact(EmergencyContacts em_Contact){
        ContentValues cv = new ContentValues();
        cv.put("name",em_Contact.Get_NAME());
        cv.put("e_mail",em_Contact.Get_E_MAIL());
        cv.put("phone_number",em_Contact.Get_PHONE_NUMBER());
        SQLiteDatabase database = getWritableDatabase();
        long result = database.insert("emergency_contacts",null,cv);
        return result;
    }
}
