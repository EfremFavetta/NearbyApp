package com.progetto.nearby;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Tools {
	
	public static String SERVICE_PATH = "http://nearby.altervista.org";
	public static String GET_URL = SERVICE_PATH + "/test/index.php/places";
	public static String GET_DETAIL_URL = SERVICE_PATH + "/test/index.php/place/";
	public static String GET_IMAGE_URL = "http://nearby.altervista.org/images/";
	
	public static final String PREFERENCES_FILE_NAME = "nearbypreferences";
	public static final String PREFERNCES_DISTANZA = "distanza";
	public static final String PREFERNCES_CATEGORIA = "categoria";
	public static final String PREFERNCES_TIPOLOGIA = "tipologia";
	
	public static final int FILTRO_DISTANZA_DEFAULT = 500;
	
	
	public static GPSProvider gpsProvider;
	private static NetworkInfo networkInfo;
	private static ConnectivityManager connectivityManager;
	
	public static boolean isNetworkEnabled(Context context) {
		connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		networkInfo = connectivityManager.getActiveNetworkInfo();
		
		return networkInfo != null && networkInfo.isConnectedOrConnecting();
	}
}