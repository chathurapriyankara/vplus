package com.virtusa.vplus;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class RaveFragment extends SherlockFragment {

	private List<RaveList> raveList = new ArrayList<RaveList>();
	TextView tvConfirm;
	ImageView imageView;
	View view;
	RaveList currentRave;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.rave_fragment_layout, container,
				false);
		// adds the options menu in action bar
		setHasOptionsMenu(true);

		populateRaveList();

		final ArrayAdapter<RaveList> adapter = new MylistAdapter();
		ListView list = (ListView) view.findViewById(R.id.ravelist);
		list.setAdapter(adapter);

		// list item click event to get the popup window
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				currentRave = raveList.get(position);

				initiatePopupWindow(currentRave.getRaveType(),
						currentRave.getRaveDis(), currentRave.getRavePerson());

			}
		});
		
		// click event to view the rave badges
		ImageView image = (ImageView) view.findViewById(R.id.ravebadge);
		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				RaveBadgeFragment fragment = new RaveBadgeFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
			    
			    transaction.replace(R.id.raves_frame, fragment );
			    transaction.addToBackStack(null);
			    transaction.commit();
				
				
			}
		});

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_rave, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.action_new_rave) {
			Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
			FragmentTransaction ft = getActivity().getSupportFragmentManager()
					.beginTransaction();
			Fragment fragment = new AddNewRaveFragment();
			ft.replace(R.id.raves_frame, fragment, "NewFragmentTag");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(null);
			ft.commit();
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Enter values to the ravelist
	private void populateRaveList() {
		raveList.add(new RaveList("Good Thinking",
				"\" Thank you for doing such a great job \"", "Madushi Dias",
				R.drawable.thumbsup));
		raveList.add(new RaveList(
				"Good Thinking",
				" \"Thank you for doing such a great job  hjgkffyu ukf u  kuyf  uy ku ku uk k k f u ku yku uky kuy fyu yuk uy  jyutdf  dhgd yt jydt uy fuyf uyfuyf uyfufffuy  yuf uyf  u uyf \" ",
				"Madushi Dias", R.drawable.thumbsup));
		raveList.add(new RaveList(
				"Good Thinking",
				" \"Thank you for doing such a great jobjhyf uk uklgt iut iu uit kgtui tui kitu \"",
				"Madushi Dias", R.drawable.thumbsup));
		raveList.add(new RaveList("Good Thinking",
				" \"Thank you for doing such a great job\"", "Madushi Dias",
				R.drawable.thumbsup));

	}
	
	//Setting values to the layout
	public class MylistAdapter extends ArrayAdapter<RaveList> {

		Context context;

		public MylistAdapter() {
			super(getActivity(), R.layout.ravelistview, raveList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater(null).inflate(
						R.layout.ravelistview, parent, false);
			}
			currentRave = raveList.get(position);
		
			// rave type
			TextView rave_type = (TextView) itemView
					.findViewById(R.id.ravetitle);
			rave_type.setText(currentRave.getRaveType());
			// rave description
			TextView rave_dec = (TextView) itemView
					.findViewById(R.id.raveexplanation);

			rave_dec.setText(currentRave.getRaveDis());

			// rave person
			TextView rave_person = (TextView) itemView
					.findViewById(R.id.raveperson);
			rave_person.setText(currentRave.getRavePerson());

			return itemView;
		}

	}

	//popup window method
	private PopupWindow pwindo;

	public void initiatePopupWindow(String type, String des, String name) {

		try {
			// We need to get the instance of the LayoutInflater
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.popup_screen, (ViewGroup)

			getActivity().findViewById(R.id.popup));
			pwindo = new PopupWindow(layout, 400,
					ViewGroup.LayoutParams.WRAP_CONTENT, true);
			pwindo.showAtLocation(layout, Gravity.CENTER_VERTICAL, 0, 0);

			//ravetype
			TextView rave_type = (TextView) layout.findViewById(R.id.ravetype);
			rave_type.setText(type);
			
			// rave description
			TextView rave_dec = (TextView) layout.findViewById(R.id.ravedes);
			rave_dec.setText(des);

			// rave person
			TextView rave_person = (TextView) layout
					.findViewById(R.id.raveperson);
			rave_person.setText(name);

			//send thank you response
			Button sendThank = (Button) layout.findViewById(R.id.send_thank);
			sendThank.setOnClickListener(thank);

			Button btnClosePopup = (Button) layout.findViewById(R.id.close);
			btnClosePopup.setOnClickListener(cancel_button_click_listener);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//send thank you message method
	private OnClickListener thank = new OnClickListener() {
		public void onClick(View v) {
			AlertDialog.Builder altDialog = new AlertDialog.Builder(
					getActivity());
			altDialog.setMessage("Send Thank you message");

			altDialog.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// if this button is clicked, close
							// current activity

						}

					});
			altDialog.show();

		}
	};

	private OnClickListener cancel_button_click_listener = new OnClickListener() {
		public void onClick(View v) {
			pwindo.dismiss();

		}
	};

}
