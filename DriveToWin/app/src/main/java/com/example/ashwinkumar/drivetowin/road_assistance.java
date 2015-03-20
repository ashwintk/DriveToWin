package com.example.ashwinkumar.drivetowin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import DatabaseAccessor.APIForSQLiteDB;
import DatabaseAccessor.Customer;
import DatabaseAccessor.EmergencyContacts;
import LibraryFunctions.CommunicateByTextEmail;
import LibraryFunctions.GeoLocation;


public class road_assistance extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_assistance);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_road_assistance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Call911BtnPressed(View view){
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel://911"));
        try {
            startActivity(phoneIntent);
            finish();
            Log.i("Info", "Finished making a call");
        } catch (android.content.ActivityNotFoundException ex) {
            Log.i("Error", "Call Failed");
        }
    }

    public void AlertBtnPressed(View view){
        APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
        List <EmergencyContacts> em_list = obj.getAllEmergencyContacts();
        Customer cust = obj.getCustomerInformation();
        GeoLocation gps = new GeoLocation(road_assistance.this);
        String message ="An alert has been sent by "+cust.Get_FIRST_NAME()
                +" to you. The current location of "+cust.Get_FIRST_NAME()+ " is ";
        if(gps.canGetLocation()){
            message=message.concat(" lat: "+String.valueOf(gps.getLatitude()));
            message=message.concat(" long: "+String.valueOf(gps.getLatitude()));
        }else{
            gps.showSettingsAlert();
        }
        gps.stopUsingGPS();
        message=message.concat(" ");
        String subject="Alert";
        CommunicateByTextEmail comm = new CommunicateByTextEmail();
        String recipients="";
        for(int i=0;i<em_list.size();i++){
            comm.sendText(em_list.get(i).Get_PHONE_NUMBER(),message);
            recipients+=em_list.get(i).Get_E_MAIL().trim()+";";
        }
        recipients=recipients.substring(0,recipients.length()-1);
        Intent email=comm.sendEmail(recipients,subject,message);
        startActivity(Intent.createChooser(email,"Choose an email client from the following"));
    }
}
