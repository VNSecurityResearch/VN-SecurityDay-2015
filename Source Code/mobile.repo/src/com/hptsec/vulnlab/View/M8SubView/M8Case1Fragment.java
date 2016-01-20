package com.hptsec.vulnlab.View.M8SubView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

public class M8Case1Fragment extends SherlockFragment {

	private static String TAG = "M8Case1 - Broadcast Theft";
	private static String M8Case1_Implicit_Action = "com.hptsec.BroadcastImeiIntent";
	private static String M8Case1_Ordered_Action = "com.hptsec.OrderedBroadcast";
	private static String EXTRAS = "M8-OrderedBroadcast";

	private static final String ARG_POSITION = "position";
	private int position;

	public static M8Case1Fragment newInstance(int position) {
		M8Case1Fragment f = new M8Case1Fragment();
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

		View view = inflater.inflate(R.layout.fragment_m8case1_broadcasttheft,
				container, false);

		EditText txtM8C1Control = (EditText) view
				.findViewById(R.id.editM8C1OrderedBroadcastControler);

		Button btnSendImplicitBroadcast = (Button) view
				.findViewById(R.id.btnM8C1SendImplicitBroadcast);

		Button btnSendOrderedBroadcast = (Button) view
				.findViewById(R.id.btnM8C2SendOrderedBroadcast);

		btnSendImplicitBroadcast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myBroad = new Intent();
				myBroad.setAction(M8Case1_Implicit_Action); // com.hptsec.BroadcastImeiIntent
				myBroad.putExtra("MyImeiValue", "31415172638");
				getActivity().sendBroadcast(myBroad);
				Log.d(TAG, "Broadcast Intent sent!!! Value = " + "31415172638");
			}
		});

		btnSendOrderedBroadcast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(M8Case1_Ordered_Action);
				Bundle extras = new Bundle();
				extras.putString(EXTRAS, "M8Case1Framgnet: secret ");
				intent.putExtras(extras);

				// Receive final result after all receiver executed.
				getActivity().sendOrderedBroadcast(intent, null,
						new BroadcastReceiver() {

							@Override
							public void onReceive(Context context, Intent intent) {
								// TODO Auto-generated method stub
								// Bundle results = getResultExtras(true);
								Bundle results = getResultExtras(true);
								Log.d(TAG,
										"Final Result Receiver = "
												+ results.getString(EXTRAS,
														"nil"));
							}
						}, null, Activity.RESULT_OK, null, null);

			}
		});

		return view;
	}
}
