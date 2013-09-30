package com.mejorandola.ejemplo13.activities;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;

import com.mejorandola.ejemplo13.R;
import com.mejorandola.ejemplo13.fragments.MainFragment;
import com.mejorandola.ejemplo13.fragments.RoomListFragment;
import com.mejorandola.ejemplo13.fragments.TermsFragment;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements  ListView.OnItemClickListener {
	private ListView drawer_list;
	private DrawerLayout drawer_layout;
	private ActionBarDrawerToggle drawer_toggle;
	private PullToRefreshAttacher pull_to_refresh_attacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pull_to_refresh_attacher = PullToRefreshAttacher.get(this);

		drawer_list = (ListView) findViewById(R.id.left_drawer);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ArrayAdapter<String> drawer_adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, getResources().getStringArray(R.array.array_drawer_options));
        
        drawer_list.setAdapter(drawer_adapter);
        drawer_list.setOnItemClickListener(this);
        drawer_toggle = new ActionBarDrawerToggle(this, drawer_layout, R.drawable.ic_drawer, 
        										  R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        
        drawer_layout.setDrawerListener(drawer_toggle);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);  
        
        selectItem(0);
	}
	
	public PullToRefreshAttacher getPullToRefreshAttacher() {
		return pull_to_refresh_attacher;
	}

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawer_toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawer_toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawer_toggle.onOptionsItemSelected(item)) {
          return true;
        }

        return super.onOptionsItemSelected(item);
    }	
    
	private void selectItem(int position) {
		Fragment frag;
		if (position == 0) {
			frag = new MainFragment();
		} else if (position == 1) {
			Bundle args = new Bundle();
			args.putInt(RoomListFragment.LIST_TYPE, RoomListFragment.FAVORITE_LIST);
			frag = new RoomListFragment();
			frag.setArguments(args);
			
		    getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);			
		} else {
			frag = new TermsFragment();
		} 

	    FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.main_content, frag)
	                   .commit();
	    drawer_list.setItemChecked(position, true);	    
	    setTitle(drawer_list.getItemAtPosition(position).toString());
	    drawer_layout.closeDrawer(drawer_list);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		selectItem(position);		
	}	
}