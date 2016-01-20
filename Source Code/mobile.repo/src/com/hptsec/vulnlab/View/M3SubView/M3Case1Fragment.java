package com.hptsec.vulnlab.View.M3SubView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyColorHandler;
import com.hptsec.vulnlab.Utilities.MyHttpClientBuilderForPinning;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class M3Case1Fragment extends SherlockFragment {
	private static String TAG = "M3Case1 - Lack of Certificate Pinning (Inspection)";

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M3Case1Fragment newInstance(int position) {
		M3Case1Fragment f = new M3Case1Fragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(
				R.layout.fragment_m3case1_certificatepinning_inspection, container,
				false);

		final TextView txtM3C1Result = (TextView) view
				.findViewById(R.id.txtM3C1Result);

		final TextView txtM3C1VulnDescription = (TextView) view
				.findViewById(R.id.txtM3C1VulnDescription);

		Button btnM3C1PinMe = (Button) view
				.findViewById(R.id.btnM3C1PinMe);

		btnM3C1PinMe.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
				Log.v(TAG, "PinMe Button Pressed");
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				builder.setMessage(getRemote("https://api.securityinone.com"));
				builder.create().show();
			}


		});

		return view;
	}

	private String getRemote(String domain) {
		String keystore_pass = "P@ssw0rd";

		String result = "Null";

		InputStream keystore = getResources().openRawResource(R.raw.webserver);

		MyHttpClientBuilderForPinning httpClient = new MyHttpClientBuilderForPinning();
		httpClient.setConnectionTimeout(10000);
		httpClient.setSocketTimeout(60000);
		httpClient.setHttpPort(80);
		httpClient.setHttpsPort(443);
		httpClient.setCookieStore(new BasicCookieStore());

		try {
			httpClient.pinCertificates(keystore, keystore_pass.toCharArray());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream dataStream = null;
		try {
			dataStream = httpClient.build().execute(new HttpGet(domain)).getEntity().getContent();
		} catch (IOException e) {
			result = "MiTM detected!";
			Log.d(TAG, "Certificate Validate Error: No peer certificate!");
		}

		if (dataStream != null) {
			Log.d(TAG, "Successful communicate with remote server.");
			return convertInputStreamToString(dataStream);
		}

		return result;
	}

	private String convertInputStreamToString(InputStream dataStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
		StringBuilder out = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		String line;
		try {
			while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append(newLine);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}
}
