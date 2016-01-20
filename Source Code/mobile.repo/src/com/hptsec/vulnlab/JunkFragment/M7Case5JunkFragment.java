package com.hptsec.vulnlab.JunkFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.JavascriptInterface.JsObject;

/**
 * Assume that this Fragment only use by another activity, and another activity
 * don't have exported permission. And this fragment have a Web View control to
 * handle another activity loadUrl call.
 * 
 * @author whitehatpanda
 * 
 */
public class M7Case5JunkFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_m7case5_junkfragment,
				container, false);

		WebView webView = (WebView) view
				.findViewById(R.id.webViewM7Case5Vulnerable);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JsObject(), "jsObject");

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			}
		});
		webView.loadUrl(this.getActivity().getIntent().getDataString());

		return view;
	}
}
