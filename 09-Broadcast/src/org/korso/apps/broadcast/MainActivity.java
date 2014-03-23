package org.korso.apps.broadcast;

import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * Here we use the broadcast to use the gps. So in the onStart of the activity
 * we take the broadcast and in the onStop we free the broadcast
 * 
 * */

public class MainActivity extends Activity implements OnClickListener,  LocationListener{

	private LocationManager locationManager;
	private final String TAG = "GPS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View button = findViewById(R.id.button1);
		button.setOnClickListener(this);
		
	}

	@Override
	protected void onStart() {		
		super.onStart();// we have to call algways to the supper otherwise it will explote.
		
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		locationManager.removeUpdates(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent("korso.curso.HOLA_MUNDO");
		sendBroadcast(intent);
		
	}



	@Override
	public void onLocationChanged(Location location) {

		Log.d( TAG, "lat= "+ location.getLatitude() + "lon= " + location.getLongitude());
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
