package com.hptsec.vulnlab.View.M2SubView;

import javax.crypto.spec.SecretKeySpec;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyAESAlgorithmHandler;
import com.hptsec.vulnlab.Utilities.MyBase64Handler;
import com.hptsec.vulnlab.Utilities.MyColorHandler;
import com.hptsec.vulnlab.Utilities.MyMessageHandler;

/**
 * Store Encryption Key Locally
 * @author whitehatpanda
 *
 */
public class M2Case5Fragment extends SherlockFragment {
	/*
	 * My Variable Region
	 */
	static final String MyEncryptedSharedPreferences = "MyEncryptedPref";
	static final String MyEncryptionSharedPreferences = "MyEncryptionKeyPref";
	static final String MyEncryptionKey = "EncryptedKey";
	SharedPreferences sharedPreferences;
	static final String TAG = "SymmetricAlgorithmAES";

	private static final String ARG_POSITION = "position";
	private int position;

	public static M2Case5Fragment newInstance(int position) {
		M2Case5Fragment f = new M2Case5Fragment();
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
				R.layout.fragment_m2case5_storeencryptionkeylocally, container,
				false);
		final TextView txtC5Result = (TextView) view
				.findViewById(R.id.txtC5Result);
		final TextView txtC5VulnDescription = (TextView) view
				.findViewById(R.id.txtC5VulnDescription);
		final EditText editDataStore = (EditText) view
				.findViewById(R.id.txtC5DataStorage);
		Button btnC5SaveEncryptDataWithKey = (Button) view
				.findViewById(R.id.btnC5SaveWithKey);

		btnC5SaveEncryptDataWithKey.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Call SharePreferences to store data in editSecretKey
				String strData = editDataStore.getText().toString();

				if (strData.length() > 0) {
					// Encrypt data & store key on internal storage
					// Set up secret key spec for 128-bit AES encryption and
					// decryption
					SecretKeySpec sks = MyAESAlgorithmHandler
							.getAESSecretKeySpec();

					sharedPreferences = getActivity()
							.getSharedPreferences(
									MyEncryptionSharedPreferences,
									Context.MODE_PRIVATE); // Random encryption
															// key each
															// running time
					Editor editor = sharedPreferences.edit();
					editor.putString(MyEncryptionKey, MyBase64Handler
							.encodeByteArrayToBase64(sks.getEncoded()));
					editor.commit();

					// Encrypt the original data with AES
					byte[] encryptedBytes = MyAESAlgorithmHandler.encryptData(
							strData.getBytes(), sks);

					String encryptedData = MyBase64Handler
							.encodeByteArrayToBase64(encryptedBytes);

					// Write encrypted data to sharedpreferences
					sharedPreferences = getActivity().getSharedPreferences(
							MyEncryptedSharedPreferences, Context.MODE_PRIVATE);
					editor = sharedPreferences.edit();
					editor.putString(MyEncryptedSharedPreferences,
							encryptedData);
					editor.commit();

					Toast.makeText(
							getActivity().getBaseContext(),
							"Your provided message was encrypted using my AES algorithm with key length: "
									+ sks.getEncoded().length,
							Toast.LENGTH_LONG).show();

					txtC5Result
							.setText("Store encrypted data successful! NEVER STORE ENCRYPTION KEY LOCALLY!!!");
					txtC5Result.setTextColor(MyColorHandler.getResultColor());
					txtC5VulnDescription
							.setText(getResources().getString(R.string.m2case5_vulndescription));
					txtC5VulnDescription.setTextColor(MyColorHandler
							.getVulnDescriptionColor());

					// Make sure that we can decrypt from the encoded key +
					// encoded encrypted message
					// Double check phare

					sharedPreferences = getActivity().getSharedPreferences(
							MyEncryptedSharedPreferences, Context.MODE_PRIVATE);

					byte[] decodedEncryptedCode = Base64.decode(
							sharedPreferences.getString(
									MyEncryptedSharedPreferences, ""),
							Base64.DEFAULT);

					if (decodedEncryptedCode.length > 0) {
						sharedPreferences = getActivity().getSharedPreferences(
								MyEncryptionSharedPreferences,
								Context.MODE_PRIVATE);

						String encodedKey = sharedPreferences.getString(
								MyEncryptionKey, "");

						if (encodedKey != "") {
							byte[] decodedKey = Base64.decode(encodedKey,
									Base64.DEFAULT);

							sks = new SecretKeySpec(decodedKey, "AES");
							byte[] finalData = MyAESAlgorithmHandler
									.decryptData(decodedEncryptedCode, sks);
							if (finalData != null) {
								Toast.makeText(
										getActivity().getBaseContext(),
										"Your data after decryption phare: "
												+ new String(finalData),
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(getActivity().getBaseContext(),
										"Decryption failed,", Toast.LENGTH_LONG)
										.show();
							}
						}
					}

				} else {
					txtC5Result.setText(MyMessageHandler
							.getNullCredentialsMessage());
					txtC5Result.setTextColor(MyColorHandler.getResultColor());
					txtC5VulnDescription.setText("");
				}
			}
		});

		return view;
	}
}
