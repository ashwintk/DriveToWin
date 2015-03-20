package com.example.ashwinkumar.drivetowin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import DatabaseAccessor.APIForSQLiteDB;
import DatabaseAccessor.Customer;
import LibraryFunctions.CommunicateByTextEmail;


public class file_a_claim extends ActionBarActivity {
    private Customer cust;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_a_claim);
        APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
        cust=obj.getCustomerInformation();
        TextView v = (TextView) findViewById(R.id.policy_num_lbl);
        v.setText(cust.Get_POLICY_NUMBER());
        v = (TextView) findViewById(R.id.claim_phone_lbl);
        v.setText(cust.Get_CLAIM_NUMBER());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_a_claim, menu);
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
    public void CallRoadSideAssistancePressed(View view){
        CommunicateByTextEmail comm = new CommunicateByTextEmail();
        Intent phoneIntent = comm.callPhone("tel://"+cust.Get_CLAIM_NUMBER());
        try {
            startActivity(phoneIntent);
            finish();
            Log.i("Info", "Finished making a call");
        } catch (android.content.ActivityNotFoundException ex) {
            Log.i("Error", "Call Failed");
        }
    }
}
