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
import com.hptsec.vulnlab.View.M2SubView.M2Case1Fragment;
import com.hptsec.vulnlab.View.M2SubView.M2Case2Fragment;
import com.hptsec.vulnlab.View.M2SubView.M2Case3Fragment;
import com.hptsec.vulnlab.View.M2SubView.M2Case4Fragment;
import com.hptsec.vulnlab.View.M2SubView.M2Case5Fragment;
import com.hptsec.vulnlab.View.M2SubView.M2Case6Fragment;
import com.hptsec.vulnlab.View.M2SubView.M2IntroduceFragment;

/**
 * M2 - Insecure Data Storage
 * 
 * @author whitehatpanda
 * 
 */
public class InsecureDataStorageFragment extends Fragment {

	public static final String TAG = InsecureDataStorageFragment.class
			.getSimpleName();

	public static InsecureDataStorageFragment newInstance() {
		return new InsecureDataStorageFragment();
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
		InsecureDataStoragePagerAdapter adapter = new InsecureDataStoragePagerAdapter(getChildFragmentManager());
		pager.setAdapter(adapter);
		tabs.setViewPager(pager);

	}

	/**
	 * Processing for sliding left -> right, vice versa
	 * 
	 * @author whitehatpanda
	 * 
	 */
	public class InsecureDataStoragePagerAdapter extends FragmentPagerAdapter {

		public InsecureDataStoragePagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		private final String[] TITLES = { "Introduce", "Case 1", "Case 2",
				"Case 3", "Case 4", "Case 5", "Case 6" };

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
				return M2IntroduceFragment.newInstance(position);
			case 1:
				return M2Case1Fragment.newInstance(position);
			case 2:
				return M2Case2Fragment.newInstance(position);
			case 3:
				return M2Case3Fragment.newInstance(position);
			case 4:
				return M2Case4Fragment.newInstance(position);
			case 5:
				return M2Case5Fragment.newInstance(position);
			case 6:
				return M2Case6Fragment.newInstance(position);
			default:
				return M2IntroduceFragment.newInstance(position);
			}

		}

	}

}
