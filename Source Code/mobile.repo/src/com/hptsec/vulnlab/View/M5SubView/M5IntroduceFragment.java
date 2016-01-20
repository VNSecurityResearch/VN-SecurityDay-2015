package com.hptsec.vulnlab.View.M5SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

public class M5IntroduceFragment extends SherlockFragment {

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M5IntroduceFragment newInstance(int position) {
		M5IntroduceFragment f = new M5IntroduceFragment();
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

		View view = inflater.inflate(R.layout.fragment_m5introduce, container,
				false);

		TextView txtM5Introduce = (TextView) view
				.findViewById(R.id.txtM5IntroduceData);

		String vulnDescription = "Poor or missing authentication schemes allow an adversary to anonymously execute functionality within the mobile app or backend server used by the mobile app. Weaker authentication for mobile apps is fairly prevalent due to a mobile device's input form factor. The form factor highly encourages short passwords that are often purely based on 4-digit PINs. Authentication requirements for mobile apps can be quite different to traiditonal web authentication schemes due to availability requirements. In traditional web apps, users are exepcted to be online and authenticate in realtime with a backend server. Throughout their session, there is a reasonable expectation that they will have continuous access to the Internet.";

		txtM5Introduce.setText(vulnDescription);

		return view;
	}

}
