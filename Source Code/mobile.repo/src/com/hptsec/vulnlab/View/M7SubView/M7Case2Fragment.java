package com.hptsec.vulnlab.View.M7SubView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * M7 Case 2 - Javascript Injection
 */
import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyColorHandler;

public class M7Case2Fragment extends SherlockFragment {
	private static String TAG = "M7Case2 - JavaScript Injection";

	private static String URL = "http://api.securityinone.com/m7case2.php?search=";

	ArrayAdapter<String> arrAdapter = null;

	private static final String ARG_POSITION = "position";

	private int position;

	public static M7Case2Fragment newInstance(int position) {
		M7Case2Fragment f = new M7Case2Fragment();
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

		View view = inflater
				.inflate(R.layout.fragment_m7case2_javascriptinjection,
						container, false);

		final EditText editSearch = (EditText) view
				.findViewById(R.id.txtM7C2Search);

		final TextView txtM7C2Result = (TextView) view
				.findViewById(R.id.txtM7C2Result);
		final TextView txtM7C2VulnDescription = (TextView) view
				.findViewById(R.id.txtM7C2VulnDescription);

		final WebView webView = (WebView) view.findViewById(R.id.webViewM7C2);
		webView.setBackgroundColor(Color.TRANSPARENT);

		Button btnM7C2Search = (Button) view.findViewById(R.id.btnM7C2Search);

		btnM7C2Search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String search = editSearch.getText().toString();

				if (search.length() > 0) {
					String url = URL + search;

					WebSettings webSetting = webView.getSettings();
					webSetting.setJavaScriptEnabled(true);
					webSetting.setAllowFileAccess(false);
					webView.setWebChromeClient(new WebChromeClient());
					webView.loadUrl(url);

					txtM7C2Result
							.setText("Get your result in the Web View below!");
					txtM7C2Result.setTextColor(MyColorHandler.getResultColor());

					txtM7C2VulnDescription.setText(getResources().getString(
							R.string.m7case2_vulndescription));
					txtM7C2VulnDescription.setTextColor(MyColorHandler
							.getVulnDescriptionColor());
				} else {
					txtM7C2Result.setText("");
					txtM7C2VulnDescription.setText("");
					webView.loadUrl("about:blank");
				}
			}
		});

		return view;
	}
}
