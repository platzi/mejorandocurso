package com.mejorandola.ejemplo09.data;

import com.mejorandola.ejemplo09.fragments.PagerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomPageAdapter extends FragmentStatePagerAdapter {
	
    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putInt(PagerFragment.CONTENT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

	@Override
	public int getCount() {
		return 2;
	}    
}