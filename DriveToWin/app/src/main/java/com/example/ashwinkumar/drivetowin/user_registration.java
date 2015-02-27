package com.example.ashwinkumar.drivetowin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.security.MessageDigest;

import DatabaseAccessor.*;

public class user_registration extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_registration, menu);
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
    /*Button action when cancel button is pressed*/
    public void CancelButtonPressed(View v){
        finish();
    }
    /*Button action when next button is pressed*/
    public void NextButtonPressed_Registration_1(View v){
        Customer cust = new Customer();
        boolean flag= false;
        String message ="Please correct the following errors.";
        /*Get Entered First Name*/
        EditText text_box = (EditText) findViewById(R.id.first_name_txt);
        String temp = text_box.getText().toString();
        if(temp.length()!=0){
            cust.Set_FIRST_NAME(temp.trim());
        }else{
            flag=false;
            message=message.concat("First Name should not be blank. ");
        }
        /*Get Entered Last Name*/
        text_box = (EditText) findViewById(R.id.last_name_txt);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            cust.Set_LAST_NAME(temp.trim());
        }else{
            flag=false;
            message=message.concat("Last Name should not be blank. ");
        }
        /*Get Entered E-mail Address*/
        text_box = (EditText) findViewById(R.id.email_txt);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            cust.Set_E_MAIL(temp.trim());
        }else{
            flag=false;
            message=message.concat("E-mail should not be blank. ");
        }
        /*Get Entered Password*/
        text_box = (EditText) findViewById(R.id.password_txt);
        temp = text_box.getText().toString();
        /*Get Confirm Password*/
        text_box = (EditText) findViewById(R.id.conf_pwd_text);
        String temp_1 = text_box.getText().toString();
        /*Encrypt the password*/
        String passwd_enc="";
        try{
            byte[] bytesOfMessage = temp.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            passwd_enc=thedigest.toString();
        }catch(Exception e) {
            passwd_enc = null;
        }
        if(temp_1.equals(temp)){//Check if the passwords are the same
            if(passwd_enc.length()!=0){
                cust.Set_PASSWORD(passwd_enc.trim());
            }else{
                flag=false;
                message=message.concat("Password should not be blank.");
            }
        }else{
            flag=false;
            message=message.concat("Password & Confirm Password do not match.");
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
            Intent intent = new Intent(this, user_registration_2.class);
            startActivity(intent);
        }
    }
}
