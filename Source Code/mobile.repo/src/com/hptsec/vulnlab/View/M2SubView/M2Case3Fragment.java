package com.hptsec.vulnlab.View.M2SubView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
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
import com.hptsec.vulnlab.Utilities.MyMessageHandler;

/**
 * M2 Case 3 - Internal Storage
 * 
 * @author whitehatpanda
 * 
 */
public class M2Case3Fragment extends SherlockFragment {

	String FILE_NAME = "MyInternalFile";
	String MY_CACHE_FILE = "MyCacheFile";
	File tempFile;

	private static final String ARG_POSITION = "position";

	private int position;

	public static M2Case3Fragment newInstance(int position) {
		M2Case3Fragment f = new M2Case3Fragment();
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
				R.layout.fragment_m2case3_internalstrorage, container, false);
		final EditText editUsername = (EditText) view
				.findViewById(R.id.txtC3Username);
		final EditText editPassword = (EditText) view
				.findViewById(R.id.txtC3Password);
		final TextView txtC3Result = (TextView) view
				.findViewById(R.id.txtC3Result);
		final TextView txtC3VulnDescription = (TextView) view
				.findViewById(R.id.txtC3VulnDescription);
		Button btnSaveInternal = (Button) view
				.findViewById(R.id.btnC3SaveInternal);
		Button btnSaveRawResource = (Button) view
				.findViewById(R.id.btnC3SaveCacheFile);

		// Button save internal click event
		btnSaveInternal.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = "Android Internal Storage With World Readable\nUsername:"
						+ editUsername.getText().toString() + "\n";
				String password = "Password:"
						+ editPassword.getText().toString() + "\n";
				if (editUsername.getText().toString().length() > 0
						&& editPassword.getText().toString().length() > 0) {
					try {
						FileOutputStream fos = getActivity().openFileOutput(
								FILE_NAME, Context.MODE_WORLD_READABLE);
						fos.write(username.getBytes());
						fos.write(password.getBytes());
						fos.close();
						// Save successful -> setText
						txtC3Result
								.setText("Save successful! MODE_WORLD_READABLE");
						txtC3Result.setTextColor(MyColorHandler
								.getResultColor());
						txtC3VulnDescription
								.setText("You can save files directly on the device's internal storage. By default, files saved to the internal storage are private to your application and other applications cannot access them (nor can the user). When the user uninstalls your application, these files are removed.\nDO NOT STORE SENSITIVE INFORMATION VIA INTERNAL STORAGE INSTEAD OF MODE PRIVATE!");
						txtC3VulnDescription.setTextColor(MyColorHandler
								.getVulnDescriptionColor());

						editUsername.setText("");
						editPassword.setText("");

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						Log.d("OpenFileOutPut", "Open failled");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					txtC3Result.setText(MyMessageHandler
							.getNullCredentialsMessage());
					txtC3Result.setTextColor(MyColorHandler.getResultColor());
					txtC3VulnDescription.setText("");
				}
			}

		});

		// Button save raw resource click event
		btnSaveRawResource.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = "Android Cache/Temporary File\nUsername:"
						+ editUsername.getText().toString() + "\n";
				String password = "Password:"
						+ editPassword.getText().toString() + "\n";
				if (editUsername.getText().toString().length() > 0
						&& editPassword.getText().toString().length() > 0) {
					// Handle for save cache file
					/* Getting cache directory */

					File cacheDir = getActivity().getBaseContext()
							.getCacheDir();
					/* Getting a reference to temporary file, if created earlier */
					tempFile = new File(cacheDir.getPath() + "/"
							+ MY_CACHE_FILE);
					tempFile.setWritable(true);
					FileWriter writer = null;
					try {
						writer = new FileWriter(tempFile);
						/** Saving the contents to the file */
						writer.write(username);
						writer.write(password);
						/** Closing the writer object */
						writer.close();
						Log.d("Writing Cache File: ", tempFile.getPath());

						Toast.makeText(
								getActivity().getBaseContext(),
								"Cache file saved contents in "
										+ tempFile.getPath(), Toast.LENGTH_LONG)
								.show();

						editUsername.setText("");
						editPassword.setText("");

						// Show result
						txtC3Result
								.setText("Save cache file successful!! MODE_WORLD_WRITABLE!");
						txtC3Result.setTextColor(MyColorHandler
								.getResultColor());

						txtC3VulnDescription
								.setText(getResources().getString(R.string.m2case3_vulndescription));
						txtC3VulnDescription.setTextColor(MyColorHandler
								.getVulnDescriptionColor());

					} catch (IOException e) {
						// e.printStackTrace();
						Log.d("Write Cache File: ", "Failed");
					}

				} else {
					txtC3Result.setText(MyMessageHandler
							.getNullCredentialsMessage());
					txtC3Result.setTextColor(MyColorHandler.getResultColor());
					txtC3VulnDescription.setText("");
					;
				}
			}
		});

		return view;
	}

}
