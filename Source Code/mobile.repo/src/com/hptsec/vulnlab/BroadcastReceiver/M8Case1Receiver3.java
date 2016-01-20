package com.hptsec.vulnlab.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class M8Case1Receiver3 extends BroadcastReceiver {

	private static String TAG = "M8Case1Receiver3";
	private static String EXTRAS = "M8-OrderedBroadcast";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle results = getResultExtras(true);
		if (results != null) {
			String forwardString = results.getString(EXTRAS, "nil");
			forwardString += " -> " + TAG;
			results.putString(EXTRAS, forwardString);
			this.setResultExtras(results);
			Log.d(TAG, "Priority: 1, Put Extras with R3 Tag");
		} else {
			Log.d(TAG, "EXTRAS Nil!");
		}
	}

}
