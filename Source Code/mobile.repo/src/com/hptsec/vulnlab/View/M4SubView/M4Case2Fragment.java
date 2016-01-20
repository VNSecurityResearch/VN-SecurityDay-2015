package com.hptsec.vulnlab.View.M4SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

public class M4Case2Fragment extends SherlockFragment {
	private static String TAG = "M4Case1 - App/Web Caches Data Leak";

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M4Case2Fragment newInstance(int position) {
		M4Case2Fragment f = new M4Case2Fragment();
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
				R.layout.fragment_m4case2_informationleakthroughlogs,
				container, false);


		return view;
	}
}
