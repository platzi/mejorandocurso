package com.mejorandola.ejemplo13.fragments;

import com.mejorandola.ejemplo13.R;
import com.mejorandola.ejemplo13.data.CustomPageAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment implements ActionBar.TabListener, 
													  ViewPager.OnPageChangeListener {
	private ViewPager view_pager;
	private CustomPageAdapter adapter;	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new CustomPageAdapter(getActivity().getSupportFragmentManager());

        view_pager.setAdapter(adapter);
        view_pager.setOnPageChangeListener(this);
        
	    ActionBar actionBar = getActivity().getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.removeAllTabs();
        actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.title_tab_list)).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.title_tab_grid)).setTabListener(this));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, null);
        view_pager = (ViewPager) view.findViewById(R.id.pager);
		return view;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onPageSelected(int position) {
		getActivity().getActionBar().setSelectedNavigationItem(position);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		view_pager.setCurrentItem(tab.getPosition());
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {}

}
