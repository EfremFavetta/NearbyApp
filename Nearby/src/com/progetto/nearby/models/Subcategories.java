package com.progetto.nearby.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Subcategories {

	public static final String tag_id = "id_subcategory";
	public static final String tag_name = "subcategory_name";
	public static final String tag_id_category = "id_category";
	
	
	public int id;
	public String name;
	public int id_category;
	
	public Subcategories(int id, String name, int id_category) {
		this.id = id;
		this.name = name;
		this.id_category = id_category;
	}
	
	public static Subcategories decodeJSON(JSONObject obj) {
		try {
			int id = obj.getInt(tag_id);
			String name = obj.getString(tag_name);
			int category = obj.getInt(tag_id_category);
			return new Subcategories(id, name, category);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
