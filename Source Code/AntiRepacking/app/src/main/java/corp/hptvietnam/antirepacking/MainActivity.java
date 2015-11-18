package corp.hptvietnam.antirepacking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignatureChecker checker = new SignatureChecker(this);
        // Log.d("AntiRepacking", checker.getSignature());
        if (checker.isModified()) {
            Log.d("AntiRepacking-Exited", "Wrong Signature: " + checker.getSignature());
            Log.d("AntiRepacking-Exited", "Expected Signature is: " + checker.getExpectedSig());
            System.exit(0);
        }
        else {
            Toast.makeText(this,"Congraz! You are validated!", Toast.LENGTH_SHORT).show();
        }
    }
}
