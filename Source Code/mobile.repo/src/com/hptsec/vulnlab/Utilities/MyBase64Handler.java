package com.hptsec.vulnlab.Utilities;

import android.util.Base64;

public class MyBase64Handler {

	public static String encodeByteArrayToBase64(byte[] byteData) {
		return Base64.encodeToString(byteData, Base64.DEFAULT);
	}

	public static byte[] decodeBase64ToByteArray(String based64Data) {
		return Base64.decode(based64Data, Base64.DEFAULT);
	}
}
