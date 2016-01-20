package com.hptsec.vulnlab.Controller;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class M7Case1SqlInjectionController extends SQLiteOpenHelper {

	// Database Name
	private static final String DATABASE_NAME = "MyM7SqlVuln";

	private static final int DATABASE_VERSION = 1;

	// UserInfo table name
	private static final String TABLE_NOTE = "mynotekeeper";

	private static final String KEY_ID = "id";
	private static final String KEY_NOTEDATA = "notedata";
	private static final String KEY_PRIVATENOTE = "privatenote";

	public M7Case1SqlInjectionController(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_NOTE + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOTEDATA
				+ " TEXT," + KEY_PRIVATENOTE + " INTEGER" + ")";

		Log.d("M7Case1 - Create Table ", CREATE_NOTE_TABLE);

		db.execSQL(CREATE_NOTE_TABLE);

		String INSERT_ITEM_1 = "INSERT INTO " + TABLE_NOTE + " (" + KEY_ID
				+ "," + KEY_NOTEDATA + ", " + KEY_PRIVATENOTE
				+ ") VALUES (null, \"My Public Note\", 0)";

		db.execSQL(INSERT_ITEM_1);

		String INSERT_ITEM_2 = "INSERT INTO "
				+ TABLE_NOTE
				+ " ("
				+ KEY_ID
				+ ","
				+ KEY_NOTEDATA
				+ ","
				+ KEY_PRIVATENOTE
				+ ") VALUES (null, \"My Email (pub): example@gmail.com\", 0)";

		db.execSQL(INSERT_ITEM_2);

		String INSERT_ITEM_3 = "INSERT INTO "
				+ TABLE_NOTE
				+ " ("
				+ KEY_ID
				+ ","
				+ KEY_NOTEDATA
				+ ","
				+ KEY_PRIVATENOTE
				+ ") VALUES (null, \"My hobbies are Programming and Listening to music\", 0)";

		db.execSQL(INSERT_ITEM_3);

		String INSERT_PRIVATE_ITEM = "INSERT INTO "
				+ TABLE_NOTE
				+ " ("
				+ KEY_ID
				+ ","
				+ KEY_NOTEDATA
				+ ","
				+ KEY_PRIVATENOTE
				+ ") VALUES (null, \"My Secret: Th1s 1s Sql 1nj3c710n! - YOU HAVE DONE THIS CASE!\", 1)";

		db.execSQL(INSERT_PRIVATE_ITEM);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);

		// Create tables again
		onCreate(db);
	}

	public ArrayList<String> getPublicNote(String mySearch) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			String query = "SELECT * FROM " + TABLE_NOTE + " WHERE "
					+ KEY_NOTEDATA + " LIKE '%" + mySearch + "%' AND "
					+ KEY_PRIVATENOTE + " = 0";

			Log.d("M7Case1 - Query", query);

			Cursor cursor = db.rawQuery(query, null);

			// M7MyNoteModel myNote = new M7MyNoteModel();
			ArrayList<String> arrNote = new ArrayList<String>();

			if (cursor != null && cursor.moveToFirst()) { // Check not null
				// myNote.setNoteId(cursor.getInt(0));
				// myNote.setNoteData(cursor.getString(1));
				// myNote.setPrivate(false);
				do {
					arrNote.add(cursor.getString(1));
				} while (cursor.moveToNext());
			}
			return arrNote;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public int getNumberOfPublicNote() {
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT count(id) FROM " + TABLE_NOTE + " WHERE "
				+ KEY_PRIVATENOTE + " = 0";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			return cursor.getInt(0);
		}
		return 0;
	}
}
