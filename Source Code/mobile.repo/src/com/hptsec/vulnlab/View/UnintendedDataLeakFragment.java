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
import com.hptsec.vulnlab.View.M4SubView.M4Case1Fragment;
import com.hptsec.vulnlab.View.M4SubView.M4IntroduceFragment;

public class UnintendedDataLeakFragment extends Fragment {

	public static final String TAG = UnintendedDataLeakFragment.class
			.getSimpleName();

	public static UnintendedDataLeakFragment newInstance() {
		return new UnintendedDataLeakFragment();
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
		UnintendedDataLeakFragmentPagerAdapter adapter = new UnintendedDataLeakFragmentPagerAdapter(
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
	public class UnintendedDataLeakFragmentPagerAdapter extends
			FragmentPagerAdapter {

		public UnintendedDataLeakFragmentPagerAdapter(
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
				return M4IntroduceFragment.newInstance(position);
			case 1:
				return M4Case1Fragment.newInstance(position);
			case 2:
				return M4IntroduceFragment.newInstance(position);
			case 3:
				return M4IntroduceFragment.newInstance(position);
			default:
				return M4IntroduceFragment.newInstance(position);
			}

		}
	}
}
