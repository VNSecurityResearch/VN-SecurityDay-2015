package com.hptsec.vulnlab.View.M5SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyColorHandler;

public class M5Case1Fragment extends SherlockFragment {

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M5Case1Fragment newInstance(int position) {
		M5Case1Fragment f = new M5Case1Fragment();
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
				R.layout.fragment_m5case1_redundancypermissiongranted,
				container, false);

		TextView txtM5C1Result = (TextView) view
				.findViewById(R.id.txtM5C1Result);

		TextView txtM5C1VulnDescription = (TextView) view
				.findViewById(R.id.txtM5C1VulnDescription);

		txtM5C1Result
				.setText("There is no way to exclude app permission\nunless you don't install them!");
		txtM5C1Result.setTextColor(MyColorHandler.getResultColor());

		txtM5C1VulnDescription
				.setText(getActivity().getResources().getString(R.string.m5case1_vulndescription));

		txtM5C1VulnDescription.setTextColor(MyColorHandler
				.getVulnDescriptionColor());

		return view;
	}

}
