package com.hptsec.vulnlab.View.M4SubView;

import java.io.File;
import java.io.FileWriter;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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

public class M4Case1Fragment extends SherlockFragment {
	private static String TAG = "M4Case1 - App/Web Caches Data Leak";

	File tempFile;
	File cacheDir = null;

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M4Case1Fragment newInstance(int position) {
		M4Case1Fragment f = new M4Case1Fragment();
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
				R.layout.fragment_m4case1_appwebcachesdataleak, container,
				false);

		final TextView txtM4C1Result = (TextView) view
				.findViewById(R.id.txtM4C1Result);

		final TextView txtM4C1VulnDescription = (TextView) view
				.findViewById(R.id.txtM4C1VulnDescription);

		Button btnM4C1StoreCache = (Button) view
				.findViewById(R.id.btnM4C1StoreCache);

		btnM4C1StoreCache.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TelephonyManager telephonyManager = (TelephonyManager) getActivity()
						.getSystemService(Context.TELEPHONY_SERVICE);

				// Get IMEI and cache them
				String myImei = telephonyManager.getDeviceId();

				cacheDir = getActivity().getBaseContext().getCacheDir();

				FileWriter writer = null;

				if (myImei.length() > 0 && cacheDir != null) {
					tempFile = new File(cacheDir.getPath() + "/"
							+ "myM4Case1.cache");
					try {
						writer = new FileWriter(tempFile);
						writer.write("My Cache Imei: " + myImei);
						writer.close();

						Toast.makeText(getActivity().getBaseContext(),
								"Write Cache successful!", Toast.LENGTH_SHORT)
								.show();

						txtM4C1Result.setText("Saved to: "
								+ tempFile.getAbsolutePath());
						txtM4C1Result.setTextColor(MyColorHandler
								.getResultColor());

						txtM4C1VulnDescription.setText(getActivity()
								.getResources().getString(
										R.string.m4case1_vulndescription));
						txtM4C1VulnDescription.setTextColor(MyColorHandler
								.getVulnDescriptionColor());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

			}
		});

		return view;
	}
}
