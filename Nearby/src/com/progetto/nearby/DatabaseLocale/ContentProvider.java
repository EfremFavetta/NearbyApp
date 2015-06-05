package com.progetto.nearby.DatabaseLocale;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ContentProvider extends android.content.ContentProvider {

	public static final String authority = "com.progetto.nearby.databaselocale.contentprovider";
	
	public static final String FAVORITES_PATH = "favorites";
	public static final String CATEGORIES_PATH = "categories";
	public static final String SUBCATEGORIES_PATH = "subcategories";
	
	public DbHelper dbHelper;
	
	public static Uri FAVORITES_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + authority
			+ "/" + FAVORITES_PATH);
	
	public static Uri CATEGORIES_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + authority
			+ "/" + CATEGORIES_PATH);
	
	public static Uri SUBCATEGORIES_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + authority
			+ "/" + SUBCATEGORIES_PATH);
	
	//favourites
	private static final int allFavorites = 0;
	private static final int oneFavorite = 1;
	
	//categories
	private static final int allCategories = 2;
	private static final int oneCategory  = 3;

	//subcategories
	private static final int allSubcategories = 4;
	private static final int oneSubcategory  = 5;
	
	
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static
	{
		uriMatcher.addURI(authority, FAVORITES_PATH, allFavorites);
		uriMatcher.addURI(authority, FAVORITES_PATH, oneFavorite);
		
		uriMatcher.addURI(authority, CATEGORIES_PATH, allCategories);
		uriMatcher.addURI(authority, CATEGORIES_PATH, oneCategory);
		
		uriMatcher.addURI(authority, SUBCATEGORIES_PATH, allSubcategories);
		uriMatcher.addURI(authority, SUBCATEGORIES_PATH, oneSubcategory);

	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int result = 0;
		String tmpId = null;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		switch(uriMatcher.match(FAVORITES_URI))
		{
			case allFavorites:
				db.delete(FavoritesTableHelper.tableName, selection, selectionArgs);
				getContext().getContentResolver().notifyChange(FAVORITES_URI, null);
				break;
				
			case oneFavorite: 
				tmpId = uri.getLastPathSegment();
				db.delete(FavoritesTableHelper.tableName, selection + "AND" + tmpId, selectionArgs);
				getContext().getContentResolver().notifyChange(FAVORITES_URI, null);
				break;
			
			case allCategories:
				db.delete(CategoriesTableHelper.tableName, selection, selectionArgs);
				getContext().getContentResolver().notifyChange(CATEGORIES_URI, null);
				break;
				
			case oneCategory: 
				tmpId = uri.getLastPathSegment();
				db.delete(CategoriesTableHelper.tableName, selection + "AND" + tmpId, selectionArgs);
				getContext().getContentResolver().notifyChange(CATEGORIES_URI, null);
				break;
				
			case allSubcategories:
				db.delete(SubcategoriesTableHelper.tableName, selection, selectionArgs);
				getContext().getContentResolver().notifyChange(SUBCATEGORIES_URI, null);
				break;
				
			case oneSubcategory: 
				tmpId = uri.getLastPathSegment();
				db.delete(SubcategoriesTableHelper.tableName, selection + "AND" + tmpId, selectionArgs);
				getContext().getContentResolver().notifyChange(SUBCATEGORIES_URI, null);
				break;
		}
		return result;
	}

	@Override
	public String getType(Uri uri) {
		String result = null;
		switch(uriMatcher.match(uri))
		{
			case allFavorites: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/allFavourites";
				break;
			case oneFavorite: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/singleFavourite";
				break;
						
			case allCategories: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/allCategories";
				break;
			case oneCategory: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/singleCategory";
				break;
				
			case allSubcategories: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/allSubcategories";
			break;
			case oneSubcategory: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/singleSubcategory";
			break;
		}
		return result;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long result;
		SQLiteDatabase vDb = dbHelper.getWritableDatabase();
		if(uriMatcher.match(uri) == allFavorites)
		{
			result = vDb.insert(FavoritesTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(FAVORITES_URI, null);
			return uri.parse(FAVORITES_URI.toString() + "/" + result);
		}
		else if(uriMatcher.match(uri) == oneFavorite)
		{
			result = vDb.insert(FavoritesTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(FAVORITES_URI, null);
			return uri.parse(FAVORITES_URI.toString() + "/" + result);
		}
		else if(uriMatcher.match(uri) == allCategories)
		{
			result = vDb.insert(CategoriesTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(CATEGORIES_URI, null);
			return uri.parse(CATEGORIES_URI.toString() + "/" + result);
		}
		else if(uriMatcher.match(uri) == oneCategory)
		{
			result = vDb.insert(CategoriesTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(CATEGORIES_URI, null);
			return uri.parse(CATEGORIES_URI.toString() + "/" + result);
		}
		else if(uriMatcher.match(uri) == allSubcategories)
		{
			result = vDb.insert(SubcategoriesTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(SUBCATEGORIES_URI, null);
			return uri.parse(SUBCATEGORIES_URI.toString() + "/" + result);
		}
		else if(uriMatcher.match(uri) == oneSubcategory)
		{
			result = vDb.insert(SubcategoriesTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(SUBCATEGORIES_URI, null);
			return uri.parse(SUBCATEGORIES_URI.toString() + "/" + result);
		}
		
		else
			return null;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DbHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		SQLiteQueryBuilder sqlQueryBilder = new SQLiteQueryBuilder();
		switch(uriMatcher.match(uri))
		{
			case allFavorites: 
				sqlQueryBilder.setTables(FavoritesTableHelper.tableName);
				break;
			case oneFavorite: 
				sqlQueryBilder.setTables(FavoritesTableHelper.tableName);
				sqlQueryBilder.appendWhere(FavoritesTableHelper._ID + "=" + uri.getLastPathSegment());
				break;
				
			case allCategories: 
				sqlQueryBilder.setTables(CategoriesTableHelper.tableName);
				break;
			case oneCategory: 
				sqlQueryBilder.setTables(CategoriesTableHelper.tableName);
				sqlQueryBilder.appendWhere(CategoriesTableHelper._ID + "=" + uri.getLastPathSegment());
				break;
				
			case allSubcategories: 
				sqlQueryBilder.setTables(SubcategoriesTableHelper.tableName);
				break;
			case oneSubcategory: 
				sqlQueryBilder.setTables(SubcategoriesTableHelper.tableName);
				sqlQueryBilder.appendWhere(SubcategoriesTableHelper._ID + "=" + uri.getLastPathSegment());
				break;
			default:
				break;
		}
		Cursor cursorQuery = sqlQueryBilder.query(db, 
				projection, selection, selectionArgs, null, null, sortOrder);
		cursorQuery.setNotificationUri(getContext().getContentResolver(), uri);
		return cursorQuery;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int result = 0;
		String tmpId = null;
		SQLiteDatabase vDb = dbHelper.getWritableDatabase();
		switch (uriMatcher.match(uri)) {
			case allFavorites:
				result = vDb.update(FavoritesTableHelper.tableName, values, selection, selectionArgs);
				getContext().getContentResolver().notifyChange(FAVORITES_URI, null);
			break;
			case oneFavorite: 			
				tmpId = uri.getLastPathSegment();
				result = vDb.update(FavoritesTableHelper.tableName, values, selection + "AND" + tmpId, selectionArgs);
				getContext().getContentResolver().notifyChange(FAVORITES_URI, null);
			break;
			
			case allCategories:
				result = vDb.update(CategoriesTableHelper.tableName, values, selection, selectionArgs);
				getContext().getContentResolver().notifyChange(CATEGORIES_URI, null);
			break;
			case oneCategory: 			
				tmpId = uri.getLastPathSegment();
				result = vDb.update(CategoriesTableHelper.tableName, values, selection + "AND" + tmpId, selectionArgs);
				getContext().getContentResolver().notifyChange(CATEGORIES_URI, null);
			break;
			
			case allSubcategories:
				result = vDb.update(SubcategoriesTableHelper.tableName, values, selection, selectionArgs);
				getContext().getContentResolver().notifyChange(SUBCATEGORIES_URI, null);
			break;
			case oneSubcategory: 			
				tmpId = uri.getLastPathSegment();
				result = vDb.update(SubcategoriesTableHelper.tableName, values, selection + "AND" + tmpId, selectionArgs);
				getContext().getContentResolver().notifyChange(SUBCATEGORIES_URI, null);
			break;
			
			default:
				break;
		}
		
		return result;
	}

}
