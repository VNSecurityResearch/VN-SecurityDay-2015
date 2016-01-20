package com.hptsec.vulnlab.View.M2SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

/**
 * M2 Introduce
 * 
 * @author whitehatpanda
 */
public class M2IntroduceFragment extends SherlockFragment {
	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M2IntroduceFragment newInstance(int position) {
		M2IntroduceFragment f = new M2IntroduceFragment();
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

		View view = inflater.inflate(R.layout.fragment_m2introduce, container,
				false);
		TextView txtIntroduce = (TextView) view
				.findViewById(R.id.txtIntroduceData);
		txtIntroduce
				.setText("Insecure data storage, vulnerabilities occurs when development teams assume that users or malware will not have access to a mobile device's filesystem and subsequent sensitive information in data-stores on the device. Filesystems are easily accessible. Organizations should expect a malicious user or malware to inspect sensitive data stores. Rooting or jailbreaking a mobile device circumvents any encryption protections. When data is not protected properly, specialized tools are all that is needed to view application data.");

		return view;
	}
}
