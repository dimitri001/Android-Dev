package data;

import data.CallsContract.CallsTable;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider{

	private MyDbHelper mDbHelper;
	private static final int TYPE_USERS_COLLECTION = 1;
	private static final int TYPE_USERS_ITEM = 2;

	//here we create the UriMatcher here this name start with the s because this is an stactic value
	private static final UriMatcher sUriMatcher; 
	 //here is initialized in an static block
	//the uri's here are changed to number values
	//Every uri that we create is added here

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(CallsContract.AUTHORITY, "users", TYPE_USERS_COLLECTION);//all the regs
		sUriMatcher.addURI(CallsContract.AUTHORITY, "users/#", TYPE_USERS_ITEM);//one reg, the # represents t 
	}
	
	//we instanced what we need here at the contentprovider
	@Override
	public boolean onCreate() {
		mDbHelper = new MyDbHelper(getContext());
		return true;
	}
	
	//returns a mimetype used to filter the reg that correspond to a uri 
	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)){
			case TYPE_USERS_COLLECTION:
				return "android.cursor.dir/vnd.org.korso.apps.mycontentprovider";
			case TYPE_USERS_ITEM:
				return "android.cursor.item/vnd.org.korso.apps.mycontentprovider";
			default:
				return null;
		}
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		int uriType = sUriMatcher.match(uri);
		if(uriType!=TYPE_USERS_COLLECTION){
			return null;
			
		} 

		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		long id = db.insert(CallsTable.TABLE_NAME, null, values);
		
		Uri newUri = CallsTable.getUri(id);
		
		return newUri;
	}

	

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String orderBy) {
		
		final SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		String table =CallsTable.TABLE_NAME;
		String groupBy = null;
		String having = null;
		Cursor cursor;
		
		switch (sUriMatcher.match(uri)){
		case TYPE_USERS_ITEM:
			String id = uri.getLastPathSegment();
			
			if (!TextUtils.isEmpty(selection)){
				selection += " AND ";
			}else {
				selection = "";
			}
			
			selection += CallsTable._ID+ "=="+id;
			
			
			//cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, orderBy);
			//return cursor;
			
		case TYPE_USERS_COLLECTION:		
			
			cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, orderBy);
			return cursor;
			
		default:
			return null;
			
		}
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
