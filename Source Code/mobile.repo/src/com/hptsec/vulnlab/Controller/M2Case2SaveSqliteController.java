package com.hptsec.vulnlab.Controller;

import com.hptsec.vulnlab.Model.M2LoginUserModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class M2Case2SaveSqliteController extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "MyM2Credentials";

	// UserInfo table name
	private static final String TABLE_USERINFO = "userinfo";

	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";

	public M2Case2SaveSqliteController(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_USERINFO_TABLE = "CREATE TABLE " + TABLE_USERINFO + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME
				+ " TEXT," + KEY_PASSWORD + " TEXT" + ")";
		Log.d("M2Case 2 - Create Table ", TABLE_USERINFO);
		db.execSQL(CREATE_USERINFO_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table userinfo
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);

		// Create tables again
		onCreate(db);
	}

	public boolean AddUser(M2LoginUserModel userinfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.putNull(KEY_ID);
		values.put(KEY_USERNAME, userinfo.getUsername()); // User name
		values.put(KEY_PASSWORD, userinfo.getPassword()); // User pass

		// Inserting Row
		if (db.insert(TABLE_USERINFO, null, values) > 0) {
			db.close(); // Closing database connection
			return true;
		}
		return false;

	}
}
