package com.hptsec.vulnlab.View.HomeSubView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TutorialsFragment extends SherlockFragment {
	private static final String ARG_POSITION = "position";

	private int position;

	public static TutorialsFragment newInstance(int position) {
		TutorialsFragment f = new TutorialsFragment();
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

		View view = inflater.inflate(R.layout.fragment_project_tutorials,
				container, false);

		return view;
	}
}
