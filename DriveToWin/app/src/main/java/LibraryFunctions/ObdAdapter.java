package LibraryFunctions;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.Switch;

import java.io.IOException;
import java.util.UUID;

import pt.lighthouselabs.obd.commands.SpeedObdCommand;
import pt.lighthouselabs.obd.commands.engine.EngineRPMObdCommand;
import pt.lighthouselabs.obd.commands.engine.EngineRuntimeObdCommand;
import pt.lighthouselabs.obd.commands.fuel.FuelLevelObdCommand;
import pt.lighthouselabs.obd.commands.protocol.EchoOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.LineFeedOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectProtocolObdCommand;
import pt.lighthouselabs.obd.commands.protocol.TimeoutObdCommand;
import pt.lighthouselabs.obd.enums.ObdProtocols;

/**
 * Created by lelouch on 3/18/15.
 */
public class ObdAdapter {

	BluetoothSocket socket = null;
	public ObdAdapter(String deviceAddress){
		BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
		BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
		UUID uuid = UUID.fromString(deviceAddress);

		try {
			socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket.connect();
			new EchoOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());

			new LineFeedOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());

			new TimeoutObdCommand(10).run(socket.getInputStream(), socket.getOutputStream());

			new SelectProtocolObdCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getSpeedData(){
		String speedData = "";
		SpeedObdCommand speed = new SpeedObdCommand();
		while (!Thread.currentThread().isInterrupted()){
			try {
				speed.run(socket.getInputStream(), socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			speedData =  speed.getFormattedResult();
		}
		return speedData;
	}

	public String getEngineRPMData(){
		String speedData = "";
		EngineRPMObdCommand engine = new EngineRPMObdCommand();
		while (!Thread.currentThread().isInterrupted()){
			try {
				engine.run(socket.getInputStream(), socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			speedData =  engine.getFormattedResult();
		}
		return speedData;
	}

	public String getEngineRunTimeData(){
		String engineData = "";
		EngineRuntimeObdCommand engine = new EngineRuntimeObdCommand();
		while (!Thread.currentThread().isInterrupted()){
			try {
				engine.run(socket.getInputStream(), socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			engineData =  engine.getFormattedResult();
		}
		return engineData;
	}

	public String getFuelLevel(){
		String fuelLevel = "";
		FuelLevelObdCommand fuel = new FuelLevelObdCommand();
		while (!Thread.currentThread().isInterrupted()){
			try {
				fuel.run(socket.getInputStream(), socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fuelLevel =  fuel.getFormattedResult();
		}
		return fuelLevel;
	}
}
