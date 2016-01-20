package com.hptsec.vulnlab.View.M7SubView;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Controller.M7Case1SqlInjectionController;
import com.hptsec.vulnlab.Utilities.MyColorHandler;

public class M7Case1Fragment extends SherlockFragment {

	private static String TAG = "M7Case1 - Sql Injection";

	ArrayAdapter<String> arrAdapter = null;

	private static final String ARG_POSITION = "position";

	private int position;

	public static M7Case1Fragment newInstance(int position) {
		M7Case1Fragment f = new M7Case1Fragment();
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

		View view = inflater.inflate(R.layout.fragment_m7case1_sqlinjection,
				container, false);

		final EditText editSearchNote = (EditText) view
				.findViewById(R.id.txtM7C1Query);

		final TextView txtM7C1Result = (TextView) view
				.findViewById(R.id.txtM7C1Result);
		final TextView txtM7C1VulnDescription = (TextView) view
				.findViewById(R.id.txtM7C1VulnDescription);

		final ListView lstSearchResult = (ListView) view
				.findViewById(R.id.lstM7C1SearchResult);

		final View viewSplitter = (View) view.findViewById(R.id.viewSplitter);

		final M7Case1SqlInjectionController db = new M7Case1SqlInjectionController(
				getActivity());

		Button btnM7C1Search = (Button) view.findViewById(R.id.btnM7C1Search);

		btnM7C1Search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String searchString = editSearchNote.getText().toString();
				ArrayList<String> arrSearchResult = new ArrayList<String>();

				arrSearchResult = db.getPublicNote(searchString);

				if (arrSearchResult != null) {
					if (arrSearchResult.size() > 0) {
						Log.d(TAG, arrSearchResult.toString());
						arrAdapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_list_item_1,
								arrSearchResult) {
							@Override
							public View getView(int position, View convertView,
									ViewGroup parent) {
								TextView textView = (TextView) super.getView(
										position, convertView, parent);
								if (textView.getText().toString()
										.contains("My Secret"))
									textView.setTextColor(Color.RED);
								return textView;
							};

						};

						if (arrAdapter != null) {
							lstSearchResult.setAdapter(arrAdapter);

							// Set Text - Vuln Description
							txtM7C1Result
									.setText("Your search result was added to the ListView below!");
							txtM7C1Result.setTextColor(MyColorHandler
									.getResultColor());

							txtM7C1VulnDescription
									.setText(getResources().getString(
											R.string.m7case1_vulndescription));
							txtM7C1VulnDescription.setTextColor(MyColorHandler
									.getVulnDescriptionColor());

							viewSplitter.setBackgroundColor(Color.WHITE);
						}
					} else {
						Toast.makeText(getActivity().getBaseContext(),
								"No Result", Toast.LENGTH_SHORT).show();
						txtM7C1Result.setText("");
						txtM7C1VulnDescription.setText("");
						viewSplitter.setBackgroundColor(Color.TRANSPARENT);
						if (arrAdapter != null) {
							arrAdapter.clear();
						}
					}
				} else {
					Toast.makeText(getActivity().getBaseContext(),
							"Null Result List", Toast.LENGTH_SHORT).show();
					txtM7C1Result.setText("");
					txtM7C1VulnDescription.setText("");
					viewSplitter.setBackgroundColor(Color.TRANSPARENT);
					if (arrAdapter != null) {
						arrAdapter.clear();
					}
				}

			}
		});

		return view;
	}
}
