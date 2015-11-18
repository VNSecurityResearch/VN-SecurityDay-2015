package corp.hptvietnam.callsecretactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "CallSecureActivity";

    Button btnActivity;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivity = (Button) findViewById(R.id.btnStart);

        txtResult = (TextView) findViewById(R.id.txtResult);

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent secretActivity = new Intent();
                    secretActivity.setAction("corp.hptvietnam.secureactivity.VIEW_SECRET");
                    secretActivity.setClassName("corp.hptvietnam.secureactivity", "corp.hptvietnam.secureactivity.SecretActivity");
                    startActivity(secretActivity);
                } catch (Exception ex) {
                    txtResult.setText(ex.getMessage().toString());
                    ex.printStackTrace();
                }
            }
        });
    }
}
