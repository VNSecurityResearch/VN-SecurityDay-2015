package com.hptsec.vulnlab.View.M5SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.Utilities.MyColorHandler;

public class M5Case3Fragment extends SherlockFragment {
	private static String TAG = "M5Case3 - Weak Password Policy";

	private static final String ARG_POSITION = "position";

	@SuppressWarnings("unused")
	private int position;

	public static M5Case3Fragment newInstance(int position) {
		M5Case3Fragment f = new M5Case3Fragment();
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
				R.layout.fragment_m5case3_weakpasswordpolicy, container, false);

		final TextView txtM5C3Result = (TextView) view
				.findViewById(R.id.txtM5C3Result);

		final TextView txtM5C3VulnDescription = (TextView) view
				.findViewById(R.id.txtM5C3VulnDescription);

		final EditText editVaultPass = (EditText) view
				.findViewById(R.id.editM5C3Password);

		Button btnM5C3GetVault = (Button) view
				.findViewById(R.id.btnM5C3GetVault);

		btnM5C3GetVault.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String vaultPass = editVaultPass.getText().toString();

				if (vaultPass.equals("1")) {
					Toast.makeText(
							getActivity().getBaseContext(),
							"Here your Vault's s3cr3t : DON'T USE WEAK PASSWORD!",
							Toast.LENGTH_SHORT).show();

					txtM5C3Result.setText("DON'T USE WEAK PASSWORD!");
					txtM5C3Result.setTextColor(MyColorHandler.getResultColor());

					txtM5C3VulnDescription.setText(getResources().getString(
							R.string.m5case3_vulndescription));
					txtM5C3VulnDescription.setTextColor(MyColorHandler
							.getVulnDescriptionColor());
				} else {
					txtM5C3Result.setText("My weak password is: 1");
					txtM5C3Result.setTextColor(MyColorHandler.getResultColor());

					txtM5C3VulnDescription.setText("");
				}

			}
		});

		return view;
	}
}
