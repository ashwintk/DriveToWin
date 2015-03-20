package com.example.ashwinkumar.drivetowin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class main_menu extends ActionBarActivity {
	Intent intent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		ListView list = (ListView) findViewById(R.id.mainmenu_listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
					case 0:
						break;
					case 1:
						intent = new Intent(getApplicationContext(), VehicleDiagonistic.class);
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						intent=new Intent(getApplicationContext(),List_emergency_Contacts.class);
						break;
                    case 6:
                        intent=new Intent(getApplicationContext(),road_assistance.class);
                        break;
				}
				if (position <= 6) {
					startActivity(intent);
				}
	        }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
}
