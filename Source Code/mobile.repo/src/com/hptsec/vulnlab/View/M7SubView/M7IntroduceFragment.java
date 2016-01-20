package com.hptsec.vulnlab.View.M7SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

public class M7IntroduceFragment extends SherlockFragment {
	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M7IntroduceFragment newInstance(int position) {
		M7IntroduceFragment f = new M7IntroduceFragment();
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

		View view = inflater.inflate(R.layout.fragment_m7introduce, container,
				false);

		TextView txtM7Introduce = (TextView) view
				.findViewById(R.id.txtM7IntroduceData);

		String vulnDescription = "Client-side injection results in the execution of malicous code on the mobile device via the mobile app. Typically, this malicious code is provided in the form of data that the threat agent inputs to the mobile app through a number of differnt means. The data is malformed and is processed (like all other data) by the underlying frameworks supporting the mobile app. During processing, this special data is forces a context switch and the framework reinterprets the data as executable code. The code is malicious in nature and executed by the app.";

		txtM7Introduce.setText(vulnDescription);
		
		return view;
	}
}
