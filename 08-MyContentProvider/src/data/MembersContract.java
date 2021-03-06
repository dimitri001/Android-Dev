package data;

import android.net.Uri;
import android.provider.BaseColumns;

/*
 * This class is used to create the constants that we are going to use.
 * */

public class MembersContract {
	
	public static final String DB_NAME ="users_db";
	public static final String AUTHORITY = "org.korso.apps.mycontentprovider";

	//Making the constructor private, we avoid that anybody instance this class
	private MembersContract(){}
	
	//Implementing BaseColumns we make that our new class has an _id used in android
	//we implement the BaseColumns because it�s added the id_ field
	public static class UsersTable implements BaseColumns{
		
		//Making the constructor private, we avoid that anybody instance this class
		private UsersTable(){}
		
		public static final String TABLE_NAME = "users";		
		public static final String USERNAME ="username";
		public static final String EMAIL="email";
		public static final String DATE="date";
		
		public static Uri getUri() {
			return Uri.parse("content://"+AUTHORITY+"/users");
		}
		
		public static Uri getUri(long id) {
			return Uri.parse("content://"+AUTHORITY+"/users/"+id);
		}
		
	

		}
	
}
