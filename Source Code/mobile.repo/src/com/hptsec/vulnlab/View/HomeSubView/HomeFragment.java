package com.hptsec.vulnlab.View.HomeSubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.View.ClientSideInjectionFragment;
import com.hptsec.vulnlab.View.InsecureDataStorageFragment;
import com.hptsec.vulnlab.View.InsufficientTransportLayerProtectionFragment;
import com.hptsec.vulnlab.View.PoorAuthenticationAndAuthorizationFragment;
import com.hptsec.vulnlab.View.SecurityDecisionsViaUntrustedInputFragment;
import com.hptsec.vulnlab.View.UnintendedDataLeakFragment;

public class HomeFragment extends SherlockFragment {
	private static final String ARG_POSITION = "position";

	private int position;

	public static HomeFragment newInstance(int position) {
		HomeFragment f = new HomeFragment();
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

		View view = inflater.inflate(R.layout.fragment_project_home, container,
				false);

		String[] arrCateTitle = getResources().getStringArray(
				R.array.owasp_top10_categories);

		ListView myCategory = (ListView) view.findViewById(R.id.lstCategory);

		ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1,
				arrCateTitle);

		myCategory.setAdapter(arrAdapter);

		myCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0: // M2
					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content,
									InsecureDataStorageFragment.newInstance(),
									InsecureDataStorageFragment.TAG)
							.addToBackStack(null).commit();
					break;
					case 2: // M3
						getActivity()
								.getSupportFragmentManager()
								.beginTransaction()
								.replace(R.id.content,
										InsufficientTransportLayerProtectionFragment.newInstance(),
										InsufficientTransportLayerProtectionFragment.TAG)
								.addToBackStack(null).commit();
						break;
				case 3: // M4
					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content,
									UnintendedDataLeakFragment.newInstance(),
									UnintendedDataLeakFragment.TAG)
							.addToBackStack(null).commit();
					break;
				case 4:
					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(
									R.id.content,
									PoorAuthenticationAndAuthorizationFragment
											.newInstance(),
									PoorAuthenticationAndAuthorizationFragment.TAG)
							.addToBackStack(null).commit();
					break;

				case 6:
					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content,
									ClientSideInjectionFragment.newInstance(),
									ClientSideInjectionFragment.TAG)
							.addToBackStack(null).commit();
					break;
				case 7:
					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(
									R.id.content,
									SecurityDecisionsViaUntrustedInputFragment
											.newInstance(),
									SecurityDecisionsViaUntrustedInputFragment.TAG)
							.addToBackStack(null).commit();
					break;
				default:
					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content,
									InsecureDataStorageFragment.newInstance(),
									InsecureDataStorageFragment.TAG)
							.addToBackStack(null).commit();
					break;
				}
			}
		});

		return view;
	}
}
