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

import DatabaseAccessor.Customer;


public class user_registration_2 extends ActionBarActivity {

   private Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customer = (Customer) getIntent().getSerializableExtra("registration_screen_1");
        setContentView(R.layout.activity_user_registration_2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_registration_2, menu);
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

    public void BackOrCancelPressed(View view){this.finish();}

    public void NextButtonPressed_Registration_2(View view){
        boolean flag= true;
        String message ="Please correct the following errors.";
        /*Get Entered First Name*/
        EditText text_box = (EditText) findViewById(R.id.phone_num_text);
        String temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_PHONE_NUMBER(temp.trim());
        }else{
            flag=false;
            message=message.concat("Your phone number should not be blank. ");
        }
        /*Get Entered Car Year*/
        text_box = (EditText) findViewById(R.id.car_year_text);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_CAR_YEAR(temp.trim());
        }else{
            flag=false;
            message=message.concat("Car year should not be blank. ");
        }
        /*Get Entered Car Make*/
        text_box = (EditText) findViewById(R.id.car_make_text);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_CAR_MAKE(temp.trim());
        }else{
            flag=false;
            message=message.concat("Car make should not be blank. ");
        }
        /*Get Entered Car Model*/
        text_box = (EditText) findViewById(R.id.car_model_text);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_CAR_MODEL(temp.trim());
        }else{
            flag=false;
            message=message.concat("Car model should not be blank. ");
        }
        /*Get Entered Car Vin #*/
        text_box = (EditText) findViewById(R.id.car_vin_text);
        temp = text_box.getText().toString();
        if(temp.length()!=0){
            customer.Set_VIN_NUMBER(temp.trim());
        }else{
            flag=false;
            message=message.concat("Vin # should not be blank. ");
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
            Intent intent = new Intent(this, user_registration_3.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("registration_screen_2",this.customer);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
