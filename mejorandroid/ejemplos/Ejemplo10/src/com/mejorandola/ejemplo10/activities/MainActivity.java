package com.mejorandola.ejemplo10.activities;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;

import com.mejorandola.ejemplo10.R;
import com.mejorandola.ejemplo10.data.CustomPageAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
	ViewPager view_pager;
	CustomPageAdapter adapter;
	private PullToRefreshAttacher pull_to_refresh_attacher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pull_to_refresh_attacher = PullToRefreshAttacher.get(this);
		
		adapter = new CustomPageAdapter(getSupportFragmentManager());
        view_pager = (ViewPager) findViewById(R.id.pager);
        view_pager.setAdapter(adapter);
        view_pager.setOnPageChangeListener(this);
        
	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.title_tab_list)).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.title_tab_grid)).setTabListener(this));
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onPageSelected(int position) {
		getActionBar().setSelectedNavigationItem(position);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		view_pager.setCurrentItem(tab.getPosition());
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {}

	public PullToRefreshAttacher getPullToRefreshAttacher() {
		return pull_to_refresh_attacher;
	}
}