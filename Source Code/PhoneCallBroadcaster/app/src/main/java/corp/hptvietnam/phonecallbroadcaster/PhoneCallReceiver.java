package corp.hptvietnam.phonecallbroadcaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by baolq on 9/23/2015.
 */
public class PhoneCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("phone_number");
        Intent call_intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + message));
        call_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(call_intent);
    }
}
