package corp.hptvietnam.androidkeystore;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by whitehatpanda on 11/9/15.
 */
abstract class KeyStoreTask extends AsyncTask<Void, Void, String[]> {

    private Context context;

    private static final String TAG = "AndroidKS-AsyncTask";

    Exception error;

    @Override
    protected String[] doInBackground(Void... params) {
        try {
            return doWork();
        } catch (Exception ex) {
            error = ex;
            Log.e(TAG, "Error: " + ex.getMessage(), ex);
            return null;
        }
    }

    protected abstract String[] doWork() throws Exception;

    @Override
    protected void onPostExecute(String[] result) {
        try {
            updateUI(result);
        } catch (Exception ex) {
            error = ex;
            Log.d(TAG, "UpdateUI Failed: " + error.getMessage());
        }
    }

    protected abstract void updateUI(String[] result);


}
