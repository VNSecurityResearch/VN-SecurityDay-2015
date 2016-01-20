package com.hptsec.vulnlab.View.M5SubView;

import java.io.IOException;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyColorHandler;
import com.hptsec.vulnlab.Utilities.MyHttpClientHandler;

public class M5Case2Fragment extends SherlockFragment {

	private static String TAG = "M5Case2 - Use spoof-able value to authenticating users";

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M5Case2Fragment newInstance(int position) {
		M5Case2Fragment f = new M5Case2Fragment();
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

		View view = inflater
				.inflate(
						R.layout.fragment_m5case2_usespoofablevalueforauthenticatinguser,
						container, false);

		final TextView txtM5C2Result = (TextView) view
				.findViewById(R.id.txtM5C2Result);

		final TextView txtM5C2VulnDescription = (TextView) view
				.findViewById(R.id.txtM5C2VulnDescription);

		Button btnM5C2ImeiMe = (Button) view
				.findViewById(R.id.btnM5C2ImeiAuthen);

		btnM5C2ImeiMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TelephonyManager telephonyManager = (TelephonyManager) getActivity()
						.getSystemService(Context.TELEPHONY_SERVICE);

				String serverUrl = "http://api.securityinone.com/m5case2.php?auth=";

				String myImei = telephonyManager.getDeviceId();

				try {
					String result = MyHttpClientHandler
							.sendGetRequestByUrl(serverUrl + myImei);

					txtM5C2Result
							.setText("Imei sent! Never use device id to authenticating user!");
					txtM5C2Result.setTextColor(MyColorHandler.getResultColor());

					txtM5C2VulnDescription.setText(getActivity().getResources()
							.getString(R.string.m5case2_vulndescription));

					Log.d(TAG, result);

				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return view;
	}

}
