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

import DatabaseAccessor.APIForSQLiteDB;
import DatabaseAccessor.Customer;
import DatabaseAccessor.StoreCustomerDataToServer;
import LibraryFunctions.LibraryFunction;


public class Change_Password extends ActionBarActivity {
    private Customer cust;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
        cust=obj.getCustomerInformation();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
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
    public void CancelButtonPressed(View view){
        this.finish();
    }
    public void ChangePasswordBtnPressed(View view){
        boolean flag = true;
        String message = "Please correct the following errors.";
        EditText text_box;
         /*Get Entered Password*/
        text_box = (EditText) findViewById(R.id.old_password);
        String temp = text_box.getText().toString();
        if(temp.length()==0){
            flag=false;
            message=message.concat("Old Password should not be blank.");
        }
        String enc_old_pwd = new LibraryFunction().getHashedPwd(temp);
        if(!cust.Get_PASSWORD().equals(enc_old_pwd)){
            flag=false;
            message=message.concat("Old Password is incorrect. ");
        }
        text_box = (EditText) findViewById(R.id.new_password);
        temp = text_box.getText().toString();
        if(temp.length()==0){
            flag=false;
            message=message.concat("New password should not be blank.");
        }
        text_box = (EditText) findViewById(R.id.conf_new_password);
        String temp1 = text_box.getText().toString();
        if(temp1.length()==0){
            flag=false;
            message=message.concat("Confirm New password should not be blank.");
        }
        if(!temp.equals(temp1)){
            flag=false;
            message=message.concat("New Password & Confirm New Password do not match.");
        }
        String new_enc_pwd = new LibraryFunction().getHashedPwd(temp1);
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
            cust.Set_PASSWORD(new_enc_pwd);
            new StoreCustomerDataToServer().execute(cust);
            APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
            obj.updateCustomerPassword(cust);
        }
    }
}
