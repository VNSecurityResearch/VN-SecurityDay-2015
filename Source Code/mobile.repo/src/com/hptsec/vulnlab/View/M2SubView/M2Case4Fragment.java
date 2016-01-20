package com.hptsec.vulnlab.View.M2SubView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
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
 * M2 Case 4 - External Storage
 * 
 * @author whitehatpanda
 * 
 */
public class M2Case4Fragment extends SherlockFragment {
	/*
	 * My Variable Region
	 */
	private String EXTERNAL_FILENAME = "MyExternalStorage";
	private String WRITE_ERROR_MESSAGE = "We can't write to your external storage - sdcard. Please check it again!!";

	private static final String ARG_POSITION = "position";
	private int position;

	public static M2Case4Fragment newInstance(int position) {
		M2Case4Fragment f = new M2Case4Fragment();
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

		View view = inflater.inflate(R.layout.fragment_m2case4_externalstorage,
				container, false);

		final TextView txtC4Result = (TextView) view
				.findViewById(R.id.txtC4Result);
		final TextView txtC4VulnDescription = (TextView) view
				.findViewById(R.id.txtC4VulnDescription);
		final EditText editUsername = (EditText) view
				.findViewById(R.id.txtC4Username);
		final EditText editPassword = (EditText) view
				.findViewById(R.id.txtC4Password);
		Button btnC4SaveExternal = (Button) view
				.findViewById(R.id.btnC4SaveExternal);

		final boolean checked = CheckForMediaWritable();
		if (!checked) {
			txtC4Result.setText(WRITE_ERROR_MESSAGE);
			txtC4Result.setTextColor(MyColorHandler.getResultColor());
		}

		btnC4SaveExternal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (checked) {
					String username = editUsername.getText().toString();
					String password = editPassword.getText().toString();
					String data = "Android External Storage\nUsername:"
							+ username + "\nPassword:" + password;
					if (username.length() > 0 && password.length() > 0) {
						File sdCard = Environment.getExternalStorageDirectory();
						try {
							File directory = new File(sdCard.getAbsolutePath()
									+ "/FptVulnApp");
							directory.mkdirs();
							// Create the file
							File myFile = new File(directory, EXTERNAL_FILENAME);
							FileOutputStream fos = new FileOutputStream(myFile);
							fos.write(data.getBytes());
							fos.close();
							Toast.makeText(
									getActivity().getBaseContext(),
									"Temporarily saved contents in "
											+ myFile.getPath(),
									Toast.LENGTH_LONG);
							Log.d("Writing to External Storage", myFile
									.getPath().toString());
							editUsername.setText("");
							editPassword.setText("");

							txtC4Result
									.setText("Save file to sdcard successful! Be careful! They are unsafe!");
							txtC4Result.setTextColor(MyColorHandler
									.getResultColor());
							txtC4VulnDescription
									.setText(getResources().getString(R.string.m2case4_vulndescription));
							txtC4VulnDescription.setTextColor(MyColorHandler
									.getVulnDescriptionColor());

						} catch (IOException e) {
							// TODO Auto-generated catch block
							Log.d("Write External Error",
									"We can't write to your external storage - sdcard. Please check it!!");
							txtC4Result
									.setText("We can't write to your external storage - sdcard. Please check it!!");
							txtC4Result.setTextColor(MyColorHandler
									.getResultColor());
						}

					} else { // Null username or password
						txtC4Result.setText(MyMessageHandler
								.getNullCredentialsMessage());
						txtC4Result.setTextColor(MyColorHandler
								.getResultColor());
						txtC4VulnDescription.setText("");
					}
				} else {
					txtC4Result.setText(WRITE_ERROR_MESSAGE);
					txtC4Result.setTextColor(MyColorHandler.getResultColor());
					txtC4VulnDescription.setText("");
				}
			}
		});

		return view;
	}

	// Check for external storage is avaiable
	private boolean CheckForMediaWritable() {
		// TODO Auto-generated method stub
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

}
