package com.hptsec.vulnlab.View.M2SubView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
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
import com.hptsec.vulnlab.Utilities.MyColorHandler;
import com.hptsec.vulnlab.Utilities.MyInternetConnectionHandler;
import com.hptsec.vulnlab.Utilities.MyJsonCommunicationHandler;
import com.hptsec.vulnlab.Utilities.MyMessageHandler;

/**
 * M2 - Insecure Cookie Storage
 * 
 * @author whitehatpanda
 * 
 */
public class M2Case6Fragment extends SherlockFragment {

	JSONObject jsonobj = new JSONObject();

	private final String REMOTE_URL = "http://api.securityinone.com/m2case6.php";

	private final String MyCookiePref = "MyCookiePref";

	SharedPreferences sharedPref;

	private final String TAG = "M2Case6 - JSON Communication";

	private static final String ARG_POSITION = "position";
	private int position;

	public static M2Case6Fragment newInstance(int position) {
		M2Case6Fragment f = new M2Case6Fragment();
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
				R.layout.fragment_m2case6_insecurecookiestorage, container,
				false);

		final TextView txtC6Result = (TextView) view
				.findViewById(R.id.txtC6Result);
		final TextView txtC6VulnDescription = (TextView) view
				.findViewById(R.id.txtC6VulnDescription);
		final EditText editUsername = (EditText) view
				.findViewById(R.id.txtC6Username);
		final EditText editPassword = (EditText) view
				.findViewById(R.id.txtC6Password);
		final Button btnC6SaveExternal = (Button) view
				.findViewById(R.id.btnC6ConnectServer);

		btnC6SaveExternal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isOnline = MyInternetConnectionHandler
						.isConnected(getSherlockActivity());
				if (isOnline) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							String username = editUsername.getText().toString();
							String password = editPassword.getText().toString();
							// setEnableButton(false);

							if (username.length() > 0 && password.length() > 0) {
								try {
									jsonobj.put("username", username);
									jsonobj.put("password", password);
									// For debugging
									Log.d(TAG, jsonobj.toString());
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Log.d(TAG,
											"Error create json object by username/password");
								}
								// Now lets begin with the server part
								try {

									StringEntity se = new StringEntity(jsonobj
											.toString());
									HttpEntity resultEntity = MyJsonCommunicationHandler
											.sendJsonToRemoteServer(se,
													REMOTE_URL);
									if (resultEntity != null) {
										// Get result from remote server
										InputStream inputstream = resultEntity
												.getContent();

										String resultString = convertStreamToString(inputstream);
										if (!resultString.isEmpty()) {
											// Use shared pref to store this
											// value
											storeSessionCookie(resultString);
											setUIText(true);
										} else {
											setUIText(false);
										}
										inputstream.close();
										// setEnableButton(true);
									}
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
									Log.d(TAG, "Error while processing json...");
								}
							} else {
								txtC6Result.setText(MyMessageHandler
										.getNullCredentialsMessage());
								txtC6Result.setTextColor(MyColorHandler
										.getResultColor());
								txtC6VulnDescription.setText("");
							}
						}

						// private void setEnableButton(final boolean isTrue) {
						// // TODO Auto-generated method stub
						// getActivity().runOnUiThread(new Runnable() {
						//
						// @Override
						// public void run() {
						// // TODO Auto-generated method stub
						// btnC6SaveExternal.setEnabled(isTrue);
						// }
						// });
						// }

						private void storeSessionCookie(
								final String resultString) {
							// TODO Auto-generated method stub
							getActivity().runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
										jsonobj = null;
										jsonobj = new JSONObject(resultString);
									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
									}
									if (jsonobj != null) {
										sharedPref = getActivity()
												.getSharedPreferences(
														MyCookiePref,
														Context.MODE_PRIVATE);
										Editor editor = sharedPref.edit();
										try {
											editor.putString(
													"sessionToken",
													jsonobj.getString("sessionToken"));
											editor.commit();
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							});
						}
					}).start();
				} else // Offline
				{
					Toast.makeText(
							getActivity().getBaseContext(),
							"You are offline. Plz turn on the internet connection!",
							Toast.LENGTH_SHORT).show();
				}

			}

			private void setUIText(final boolean isTrue) {
				// Get back to main thread
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (isTrue) {
							txtC6Result
									.setText("Store session cookie successful!");
							txtC6Result.setTextColor(MyColorHandler
									.getResultColor());
							txtC6VulnDescription
									.setText(getResources().getString(
											R.string.m2case6_vulndescription));
							txtC6VulnDescription.setTextColor(MyColorHandler
									.getVulnDescriptionColor());
							editUsername.setText("");
							editPassword.setText("");
						} else {
							txtC6Result
									.setText("Invalid Credentials! Try again!!!");
							txtC6Result.setTextColor(MyColorHandler
									.getResultColor());
							txtC6VulnDescription.setText("");
						}
					}
				});
			}

			private String convertStreamToString(InputStream inputstream) {
				// TODO Auto-generated method stub
				String line = "";
				StringBuilder total = new StringBuilder();
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						inputstream));
				try {
					while ((line = rd.readLine()) != null) {
						total.append(line);
					}
				} catch (Exception e) {
					Toast.makeText(getActivity(), "Convert Stream Exception",
							Toast.LENGTH_SHORT).show();
				}
				return total.toString();
			}
		});

		return view;
	}
}
