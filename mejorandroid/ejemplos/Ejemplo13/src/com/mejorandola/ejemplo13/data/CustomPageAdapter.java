package com.mejorandola.ejemplo13.data;

import com.mejorandola.ejemplo13.fragments.RoomGridFragment;
import com.mejorandola.ejemplo13.fragments.RoomListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomPageAdapter extends FragmentStatePagerAdapter {
	private Fragment[] fragments;
	
    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[]{new RoomListFragment(), new RoomGridFragment()};
    }

    @Override
    public Fragment getItem(int i) {    	
        return fragments[i];
    }

	@Override
	public int getCount() {
		return fragments.length;
	}    
}