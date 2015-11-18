package corp.hptvietnam.androidkeystore;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import corp.hptvietnam.androidkeystore.android.security.KeyStore;
import corp.hptvietnam.androidkeystore.android.security.KeyStoreJb43;
import corp.hptvietnam.androidkeystore.android.security.KeyStoreKk;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "AndroidKeyStore-Demo";

    private static final boolean IS_JB43 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    private static final boolean IS_JB = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    private static final boolean IS_KK = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

    public static final String OLD_UNLOCK_ACTION = "android.credentials.UNLOCK";

    public static final String UNLOCK_ACTION = "com.android.credentials.UNLOCK";
    public static final String RESET_ACTION = "com.android.credentials.RESET";

    //private static final String KEY_NAME = "aes_key";
    //private static final String RSA_KEY_NAME = "rsa_key";
    //private static final String EC_KEY_NAME = "ec_key";

    private static final String KEYNAME = "SECRETKEY";

    EditText editInput;
    TextView txtEncResult;
    Button btnEncrypt, btnDecrypt, btnResetKS;

    KeyStore ks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVariables();

        if (IS_KK) {
            ks = KeyStoreKk.getInstance();
        } else if (IS_JB43) {
            ks = KeyStoreJb43.getInstance();
        } else {
            ks = KeyStore.getInstance();
        }
    }

    private void setVariables() {
        editInput = (EditText) findViewById(R.id.editInput);

        txtEncResult = (TextView) findViewById(R.id.txtEncResult);

        btnEncrypt = (Button) findViewById(R.id.btnEncrypt);
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ks.state() != KeyStore.State.UNLOCKED) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Keystore is locked or not initialized. Retry operation "
                                    + "after unlock activity returns.",
                            Toast.LENGTH_LONG).show();
                    unlock();
                } else {
                    String inputString = editInput.getText().toString();
                    Log.d(TAG, "Btn Encrypt clicked");
                    encryptWithAES(inputString);
                }
            }
        });

        btnDecrypt = (Button) findViewById(R.id.btnDecrypt);
        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtEncResult.getText() == null || txtEncResult.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "No encrypted text found.", Toast.LENGTH_SHORT).show();
                    return;
                }
                decryptWithAES();
            }
        });

        btnResetKS = (Button) findViewById(R.id.btnResetKeyStore);
        btnResetKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    Toast.makeText(getApplicationContext(), "Reset not supported on pre-ICS devices", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(RESET_ACTION));
                }
            }
        });
    }



    private void decryptWithAES() {
        final String cipherText = txtEncResult.getText().toString();
        new KeyStoreTask() {

            @Override
            protected String[] doWork() throws Exception {
                try {
                    byte[] keyBytes = ks.get(KEYNAME);
                    if (keyBytes == null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Error: " + "EncryptionKey not found!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        return null;
                    }
                    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
                    String plaintext = Crypto.decryptAesCbc(cipherText, key);

                    return new String[]{ plaintext };
                } catch (final Exception ex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    return new String[] { "Error" + ex };
                }
            }

            @Override
            protected void updateUI(String[] result) {
                if (result.length > 0) {
                    txtEncResult.setText(result[0]);
                }
            }
        }.execute();
    }

    private void encryptWithAES(final String inputString) {
        new KeyStoreTask() {

            @Override
            protected String[] doWork() throws Exception {
                SecretKey key = Crypto.generateAesKey();
                String encryptionKeyName = KEYNAME;
                boolean success = ks.put(encryptionKeyName, key.getEncoded());
                Log.d(TAG, "Put key success: " + success);
                if (!inputString.equals("")) {
                    String ciphertext = Crypto.encryptAesCbc(inputString, key);
                    return new String[] { ciphertext };
                }
                return null;
            }

            @Override
            protected void updateUI(String[] result) {
                txtEncResult.setText(result[0]);
            }
        }.execute();
    }

    private void unlock() {
        if (ks.state() == KeyStore.State.UNLOCKED) {
            return;
        }

        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                startActivity(new Intent(OLD_UNLOCK_ACTION));
            } else {
                startActivity(new Intent(UNLOCK_ACTION));
            }
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "No UNLOCK activity: " + e.getMessage(), e);
            Toast.makeText(this, "No keystore unlock activity found.",
                    Toast.LENGTH_SHORT).show();

            return;
        }
    }
}
