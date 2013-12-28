package com.mejorandola.ejemplo18.data;

import com.mejorandola.ejemplo18.fragments.PlacesMapFragment;
import com.mejorandola.ejemplo18.fragments.RoomGridFragment;
import com.mejorandola.ejemplo18.fragments.RoomListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomPageAdapter extends FragmentStatePagerAdapter {
	private Fragment[] fragments;
	public final static int PLACE_FRAGMENT_INDEX = 2;
	
    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[]{new RoomListFragment(), new RoomGridFragment(), new PlacesMapFragment()};
        fragments[PLACE_FRAGMENT_INDEX].setHasOptionsMenu(true);
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