package com.example.ashwinkumar.drivetowin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import DatabaseAccessor.APIForSQLiteDB;
import DatabaseAccessor.EmergencyContacts;
import DatabaseAccessor.StoreCustomerDataToServer;
import DatabaseAccessor.StoreEmergencyContactToServer;
import LibraryFunctions.LibraryFunction;


public class add_emergency_contacts extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency_contacts);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_emergency_contacts, menu);
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
        if(id==R.id.add_emergency_contact){

        }
        return super.onOptionsItemSelected(item);
    }
    public void EmergencyContactCancelBtnPressed(View v){finish();}
    public void EmergencyContactAddBtnPressed(View view){
        EmergencyContacts em_contact = new EmergencyContacts();
        boolean flag = true;
        String message = "Please correct the following errors.";
        /*Get Entered Name*/
        EditText text_box = (EditText) findViewById(R.id.add_emergency_contact_name);
        String temp = text_box.getText().toString();
        if(temp.length()!=0){
            em_contact.Set_NAME(temp.trim());
        }else{
            flag=false;
            message=message.concat("Name should not be blank. ");
        }
        /*Get Entered E-Mail*/
        text_box = (EditText) findViewById(R.id.add_emergency_contact_email);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            em_contact.Set_E_MAIL(temp.trim());
        }else{
            flag=false;
            message=message.concat("E-mail should not be blank. ");
        }
        /*Get Entered Phone Number*/
        text_box = (EditText) findViewById(R.id.add_emergency_contact_phone);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            em_contact.Set_PHONE_NUMBER(temp.trim());
        }else{
            flag=false;
            message=message.concat("Phone Number should not be blank. ");
        }
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(!new LibraryFunction().isOnline(cm)){
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
            obj.addEmergencyContact(em_contact);
            new StoreEmergencyContactToServer(this.getApplicationContext()).execute(em_contact);
            new AlertDialog.Builder(this)
                    .setTitle("Success")
                    .setMessage("Your contact has been added successfully")
                    .setCancelable(true)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
    }
}
