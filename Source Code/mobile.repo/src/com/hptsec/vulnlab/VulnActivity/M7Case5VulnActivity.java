package com.hptsec.vulnlab.VulnActivity;

import com.hptsec.vulnlab.JunkFragment.M7Case5JunkFragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * M7Case 5 - Vulnerable Implicitly Exported Activity via Intent-Filter
 * technique, demonstration of fragment injection Calling Fragment:
 * M7Case5Fragment
 * 
 * I'm
 * 
 * @author whitehatpanda
 * 
 */
public class M7Case5VulnActivity extends PreferenceActivity {

	// This class based-on decompiled code of Settings.apk to implement this
	// vulnerable app concept

	@Override
	public Intent getIntent() {
		Intent myIntent = new Intent(super.getIntent());
		myIntent.putExtra(":android:show_fragment",
				M7Case5JunkFragment.class.getName());
		myIntent.putExtra(":android:no_headers", true);

		return myIntent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected boolean isValidFragment(String fragmentName) {
		// TODO Auto-generated method stub
		return true;
	}
}
