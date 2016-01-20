package com.hptsec.vulnlab.View;

import com.actionbarsherlock.app.SherlockFragment;
import com.astuetz.PagerSlidingTabStrip;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.View.M5SubView.M5Case1Fragment;
import com.hptsec.vulnlab.View.M5SubView.M5Case2Fragment;
import com.hptsec.vulnlab.View.M5SubView.M5Case3Fragment;
import com.hptsec.vulnlab.View.M5SubView.M5IntroduceFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PoorAuthenticationAndAuthorizationFragment extends Fragment {

	public static final String TAG = PoorAuthenticationAndAuthorizationFragment.class
			.getSimpleName();

	public static PoorAuthenticationAndAuthorizationFragment newInstance() {
		return new PoorAuthenticationAndAuthorizationFragment();
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
		PoorAuthenticationAndAuthorizationFragmentPagerAdapter adapter = new PoorAuthenticationAndAuthorizationFragmentPagerAdapter(
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
	public class PoorAuthenticationAndAuthorizationFragmentPagerAdapter extends
			FragmentPagerAdapter {

		public PoorAuthenticationAndAuthorizationFragmentPagerAdapter(
				android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		private final String[] TITLES = { "Introduce", "Case 1", "Case 2",
				"Case 3" };

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
				return M5IntroduceFragment.newInstance(position);
			case 1:
				return M5Case1Fragment.newInstance(position);
			case 2:
				return M5Case2Fragment.newInstance(position);
			case 3:
				return M5Case3Fragment.newInstance(position);
			default:
				return M5IntroduceFragment.newInstance(position);
			}

		}
	}

}
