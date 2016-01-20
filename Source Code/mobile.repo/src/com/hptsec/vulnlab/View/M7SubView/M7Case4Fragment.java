package com.hptsec.vulnlab.View.M7SubView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyColorHandler;

/**
 * M3Case 4 - Local File Inclusion via WebView Android Control
 * 
 * @author whitehatpanda
 * 
 */
public class M7Case4Fragment extends SherlockFragment {

	private static final String ASSET_BASE = "file:///android_asset/";

	// If needed
	private static final String RESOURCE_BASE = "file:///android_res/";

	private static String TAG = "M7Case4 - Local File Inclusion Fragment";

	private static final String ARG_POSITION = "position";

	private int position;

	public static M7Case4Fragment newInstance(int position) {
		M7Case4Fragment f = new M7Case4Fragment();
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
				R.layout.fragment_m7case4_localfileinclusion, container, false);

		final TextView txtM7C4Result = (TextView) view
				.findViewById(R.id.txtM7C4Result);

		final TextView txtM7C4VulnDescription = (TextView) view
				.findViewById(R.id.txtM7C4VulnDescription);

		final WebView webView = (WebView) view.findViewById(R.id.webViewM7C4);

		final EditText editUri = (EditText) view.findViewById(R.id.editM7C4Uri);

		Button btnM7C4LFIMe = (Button) view.findViewById(R.id.btnM7C4IncludeMe);

		btnM7C4LFIMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String myUri = editUri.getText().toString();

				InitSettingForWebView(webView);

				if (myUri.length() > 0) {
					// Using WebView with loadUri function to include some file
					// via wrapper like this:
					// file:///android_assets/file/index.html
					try {
						webView.loadUrl(myUri);

						txtM7C4Result
								.setText("Your result in the WebView below, you will see them if your wrapper wouldn't wrong!");
						txtM7C4Result.setTextColor(MyColorHandler
								.getResultColor());

						txtM7C4VulnDescription.setText(getResources()
								.getString(R.string.m7case4_vulndescription));
						txtM7C4VulnDescription.setTextColor(MyColorHandler
								.getVulnDescriptionColor());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else { // Load Default File In Asset
					webView.loadUrl(ASSET_BASE + "home/index.html");
					txtM7C4Result
							.setText("We loaded our default page in asset base folder.");
					txtM7C4Result.setTextColor(MyColorHandler.getResultColor());

					txtM7C4VulnDescription.setText("");
				}
			}

			/**
			 * Settings for WebView
			 * 
			 * @param webView
			 */
			@SuppressLint("NewApi")
			private void InitSettingForWebView(WebView webView) {
				// TODO Auto-generated method stub
				webView.getSettings().setJavaScriptEnabled(true); // Enable
																	// JavaScript
				/* This is important to get this situation */
				webView.getSettings().setAllowFileAccess(true); // Enable File
				// System access
				webView.getSettings().setDomStorageEnabled(false); // Enable DOM
																	// Storage
																	// if needed
				webView.getSettings().setAllowFileAccessFromFileURLs(true); // Allow
																			// access
																			// via
																			// file
																			// schema,
																			// if
																			// breakdown
																			// security
																			// policy
																			// of
																			// android
																			// system

				webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
			}
		});

		return view;
	}
}
