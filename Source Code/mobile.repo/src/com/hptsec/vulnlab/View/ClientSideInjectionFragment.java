package com.hptsec.vulnlab.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.astuetz.PagerSlidingTabStrip;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.View.M7SubView.M7Case1Fragment;
import com.hptsec.vulnlab.View.M7SubView.M7Case2Fragment;
import com.hptsec.vulnlab.View.M7SubView.M7Case3Fragment;
import com.hptsec.vulnlab.View.M7SubView.M7Case4Fragment;
import com.hptsec.vulnlab.View.M7SubView.M7Case5Fragment;
import com.hptsec.vulnlab.View.M7SubView.M7IntroduceFragment;

/**
 * Client Side Injection Vulnerabilities
 * 
 * @author whitehatpanda
 * 
 */
public class ClientSideInjectionFragment extends Fragment {

	public static final String TAG = ClientSideInjectionFragment.class
			.getSimpleName();

	public static ClientSideInjectionFragment newInstance() {
		return new ClientSideInjectionFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	/**
	 * All of our fragment use this layout to present for main UI element
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_mainpager, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view
				.findViewById(R.id.tabs);
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		ClientSideInjectionPagerAdapter adapter = new ClientSideInjectionPagerAdapter(
				getChildFragmentManager());
		pager.setAdapter(adapter);
		tabs.setViewPager(pager);

	}

	/**
	 * Processing for sliding left -> right, vice versa
	 * 
	 * @author whitehatpanda
	 * 
	 */
	public class ClientSideInjectionPagerAdapter extends FragmentPagerAdapter {

		public ClientSideInjectionPagerAdapter(
				android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		private final String[] TITLES = { "Introduce", "Case 1", "Case 2",
				"Case 3", "Case 4", "Case 5" };

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public SherlockFragment getItem(int position) {
			// Based on position call exactly Fragment we wanted
			switch (position) {
			case 0:
				return M7IntroduceFragment.newInstance(position);
			case 1:
				return M7Case1Fragment.newInstance(position);
			case 2:
				return M7Case2Fragment.newInstance(position);
			case 3:
				return M7Case3Fragment.newInstance(position);
			case 4:
				return M7Case4Fragment.newInstance(position);
			case 5:
				return M7Case5Fragment.newInstance(position);
			default:
				return M7IntroduceFragment.newInstance(position);
			}

		}
	}

}
