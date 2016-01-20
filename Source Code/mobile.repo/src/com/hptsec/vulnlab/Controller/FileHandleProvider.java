package com.hptsec.vulnlab.Controller;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * M7Case 3 - Path Manipulation - Content Provider with Exported = True
 * 
 * @author whitehatpanda
 * 
 */
public class FileHandleProvider extends ContentProvider {

	private static int RETURNCODE = 1;

	public static String PROVIDER_NAME = "com.hptsec.vulnlab.Controller.FileHandleProvider";

	public static Uri PROVIDER_URI = Uri.parse("content://" + PROVIDER_NAME
			+ "/");

	private UriMatcher myUriMatcher = new UriMatcher(-1);

	private static final String TAG = "M7Case3 - Path Manipulation";

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		this.myUriMatcher.addURI(PROVIDER_NAME, "*",
				RETURNCODE);
		return false;
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode)
			throws FileNotFoundException {
		// TODO Auto-generated method stub
		try {
			File f = new File(getContext().getFilesDir(), uri.getPath());
			if (f.exists()) {
				return ParcelFileDescriptor.open(f,
						ParcelFileDescriptor.MODE_READ_ONLY);
				// Note that File System can be read when using this
				// MODE_READ_ONLY.
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
