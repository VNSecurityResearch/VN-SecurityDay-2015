/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Thanked to Andreas Stuetz
 * @Developer whitehatpanda
 */

package com.hptsec.vulnlab;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.hptsec.vulnlab.R;
import com.hptsec.vulnlab.View.ClientSideInjectionFragment;
import com.hptsec.vulnlab.View.InsecureDataStorageFragment;
import com.hptsec.vulnlab.View.InsufficientTransportLayerProtectionFragment;
import com.hptsec.vulnlab.View.PoorAuthenticationAndAuthorizationFragment;
import com.hptsec.vulnlab.View.ProjectInfoFragment;
import com.hptsec.vulnlab.View.SecurityDecisionsViaUntrustedInputFragment;
import com.hptsec.vulnlab.View.UnintendedDataLeakFragment;

public class VulnAppMainActivity extends SherlockFragmentActivity {

	int EXIT_COUNT = 0;

	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mCategoryTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		mTitle = mDrawerTitle = getTitle();
		mCategoryTitles = getResources().getStringArray(
				R.array.vulnerablecategory_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); // Layout
																			// of
																			// fragment_main
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mCategoryTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home: {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			break;
		}

		case R.id.action_contact:
			// QuickContactFragment dialog = new QuickContactFragment();
			// dialog.show(getSupportFragmentManager(), "QuickContactFragment");
			// return true;

		}

		return super.onOptionsItemSelected(item);
	}

	// The click listener for ListView in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void selectItem(int position) {

		switch (position) {
		case 0:
			// Project Info - Insecure Data Storage
			getSupportFragmentManager().popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content, ProjectInfoFragment.newInstance(),
							ProjectInfoFragment.TAG).commit();
			break;
		case 1:
			// M2 - Insecure Data Storage
			// getSupportFragmentManager().popBackStack(null,
			// FragmentManager.POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content,
							InsecureDataStorageFragment.newInstance(),
							InsecureDataStorageFragment.TAG)
					.addToBackStack(null).commit();
			break;
		case 2:
			// M3 - Insufficient Transport Layer Protection
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content,
							InsufficientTransportLayerProtectionFragment.newInstance(),
							InsufficientTransportLayerProtectionFragment.TAG)
					.addToBackStack(null).commit();
			break;
		case 3:
			// M4 - Unintended Data Leak
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content,
							UnintendedDataLeakFragment.newInstance(),
							UnintendedDataLeakFragment.TAG)
					.addToBackStack(null).commit();
			break;
		case 4:
			// M5 - Poor Authen/Author
			getSupportFragmentManager()
					.beginTransaction()
					.replace(
							R.id.content,
							PoorAuthenticationAndAuthorizationFragment
									.newInstance(),
							PoorAuthenticationAndAuthorizationFragment.TAG)
					.addToBackStack(null).commit();
			break;
		case 5:
			// M6 - Broken Cryptography
			break;
		case 6:
			// M7 - Client Side Injection
			// getSupportFragmentManager().popBackStack(null,
			// FragmentManager.POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content,
							ClientSideInjectionFragment.newInstance(),
							ClientSideInjectionFragment.TAG)
					.addToBackStack(null).commit();
			break;
		case 7:
			// M8 - Security decisions via Untrusted Input
			getSupportFragmentManager()
					.beginTransaction()
					.replace(
							R.id.content,
							SecurityDecisionsViaUntrustedInputFragment
									.newInstance(),
							SecurityDecisionsViaUntrustedInputFragment.TAG)
					.addToBackStack(null).commit();
			break;
		case 8:
			// M9 - Improper Session Handling
			break;
		case 9:
			// M10 - Lack of Binary Protection
			break;
		default:
			break;
		}

		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		FragmentManager fm = getSupportFragmentManager();
		if (fm.getBackStackEntryCount() != 0) {
			fm.popBackStack();
			EXIT_COUNT = 0;
			return;
		} else {
			EXIT_COUNT += 1;
			Toast.makeText(this, "Press Back Again to Exit!",
					Toast.LENGTH_SHORT).show();
			if (EXIT_COUNT == 2) {
				System.exit(0);
			}

		}
	}

}