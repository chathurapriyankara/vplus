package com.virtusa.vplus;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class DashboardActivity extends SherlockFragmentActivity {
	 	private String[] drawerListViewItems;
	    private ListView drawerListView;
	    private DrawerLayout drawerLayout;
	    private ActionBarDrawerToggle mDrawerToggle;
	    private String[] fragmentTitles = {"People Search","V+ Dash","Rave","My Task","Events","vBlog","vTube"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		drawerListViewItems = getResources().getStringArray(R.array.items);
		 
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.drawer);
 
                // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview_item, drawerListViewItems));
	
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        	);
        
        drawerLayout.setDrawerListener(mDrawerToggle);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 
        // just styling option add shadow the right edge of the drawer
    drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
     drawerListView.setOnItemClickListener(new DrawerItemClickListener());
     if (savedInstanceState == null) {
			selectItem(1);

			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
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
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		 switch(item.getItemId()){
		 case android.R.id.home:
			 if(drawerLayout.isDrawerOpen(drawerListView)){
				 drawerLayout.closeDrawer(drawerListView);
			 }else{
				 drawerLayout.openDrawer(drawerListView);
			 }
		 }
			return false;
		 
	    }
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
     @Override
     public void onItemClick(AdapterView parent, View view, int position, long id) {
         //Toast.makeText(MainActivity.this, ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
         //drawerLayout.closeDrawer(drawerListView);
         selectItem(position);
     }
 }
	
//	private void selectFragment(int position){
//
////		Intent intent = new Intent(MainActivity.this,Second.class);
////		startActivity(intent);
////		drawerLayout.closeDrawer(drawerListView);
//		Fragment newFragment = new Second();
//		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//		switch(position){
//		case 0:
//			sfa = new Second();
//			break;
//		}
//		fm.beginTransaction()
//		.replace(R.id.action_settings, sfa)
//		.commit();
//		
//		drawerListView.setItemChecked(position, true);
//		drawerLayout.closeDrawer(drawerListView);
//	}
	
	
	 private void selectItem(int position) {
		 
	        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	       // ft.addToBackStack(null);
	        // Locate Position
	        switch (position) {
	        
	        case 0:
	        	
	        	Intent peopleSearchIntent = new Intent(DashboardActivity.this,PeopleSearchActivity.class);
	        	startActivity(peopleSearchIntent);
	        	overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
	        	
	        	
//	        	Intent preSearchIntent = new Intent(DashboardActivity.this,PreSearchActivity.class);
//	        	startActivity(preSearchIntent);
//	        	overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
//	        	Fragment searchFragment = new SearchFragment();
//	        	ft.replace(R.id.content_frame, searchFragment);
//	        	setTitle(fragmentTitles[0]);
	        	break;
	        
	        case 1:
	        	Fragment f0 = new DashboardFragment();
	        	ft.replace(R.id.content_frame, f0);
	        	ft.addToBackStack(null);
	        	setTitle(fragmentTitles[1]);
	        	break;
	        
	        case 2:
	        	Fragment f = new RaveFragment();
	        	ft.replace(R.id.content_frame, f);
	        	setTitle(fragmentTitles[2]);
	        	break;
	        	
//	        case 3:
//	        	Fragment f2 = new MyTasksFragment();
//	        	ft.replace(R.id.content_frame, f2);
//	        	setTitle(fragmentTitles[3]);
//	        	break;
//	        	
//	        case 4:
//	        	Fragment eventsFragment = new EventsFragment();
//	        	ft.replace(R.id.content_frame, eventsFragment);
//	        	setTitle(fragmentTitles[4]);
//	        	break;
//	        	
//	        case 5:
//	        	Fragment vblogFragment = new VBlogFragment();
//	        	ft.replace(R.id.content_frame, vblogFragment);
//	        	setTitle(fragmentTitles[5]);
//	        	break;
//	        	
//	        case 6:
//	        	Fragment vtubeFragment = new VTubeFragment();
//	        	ft.replace(R.id.content_frame, vtubeFragment);
//	        	setTitle(fragmentTitles[6]);
//	        	break;
	        	
	        case 3:
	        	Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
	            startActivity(intent);      
	            finish(); 
	        	
	        }
	        ft.commit();
	        drawerListView.setItemChecked(position, true);
	        //setTitle("Title");
	        drawerLayout.closeDrawer(drawerListView);
	    }


}
