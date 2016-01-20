package com.hptsec.vulnlab.View.M8SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

public class M8IntroduceFragment extends SherlockFragment {
	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M8IntroduceFragment newInstance(int position) {
		M8IntroduceFragment f = new M8IntroduceFragment();
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

		View view = inflater.inflate(R.layout.fragment_m8introduce, container,
				false);

		TextView txtM8Introduce = (TextView) view
				.findViewById(R.id.txtM8IntroduceData);

		String vulnDescription = "M8 Introduce - ";

		txtM8Introduce.setText(vulnDescription);

		return view;
	}
}
