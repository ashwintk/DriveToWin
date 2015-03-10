package com.example.ashwinkumar.drivetowin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import DatabaseAccessor.APIForSQLiteDB;
import DatabaseAccessor.Customer;
import DatabaseAccessor.EmergencyContacts;
import DatabaseAccessor.StoreCustomerDataToServer;


public class user_registration_3 extends ActionBarActivity {
    private Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customer = (Customer) getIntent().getSerializableExtra("registration_screen_2");
        setContentView(R.layout.activity_user_registration_3);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_registration_3, menu);
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
    public void addEmergencyContacts(View view){
        Intent intent = new Intent(this, EmergencyContacts.class);
        startActivity(intent);
    }

    public void BackButtonPressed(View view){this.finish();}

    public void RegisterButtonPressed(View view){
        boolean flag= true;
        String message ="Please correct the following errors.";
        /*Get Entered Tag Number*/
        EditText text_box = (EditText) findViewById(R.id.car_tag_text);
        String temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_CAR_TAG(temp.trim());
        }else{
            flag=false;
            message=message.concat("Car Tag # should not be blank. ");
        }
        /*Get Entered Insurance Policy #*/
        text_box = (EditText) findViewById(R.id.policy_num_text);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_POLICY_NUMBER(temp.trim());
        }else{
            flag=false;
            message=message.concat("Insurance Policy Number should not be blank. ");
        }
        /*Get Entered Insurance Policy Claim Phone #*/
        text_box = (EditText) findViewById(R.id.policy_claim_phone_text);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_CLAIM_NUMBER(temp.trim());
        }else{
            flag=false;
            message=message.concat("Insurance Policy Claim Phone # should not be blank. ");
        }
        if(!isOnline()){
            flag=false;
            message=message.concat("No Internet Connectivity.");
        }
        if(!flag){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(message.trim())
                    .setCancelable(true)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else{
            APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
            obj.addCustomer(customer);
            new StoreCustomerDataToServer().execute(customer);
            new AlertDialog.Builder(this)
                    .setTitle("Success")
                    .setMessage("You have been successfully registered")
                    .setCancelable(true)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
            Customer cust = obj.getCustomerInformation();
            Toast.makeText(this.getApplicationContext(),cust.Get_FIRST_NAME()+","+cust.Get_LAST_NAME(),Toast.LENGTH_LONG).show();
        }
    }
    public boolean isOnline() {
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
