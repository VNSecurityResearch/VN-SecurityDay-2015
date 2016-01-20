package com.hptsec.vulnlab.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/***
 * Denial of OrderedBroadcast
 * 
 * @author whitehatpanda
 * 
 */
public class M8Case1Receiver2 extends BroadcastReceiver {

	private static String TAG = "M8Case1Receiver2";
	private static String EXTRAS = "M8-OrderedBroadcast";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle results = getResultExtras(true);
		if (results != null) {
			// Put "secret" to abort Broadcast at Receiver 2
			String forwardString = results.getString(EXTRAS, "nil");
			if (forwardString.contains("secret")) {
				forwardString += " -> " + TAG
						+ " -> Denial of OrderedBroadcast chain!!";
				abortBroadcast();
			} else {
				forwardString += " -> " + TAG;
			}
			results.putString(EXTRAS, forwardString);
			this.setResultExtras(results);
			Log.d(TAG, "Priority: 2, Put Extras with R2 Tag");
		} else {
			Log.d(TAG, "EXTRAS Nil!");
		}
	}

}
