package com.progetto.nearby.DatabaseLocale;

import android.provider.BaseColumns;

public class CategoriesTableHelper implements BaseColumns{
	
	public static final String tableName = "Categories";
	public static final String name = "category_name";
	public static final String create = 
			"CREATE TABLE IF NOT EXISTS " + tableName + " (" + 
			_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			name + " TEXT NOT NULL" +
			");";
	public static final String drop =
			"DROP TABLE " + tableName;
}
