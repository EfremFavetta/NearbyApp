package com.progetto.nearby.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Place {

	public static final String tag_id = "id_place";
	public static final String tag_name = "place_name";
	public static final String tag_description = "place_description";
	public static final String tag_gps = "gps";
	public static final String tag_phone = "place_phone";
	
	public int id;
	public String città;
	public String proprietario;
	public String nome;
	public String description;
	public float lat;
	public float longit;
	public String telefono;
	public String tipo;
	public float distanza;
	
	public static Place decodeJSON(JSONObject obj) {
		Place place = new Place();
		try {
			place.id = obj.getInt(tag_id);
			place.nome = obj.getString(tag_name);
			place.description = obj.getString(tag_description);
			String gps = obj.getString(tag_gps);
			String[] gpssplit = gps.split(","); 
			place.lat = Float.parseFloat(gpssplit[0]);
			place.longit = Float.parseFloat(gpssplit[1]);
			place.telefono = obj.getString(tag_phone);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return place;
	}
}
