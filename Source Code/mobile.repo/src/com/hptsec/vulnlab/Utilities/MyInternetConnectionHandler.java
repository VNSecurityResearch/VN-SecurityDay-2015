package com.hptsec.vulnlab.Utilities;

import android.content.Context;
import android.net.*;

public class MyInternetConnectionHandler {

	public static boolean isConnected(Context ct) {
		boolean connected = false;

		ConnectivityManager cm = (ConnectivityManager) ct
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm != null) {
			NetworkInfo[] netInfo = cm.getAllNetworkInfo();

			for (NetworkInfo ni : netInfo) {
				if ((ni.getTypeName().equalsIgnoreCase("WIFI") || ni
						.getTypeName().equalsIgnoreCase("MOBILE"))
						&& ni.isConnected() && ni.isAvailable()) {
					connected = true;
				}

			}
		}
		return connected;
	}
}
