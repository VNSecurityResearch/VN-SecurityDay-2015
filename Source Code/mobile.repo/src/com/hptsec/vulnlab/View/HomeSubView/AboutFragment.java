package com.hptsec.vulnlab.View.HomeSubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

public class AboutFragment extends SherlockFragment {

	private static final String ARG_POSITION = "position";

	private int position;

	public static AboutFragment newInstance(int position) {
		AboutFragment f = new AboutFragment();
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

		View view = inflater.inflate(R.layout.fragment_project_about,
				container, false);

		final TextView txtProjectDescription = (TextView) view
				.findViewById(R.id.txtProjectDescription);

		final TextView txtYear = (TextView) view.findViewById(R.id.txtYear);

		final TextView txtAuthor = (TextView) view.findViewById(R.id.txtAuthor);

		txtAuthor.setText(getActivity().getString(R.string.project_author));

		txtYear.setText("2015");

		return view;
	}
}
