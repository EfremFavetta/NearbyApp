package com.progetto.nearby;

public class Tools {
	
	public static String SERVICE_PATH = "http://nearby.altervista.org";
	public static String GET_URL = SERVICE_PATH + "/test/index.php/places";
	
	public static final String PREFERENCES_FILE_NAME = "nearbypreferences";
	public static final String PREFERNCES_DISTANZA = "distanza";
	public static final String PREFERNCES_CATEGORIA = "categoria";
	public static final String PREFERNCES_TIPOLOGIA = "tipologia";
	
	public static final int FILTRO_DISTANZA_DEFAULT = 500;
	
	
	public static GPSProvider gpsProvider;
	
	
}