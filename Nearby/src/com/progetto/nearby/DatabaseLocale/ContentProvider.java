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
	public static final String path = "Preferiti";
	public DbHelper dbHelper;
	public static Uri preferitiUri = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + authority
			+ "/" + path);
	
	private static final int allFavourites = 0;
	private static final int oneFavourites = 1;
	
	private static final UriMatcher preferitiMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static
	{
		preferitiMatcher.addURI(authority, path, allFavourites);
		preferitiMatcher.addURI(authority, path, oneFavourites);
	}
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int result = 0;
		SQLiteDatabase favouritesDb = dbHelper.getWritableDatabase();
		switch(preferitiMatcher.match(preferitiUri))
		{
			case allFavourites: favouritesDb.delete(PreferitiTableHelper.tableName, selection, selectionArgs);
				break;
			case oneFavourites: 
				{
					String tmpId = uri.getLastPathSegment();
					favouritesDb.delete(PreferitiTableHelper.tableName, selection + "AND" + tmpId, selectionArgs);
				}
				break;
		}
		getContext().getContentResolver().notifyChange(preferitiUri, null);
		return result;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		String result = null;
		switch(preferitiMatcher.match(uri))
		{
			case allFavourites: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/allFavourites";
				break;
			case oneFavourites: result = ContentResolver.CURSOR_DIR_BASE_TYPE + "/singleFavourite";
				break;
		}
		return result;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		long result;
		SQLiteDatabase favouriteDb = dbHelper.getWritableDatabase();
		if(preferitiMatcher.match(uri) == allFavourites)
		{
			result = favouriteDb.insert(PreferitiTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(preferitiUri, null);
			return uri.parse(preferitiUri.toString() + "/" + result);
		}
		else if(preferitiMatcher.match(uri) == oneFavourites)
		{
			result = favouriteDb.insert(PreferitiTableHelper.tableName, null, values);
			getContext().getContentResolver().notifyChange(preferitiUri, null);
			return uri.parse(preferitiUri.toString() + "/" + result);
		}
		else
			return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new DbHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		SQLiteQueryBuilder favouritesQueryBilder = new SQLiteQueryBuilder();
		switch(preferitiMatcher.match(preferitiUri))
		{
			case allFavourites: 
				favouritesQueryBilder.setTables(PreferitiTableHelper.tableName);
				break;
			case oneFavourites: 
				favouritesQueryBilder.setTables(PreferitiTableHelper.tableName);
				favouritesQueryBilder.appendWhere(PreferitiTableHelper._ID + "=" + uri.getLastPathSegment());
				break;
			default:
				break;
		}
		Cursor favouritesQuery = favouritesQueryBilder.query(db, 
				projection, selection, selectionArgs, null, null, sortOrder);
		favouritesQuery.setNotificationUri(getContext().getContentResolver(), uri);
		return favouritesQuery;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int result = 0;
		SQLiteDatabase favouriteDb = dbHelper.getWritableDatabase();
		switch (preferitiMatcher.match(preferitiUri)) {
			case allFavourites:
				result = favouriteDb.update(PreferitiTableHelper.tableName, values, selection, selectionArgs);
				break;
			case oneFavourites: 
			{
				String tmpId = uri.getLastPathSegment();
				result = favouriteDb.update(PreferitiTableHelper.tableName, values, selection + "AND" + tmpId, selectionArgs);
			}
				break;
			default:
				break;
		}
		getContext().getContentResolver().notifyChange(preferitiUri, null);
		return result;
	}

}
