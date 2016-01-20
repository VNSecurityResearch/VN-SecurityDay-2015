package com.hptsec.vulnlab.View.M4SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

public class M4IntroduceFragment extends SherlockFragment {

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M4IntroduceFragment newInstance(int position) {
		M4IntroduceFragment f = new M4IntroduceFragment();
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

		View view = inflater.inflate(R.layout.fragment_m4introduce, container,
				false);

		TextView txtM4Introduce = (TextView) view
				.findViewById(R.id.txtM4IntroduceData);

		String vulnDescription = "Unintended data leakage occurs when a developer inadvertantly places sensitive information or data in a location on the mobile device that is easily accessible by other apps on the device. First, a developer's code processes sensitive information supplied by the user or the backend. During that processing, a side-effect (that is unknown to the developer) results in that information being placed into an insecure location on the mobile device that other apps on the device may have open access to. Typically, these side-effects originate from the underlying mobile device's operating system (OS). This will be a very prevalent vulnerability for code produced by a developer that does not have intimate knowledge of how that information can be stored or processed by the underlying OS. It is easy to detect data leakage by inspecting all mobile device locations that are accessible to all apps for the app's sensitive information.";

		txtM4Introduce.setText(vulnDescription);

		return view;
	}
}
