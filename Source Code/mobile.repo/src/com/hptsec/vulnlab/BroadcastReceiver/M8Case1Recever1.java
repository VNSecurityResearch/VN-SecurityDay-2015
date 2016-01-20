package com.hptsec.vulnlab.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class M8Case1Recever1 extends BroadcastReceiver {

	private static String TAG = "M8Case1Receiver1";
	private static String EXTRAS = "M8-OrderedBroadcast";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// Bundle results = getResultExtras(true);
		Bundle results = intent.getExtras();
		if (results != null) {
			// Put "secret" to abort Broadcast at Receiver 2
			String forwardString = results.getString(EXTRAS, "nil");
			forwardString += " -> " + TAG;
			results.putString(EXTRAS, forwardString);
			this.setResultExtras(results);
			Log.d(TAG, "Priority: 3, Put Extras with R1 Tag");
		} else {
			Log.d(TAG, "EXTRAS Nil!");
		}

	}

}
