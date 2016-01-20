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
import com.hptsec.vulnlab.View.HomeSubView.AboutFragment;
import com.hptsec.vulnlab.View.HomeSubView.HomeFragment;
import com.hptsec.vulnlab.View.HomeSubView.TutorialsFragment;

/**
 * Project Information - Home View - Main
 * 
 * @author whitehatpanda
 * 
 */
public class ProjectInfoFragment extends Fragment {
	public static final String TAG = ProjectInfoFragment.class.getSimpleName();

	public static ProjectInfoFragment newInstance() {
		return new ProjectInfoFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

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
		ProjectHomePagerAdapter adapter = new ProjectHomePagerAdapter(
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
	public class ProjectHomePagerAdapter extends FragmentPagerAdapter {

		public ProjectHomePagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		private final String[] TITLES = { "Home", "About", "Tutorials" };

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
				return HomeFragment.newInstance(position);
			case 1:
				return AboutFragment.newInstance(position);
			case 2:
				return TutorialsFragment.newInstance(position);
			default:
				return HomeFragment.newInstance(position);
			}

		}

	}
}
