package com.example.ashwinkumar.drivetowin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import DatabaseAccessor.APIForSQLiteDB;
import DatabaseAccessor.EmergencyContacts;


public class list_emergency_contacts extends ActionBarActivity {

    private List<EmergencyContacts> em_contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
        em_contacts=obj.getAllEmergencyContacts();
        setContentView(R.layout.activity_list_emergency_contacts);
        InitializeList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_emergency_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.add_emergency_contact){
           if(em_contacts.size()>=5){
               new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("You cannot add more than 5 emergency contacts")
                        .setCancelable(true)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }else{
                Intent intent=new Intent(this,add_emergency_contacts.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void InitializeList(){
        List<String> names=new ArrayList<>();
        for (int i=0;i<em_contacts.size();i++)
            names.add(em_contacts.get(i).Get_NAME());
        ListView list = (ListView)findViewById(R.id.emergencyContactList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.activity_list_emergency_contacts,names);
        list.setAdapter(adapter);
    }
}
