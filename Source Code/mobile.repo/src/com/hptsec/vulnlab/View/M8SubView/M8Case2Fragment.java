package com.hptsec.vulnlab.View.M8SubView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

/***
 * M8Case2 - Activity Hijacking
 * 
 * @author whitehatpanda
 * 
 */
public class M8Case2Fragment extends SherlockFragment {

	private static String TAG = "M8Case1 - Activity Hijacking";

	private static final String ARG_POSITION = "position";
	private int position;

	public static M8Case2Fragment newInstance(int position) {
		M8Case2Fragment f = new M8Case2Fragment();
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
				R.layout.fragment_m8case2_activityhijacking, container, false);

		Button btnStartActivity = (Button) view
				.findViewById(R.id.btnM8C2CallImplicitActivity);

		btnStartActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.hptsec.vulnlab.implicitintent");
				intent.putExtra("topSecret", "s3cr3t!@#");
				// Verify that the intent will resolve to an activity
				if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
					startActivity(intent);
				} else {
					Log.d(TAG, "No Intent!");
				}
			}
		});

		return view;
	}

}
