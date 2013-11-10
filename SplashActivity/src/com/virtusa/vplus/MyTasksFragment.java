package com.virtusa.vplus;

import android.inputmethodservice.Keyboard.Key;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MyTasksFragment extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.mytask_fragment_layout, container, false);
		
		setHasOptionsMenu(true);
		
		 view.setOnKeyListener( new OnKeyListener() {
	          @Override
	          public boolean onKey( View v, int keyCode, KeyEvent event ) {
	        	  Toast.makeText(getActivity(), Integer.toString(keyCode), Toast.LENGTH_SHORT).show();
	        	  
	        	  return true;
	          } 
	     });
		return view;
	}
	
	
	
	@Override
	public void onCreateOptionsMenu( Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_mytask, menu);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_task) {
            Toast.makeText(getActivity(),"Clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
