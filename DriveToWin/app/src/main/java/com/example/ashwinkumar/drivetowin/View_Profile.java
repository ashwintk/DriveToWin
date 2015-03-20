package com.example.ashwinkumar.drivetowin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import DatabaseAccessor.APIForSQLiteDB;
import DatabaseAccessor.Customer;
import DatabaseAccessor.DeleteAccount;


public class View_Profile extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
        Customer cust = obj.getCustomerInformation();
        TextView v1 = (TextView) findViewById(R.id.name_view_profile);
        v1.setText(cust.Get_FIRST_NAME()+" "+cust.Get_LAST_NAME());
        v1 = (TextView) findViewById(R.id.car_make_model_view_profile);
        v1.setText(cust.Get_CAR_MAKE()+" "+cust.Get_CAR_MODEL());
        v1 = (TextView) findViewById(R.id.vin_view_profile);
        v1.setText(cust.Get_VIN_NUMBER());
        v1 = (TextView) findViewById(R.id.phone_num_view_profile);
        v1.setText(cust.Get_PHONE_NUMBER());
        v1 = (TextView) findViewById(R.id.email_view_profile);
        v1.setText(cust.Get_E_MAIL());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__profile, menu);
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

    public void ChangePasswordButtonPressed(View view){
        Intent intent = new Intent(this,Change_Password.class);
        startActivity(intent);
    }

    public void DeleteAllData(){
        APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
        Customer cust = obj.getCustomerInformation();
        new DeleteAccount(cust.Get_PHONE_NUMBER()).execute();
        obj.deleteAllData();
    }
    public void DeactivateAccountBtnPressed(View view){
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Do you really want to delete your profile information?. This cannot be undone")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteAllData();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
