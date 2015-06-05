package com.progetto.nearby.DatabaseLocale;

import android.provider.BaseColumns;

public class FavoritesTableHelper implements BaseColumns {

	public static final String tableName = "Preferiti";
	public static final String idPlaces = "Places";
	public static final String create = 
			"CREATE TABLE IF NOT EXISTS " + tableName + " (" + 
			_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			idPlaces + " INTEGER" +
			");";
	public static final String drop =
			"DROP TABLE " + tableName;
}
