package com.hptsec.vulnlab.View.M2SubView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Controller.M2Case2SaveSqliteController;
import com.hptsec.vulnlab.Model.M2LoginUserModel;
import com.hptsec.vulnlab.Utilities.MyColorHandler;
import com.hptsec.vulnlab.Utilities.MyMessageHandler;

public class M2Case2Fragment extends SherlockFragment {
	private static final String ARG_POSITION = "position";

	private int position;

	public static M2Case2Fragment newInstance(int position) {
		M2Case2Fragment f = new M2Case2Fragment();
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

		View view = inflater.inflate(R.layout.fragment_m2case2_sqlitedb,
				container, false);
		final TextView txtC2InsertResult = (TextView) view
				.findViewById(R.id.txtC2Result);
		final TextView txtC2VulnDescription = (TextView) view
				.findViewById(R.id.txtC2VulnDescription);
		final EditText editUsername = (EditText) view
				.findViewById(R.id.txtC2Username);
		final EditText editPassword = (EditText) view
				.findViewById(R.id.txtC2Password);
		Button btnC2SaveSqlite = (Button) view
				.findViewById(R.id.btnC2SaveInternal);

		final M2Case2SaveSqliteController db = new M2Case2SaveSqliteController(
				getActivity()); // In activity context

		btnC2SaveSqlite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Hangle click on btn Save, get info and put them into DB via
				// sqlite library
				// Solution Here: Must be encrypt sensitive information in
				// sqlitedb.
				M2LoginUserModel userinfo = new M2LoginUserModel();
				if (editUsername.getText().toString().length() > 0
						&& editPassword.getText().toString().length() > 0) {
					userinfo.setUsername(editUsername.getText().toString());
					userinfo.setPassword(editPassword.getText().toString());
					Log.d("M2-Case 2: Inserting user ", userinfo.getUsername());
					if (db.AddUser(userinfo)) {
						// Insert successful
						txtC2InsertResult
								.setText("Insert User/Pass successful! This is an insecure way!");
						txtC2InsertResult.setTextColor(MyColorHandler
								.getResultColor());
						txtC2VulnDescription
								.setText(getResources().getString(R.string.m2case2_vulndescription));
						txtC2VulnDescription.setTextColor(MyColorHandler
								.getVulnDescriptionColor());
						editUsername.setText("");
						editPassword.setText("");
					}
				} else { // Null username, password
					txtC2InsertResult.setText(MyMessageHandler
							.getNullCredentialsMessage());
					txtC2InsertResult.setTextColor(MyColorHandler
							.getResultColor());
					txtC2VulnDescription.setText("");
				}
			}
		});

		return view;
	}

}
