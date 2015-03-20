package com.example.ashwinkumar.drivetowin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import DatabaseAccessor.*;
import LibraryFunctions.SwipeListViewActivity;


public class List_emergency_Contacts extends SwipeListViewActivity {
    private List<EmergencyContacts> em_contacts;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emergency_contacts);
        InitializeList();
    }
    @Override
    public void onResume(){
        InitializeList();
        super.onResume();
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
        mListView = (ListView) findViewById(R.id.emergencyContactList);
        APIForSQLiteDB obj = new APIForSQLiteDB(this.getApplicationContext());
        em_contacts=obj.getAllEmergencyContacts();
        List<String> names=new ArrayList<>();
        for (int i=0;i<em_contacts.size();i++)
            names.add(em_contacts.get(i).Get_NAME());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
        mListView.setAdapter(adapter);
        Log.d("Info","DTW: "+em_contacts.size()+" elements added");
    }
    public ListView getListView() {
        return mListView;
    }
    public void getSwipeItem(boolean isRight, int position) {
        //Delete Contact
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Are you sure you want to delete this emergency contact ?")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Code to delete emergency contact
                    }
                })
                .setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
    public void onItemClickListener(ListAdapter adapter, int position) {
        //Edit Contact
        Intent intent = new Intent(this, add_emergency_contacts.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("edit_emergency_contact",em_contacts.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
