package com.progetto.nearby;

import com.google.android.gms.maps.model.LatLng;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSProvider implements LocationListener {
	
	private Context mContext;
	
    private boolean isGPSEnabled = false;
 
    private boolean isNetworkEnabled = false;
 
    // flag for GPS status
    private boolean canGetLocation = false;
 
    private Location location;
    private double latitude;
    private double longitude;
 
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 50; // 50 meters
 
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 20000; // 20sec
 
    protected LocationManager locationManager;

	private boolean isActivityDetached;
 
    public GPSProvider(Context context) {
        attachActivity(context);
        getLocation();
    }
 
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(Service.LOCATION_SERVICE);
            
            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
 
            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(isGPSEnabled) {
            	locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            } else {
            	locationManager.requestLocationUpdates(
	            		LocationManager.NETWORK_PROVIDER,
	            		MIN_TIME_BW_UPDATES,
	            		MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            }
            
            
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    /*locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);*/
                    Log.d("gps", "Network");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled -> get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        /*locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);*/
                        Log.d("gps", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return location;
    }
     
    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSProvider.this);
            Log.d("gps", "stop using gps");
        }
    }
    
    public LatLng getLatLng() {
    	if(location != null) {
    		return new LatLng(location.getLatitude(), location.getLongitude());
    	}
    	return new LatLng(latitude, longitude);
    }
    
    public double getLatitude() {
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }
    
    public double getLongitude() {
        if(location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }
     
    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }
     
    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     * */
    public void showSettingsAlert(){ //TODO sostituire con mio dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
      
        alertDialog.setTitle("Posizione GPS");
        alertDialog.setMessage("La connessione GPS è disattivata. Vuoi abilitarla per una posizione più precisa?");
        
        alertDialog.setPositiveButton("Impostazioni", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });

        alertDialog.show();
    }
 
    @Override
    public void onLocationChanged(Location location) {
    	this.location = location;
    	if(!isActivityDetached) {
    		//Tools.calculateAllDistances(mContext, new LatLng(getLatitude(), getLongitude()));
    	}
    	Log.d("gps", "on location changed");
    }
 
    //@Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    
	public void detachActivity() {//mai usato, potrebbe servire se non riconosce il context nel calcolo delle distanze
		isActivityDetached = true;
	}
	
	public void attachActivity(Context context) {
		mContext = context;
		isActivityDetached = false;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
