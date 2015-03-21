package com.example.ashwinkumar.drivetowin;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;

import LibraryFunctions.ObdAdapter;


public class VehicleDiagonistic extends ActionBarActivity {

	ArrayList deviceStrs;
	final ArrayList devices = new ArrayList();
	BluetoothAdapter btAdapter;
	Set<BluetoothDevice> pairedDevices;
	Integer REQUEST_ENABLE_BT = 1;
	String deviceAddress;
	public ObdAdapter obdAdapter;
	TextView engine;
	TextView fuel;
	TextView mileage;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_diagonistic);
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			Toast.makeText(getApplicationContext(), "Device doesn't support Bluetooth", Toast.LENGTH_LONG).show();
		}
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			Toast.makeText(getApplicationContext(), "Blutooth", Toast.LENGTH_LONG).show();
		}
		deviceStrs  = new ArrayList();
		pairedDevices = mBluetoothAdapter.getBondedDevices();
		if (pairedDevices.size() > 0){
			for (BluetoothDevice device : pairedDevices) {
				deviceStrs.add(device.getName() + "\n" + device.getAddress());
				devices.add(device.getAddress());
			}
		}
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		final ArrayAdapter[] adapter = {new ArrayAdapter(this, android.R.layout.select_dialog_singlechoice,
			deviceStrs.toArray(new String[deviceStrs.size()]))};

		alertDialog.setSingleChoiceItems(adapter[0], -1, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
				deviceAddress = (String) devices.get(position);
				connectToObd();
				loadObdData();

			}
		});
		alertDialog.setTitle("Choose Bluetooth device");
		alertDialog.show();
	}

	public void connectToObd(){
		obdAdapter = new ObdAdapter(deviceAddress);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_vehicle_diagonistic, menu);
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

	public void loadObdData(){
		engine = (TextView)findViewById(R.id.engine_runtime);
		engine.setText(obdAdapter.getEngineRunTimeData());
		fuel = (TextView)findViewById(R.id.fuel_level);
		fuel.setText(obdAdapter.getFuelLevel());
		mileage = (TextView)findViewById(R.id.mileage);
		mileage.setText(obdAdapter.getEngineMileage());
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_ENABLE_BT){
			if (resultCode == RESULT_OK){
				Toast.makeText(getApplicationContext(), "Bluetooth connected", Toast.LENGTH_LONG).show();
			}
		}
		if (requestCode == RESULT_CANCELED) {
			Toast.makeText(getApplicationContext(), "BlueTooth request disconnected", Toast.LENGTH_LONG).show();
		}
	}
}
