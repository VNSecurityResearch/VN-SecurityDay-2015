/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * @Thanked to author
 * @Developer: whitehatpanda
 */

package com.hptsec.vulnlab.View.M2SubView;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyColorHandler;
import com.hptsec.vulnlab.Utilities.MyMessageHandler;

public class M2Case1Fragment extends SherlockFragment {

	/*
	 * My Variable Region
	 */
	public static final String MySharedPreferences = "MyS3cr3tPrefs";
	public static final String MySecret = "S3cr3tKey";
	SharedPreferences sharedPreferences;

	private static final String ARG_POSITION = "position";
	private int position;

	public static M2Case1Fragment newInstance(int position) {
		M2Case1Fragment f = new M2Case1Fragment();
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
				R.layout.fragment_m2case1_sharedpreferences, container, false);
		final TextView txtC1SaveResult = (TextView) view
				.findViewById(R.id.txtC1Result);
		final TextView txtC1VulnDescription = (TextView) view
				.findViewById(R.id.txtC1VulnDescription);
		final EditText editSecretKey = (EditText) view
				.findViewById(R.id.txtC1Secret);
		Button btnC1SaveSecret = (Button) view
				.findViewById(R.id.btnC1SaveSecret);

		btnC1SaveSecret.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Call SharePreferences to store data in editSecretKey
				sharedPreferences = getActivity().getSharedPreferences(
						MySharedPreferences, Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				String strSecret = editSecretKey.getText().toString();
				if (!strSecret.isEmpty()) {
					editor.putString(MySecret, strSecret);
					editor.commit();
					txtC1SaveResult
							.setText("Save Successful! Plz carefully store your s3cr3t!!");
					txtC1SaveResult.setTextColor(MyColorHandler
							.getResultColor());

					// Show description
					txtC1VulnDescription
							.setText(getResources().getString(R.string.m2case1_vulndescription));
					txtC1VulnDescription.setTextColor(MyColorHandler
							.getVulnDescriptionColor());
					editSecretKey.setText("");
				} else {
					txtC1SaveResult.setText(MyMessageHandler
							.getNullCredentialsMessage());
					txtC1SaveResult.setTextColor(MyColorHandler
							.getResultColor());
					txtC1VulnDescription.setText("");
				}

			}
		});

		return view;
	}

}