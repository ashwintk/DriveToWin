package com.example.ashwinkumar.drivetowin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class initial_screen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
    public void userLogin (View v){
        Intent intent = new Intent(this, aaa_user_login.class);
        startActivity(intent);
    }

    public void get_aaa_membership(View v){
        String url = "http://www.aaa.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        Uri u = Uri.parse(url);
        try{
            i.setData(u);
            startActivity(i);
        }catch(Exception e){
            Toast.makeText(this, "Browser not found.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sign_in_in_as_guest(View v){
        Intent intent = new Intent(getApplicationContext(),user_registration.class);
        startActivity(intent);
    }
}
