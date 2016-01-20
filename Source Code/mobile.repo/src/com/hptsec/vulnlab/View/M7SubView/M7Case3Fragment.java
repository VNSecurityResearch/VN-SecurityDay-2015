package com.hptsec.vulnlab.View.M7SubView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
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
import com.hptsec.vulnlab.Controller.FileHandleProvider;
import com.hptsec.vulnlab.Utilities.MyColorHandler;

/**
 * M7Case 3 - Path Manipulation
 * 
 * @author whitehatpanda
 * 
 */
public class M7Case3Fragment extends SherlockFragment {

	private static String TAG = "M7Case3 - Path Manipulation Fragment";

	private static final String ARG_POSITION = "position";

	private int position;

	public static M7Case3Fragment newInstance(int position) {
		M7Case3Fragment f = new M7Case3Fragment();
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
				R.layout.fragment_m7case3_pathmanipulation, container, false);

		final TextView txtM7C3Result = (TextView) view
				.findViewById(R.id.txtM7C3Result);

		final TextView txtM7C3VulnDescription = (TextView) view
				.findViewById(R.id.txtM7C3VulnDescription);

		final TextView txtM7C3LFIData = (TextView) view
				.findViewById(R.id.txtM7C3PathReturn);

		final EditText editUri = (EditText) view.findViewById(R.id.editM7C3Uri);

		Button btnM7C3TraversalMe = (Button) view
				.findViewById(R.id.btnM7C3TraversalMe);

		btnM7C3TraversalMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String myUri = editUri.getText().toString();

				if (myUri.length() > 0) {
					try {
						ParcelFileDescriptor myFilePointer = getActivity()
								.getContentResolver()
								.openFileDescriptor(
										Uri.parse(FileHandleProvider.PROVIDER_URI
												.toString() + myUri), "r");
						// When call this function, if destination file is
						// locate on file system, the READ_MODE must be
						// MODE_READ_ONLY
						Toast.makeText(
								getActivity().getBaseContext(),
								"Your Uri: "
										+ FileHandleProvider.PROVIDER_URI
												.toString() + myUri,
								Toast.LENGTH_SHORT).show();
						if (myFilePointer != null) {
							Log.d(TAG,
									"Got ParcelFileDescritor... get it contains!");

							InputStream myStream = new FileInputStream(
									myFilePointer.getFileDescriptor());

							String fileData = ReadDataFromStream(myStream);

							txtM7C3LFIData.setText(fileData);
							txtM7C3LFIData.setTextColor(Color.RED);

							txtM7C3Result.setText("Read file successful!");
							txtM7C3Result.setTextColor(MyColorHandler
									.getResultColor());

							txtM7C3VulnDescription
									.setText(getResources().getString(
											R.string.m7case3_vulndescription));
							txtM7C3VulnDescription.setTextColor(MyColorHandler
									.getVulnDescriptionColor());

						} else {
							txtM7C3LFIData.setText("File Not Found!");
							txtM7C3Result.setText("");
							txtM7C3VulnDescription.setText("");
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.d(TAG, "Read From InputSteam Failed!");
					}
				} else {
					Toast.makeText(getActivity().getBaseContext(),
							"Enter your File Path!", Toast.LENGTH_SHORT).show();
					txtM7C3Result.setText("");
					txtM7C3VulnDescription.setText("");
				}
			}

			private String ReadDataFromStream(InputStream myStream)
					throws IOException {
				// TODO Auto-generated method stub

				if (myStream != null) {
					InputStreamReader inputStreamReader = new InputStreamReader(
							myStream);
					BufferedReader bufferedReader = new BufferedReader(
							inputStreamReader);
					String receiveString = "";
					StringBuilder stringBuilder = new StringBuilder();

					while ((receiveString = bufferedReader.readLine()) != null) {
						stringBuilder.append(receiveString);
					}
					myStream.close();
					return stringBuilder.toString();
				}
				return null;
			}
		});

		return view;
	}
}
