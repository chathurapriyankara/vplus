package com.virtusa.vplus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class AddNewRaveFragment extends SherlockFragment {
	// Creates the view for the fragment
	private View view;
	//field values of new rave
	private String recieverName = "";
	private String category = "";
	private String raveMsg = "";
	private String notifyName = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.addnew_rave_fragment_layout,
				container, false);
		// ads the optins menu in action bar
		// setHasOptionsMenu(true);
		// get the selected item form the rave category spinner
		sendRavebtnCickListner();
		final String[] category_list = {"Great Work", "Living PIRL", "Thanks for the service", "Awesome Code"};
		final EditText raveCategories = (EditText) view.findViewById(R.id.rave_category_edittext);
		raveCategories.setInputType(InputType.TYPE_NULL);
		
		final ArrayAdapter<String> spinner_categories = new  ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item, category_list);

	    raveCategories.setOnClickListener(new View.OnClickListener() {

	        public void onClick(View v) {
	            new AlertDialog.Builder(getActivity())
	                  .setTitle("Select Category")
	                  .setAdapter(spinner_categories, new DialogInterface.OnClickListener() {

	                    public void onClick(DialogInterface dialog, int which) {
	                        raveCategories.setText(category_list[which].toString());
	                      dialog.dismiss();
	                    }
	                  }).create().show();


	        }
	    });
		return view;
	}

	/** On click listner of the send rave button */
	public void sendRavebtnCickListner() {
		Button btnSendRave = (Button) view.findViewById(R.id.btnSendRave);
		btnSendRave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText editTextPersonName = (EditText) view.findViewById(R.id.etPersonName);
				Spinner spinRaveCategory = (Spinner) view.findViewById(R.id.spinner_rave_categories);
				
				
				
				EditText editTextRaveMessage = (EditText) view.findViewById(R.id.etRaveMsg);
				EditText editTextNotifyPersonName = (EditText) view.findViewById(R.id.etNotifyPersonName);
				
				recieverName = editTextPersonName.getText().toString();
				category = String.valueOf(spinRaveCategory.getSelectedItem());
				raveMsg = editTextRaveMessage.getText().toString();
				notifyName = editTextNotifyPersonName.getText().toString();
				
			
				
				Toast.makeText(getActivity(),
						String.valueOf(spinRaveCategory.getSelectedItem()), Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	/** Add the listner for the spinner */
//	public void addListnerOnSpinnerItemSelection() {
//		Spinner spinRaveCategory = (Spinner) view
//				.findViewById(R.id.spinner_rave_categories);
//		spinRaveCategory
//				.setOnItemSelectedListener(new CustomoItemSelectedListner());
//	}

//	// On item selected listner for the spinner
//	public class CustomoItemSelectedListner implements OnItemSelectedListener {
//
//		@Override
//		public void onItemSelected(AdapterView<?> parent, View view, int pos,
//				long id) {
//			Toast.makeText(parent.getContext(),
//					parent.getSelectedItem().toString(), Toast.LENGTH_SHORT)
//					.show();
//		}
//
//		@Override
//		public void onNothingSelected(AdapterView<?> arg0) {
//			// TODO Auto-generated method stub
//
//		}
//
//	}

}
