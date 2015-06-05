package com.progetto.nearby.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.progetto.nearby.Tools;

public class Place {

	public static final String tag_id = "id_place";
	public static final String tag_name = "place_name";
	public static final String tag_description = "place_description";
	public static final String tag_gps = "gps";
	public static final String tag_phone = "place_phone";
	public static final String tag_citta = "town_name";
	public static final String tag_gallery = "gallery";
	
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
	public String urlImg;
	public ArrayList<String> gallery = new ArrayList<String>();
	
	public static Place decodeJSON(JSONObject obj) {
		Place place = new Place();
		try {
			if(obj.has(tag_id))
				place.id = obj.getInt(tag_id);
			place.nome = obj.getString(tag_name);
			place.description = obj.getString(tag_description);
			String gps = obj.getString(tag_gps);
			String[] gpssplit = gps.split("\\+");
			place.lat = Float.parseFloat(gpssplit[0]);
			place.longit = Float.parseFloat(gpssplit[1]);
			place.telefono = obj.getString(tag_phone);
			place.città = obj.getString(tag_citta);
			place.urlImg = Tools.GET_IMAGE_URL + place.id + ".jpg";
			//place.gallery.add(place.urlImg);
			if(obj.has(tag_gallery))
			{
				JSONArray gallery = (JSONArray) obj.get(tag_gallery);
				for(int i = 0; i < gallery.length(); i++)
					place.gallery.add(Tools.GET_IMAGE_URL + gallery.getString(i));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return place;
	}
}
