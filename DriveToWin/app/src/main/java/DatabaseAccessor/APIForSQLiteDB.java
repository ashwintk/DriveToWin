package DatabaseAccessor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.*;

public class APIForSQLiteDB extends SQLiteOpenHelper{
    private static final String db_name="DriveToWinData";
    public APIForSQLiteDB(Context context){
        super(context,db_name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table customer (device_id TEXT, first_name TEXT, last_name TEXT, "
        +"e_mail TEXT, password TEXT, phone_number TEXT, car_year INTEGER, car_make TEXT,"
        +"car_model TEXT, car_vin TEXT, car_tag TEXT, policy_num TEXT, claim_phone TEXT);";
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
        cv.put("policy_num",cust.Get_POLICY_NUMBER());
        cv.put("claim_phone",cust.Get_CLAIM_NUMBER());
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("customer",null,cv);
    }

    public long addEmergencyContact(EmergencyContacts em_Contact){
        ContentValues cv = new ContentValues();
        cv.put("name",em_Contact.Get_NAME());
        cv.put("e_mail",em_Contact.Get_E_MAIL());
        cv.put("phone_number",em_Contact.Get_PHONE_NUMBER());
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("emergency_contacts",null,cv);
    }

    public Customer getCustomerInformation(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor resultSet = database.rawQuery("Select * from customer",null);
        resultSet.moveToFirst();
        Customer obj = new Customer();
        obj.Set_DEVICE_ID(resultSet.getString(1));
        obj.Set_FIRST_NAME(resultSet.getString(2));
        obj.Set_LAST_NAME(resultSet.getString(3));
        obj.Set_E_MAIL(resultSet.getString(4));
        obj.Set_PASSWORD(resultSet.getString(5));
        obj.Set_PHONE_NUMBER(resultSet.getString(6));
        obj.Set_CAR_YEAR(resultSet.getString(7));
        obj.Set_CAR_MAKE(resultSet.getString(8));
        obj.Set_CAR_MODEL(resultSet.getString(9));
        obj.Set_VIN_NUMBER(resultSet.getString(10));
        obj.Set_CAR_TAG(resultSet.getString(11));
        obj.Set_POLICY_NUMBER(resultSet.getString(12));
        obj.Set_CLAIM_NUMBER(resultSet.getString(13));
        return obj;
    }

    public List<EmergencyContacts> getAllEmergencyContacts(){
        List<EmergencyContacts> em_list = new ArrayList<EmergencyContacts>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor resultSet = database.rawQuery("Select * from emergency_contacts",null);
        resultSet.moveToFirst();
        while(resultSet.moveToNext()){
            EmergencyContacts obj = new EmergencyContacts();
            obj.Set_NAME(resultSet.getString(1));
            obj.Set_E_MAIL(resultSet.getString(2));
            obj.Set_PHONE_NUMBER(resultSet.getString(3));
            em_list.add(obj);
        }
        return em_list;
    }
}
