package com.virtusa.vplus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class PeopleSearchActivity extends Activity {
	public AutoCompleteTextView autoComplete;
	//flag use to check wether the status of the async task
	boolean flag = true;
	//array lists use to store suggesions information
	ArrayList<String> people = new ArrayList<String>();
	ArrayList<Integer> avatar = new ArrayList<Integer>();
	ArrayList<String> designation = new ArrayList<String>();
	//hash map passes to the async task with the parameters
	HashMap<String, String> params;
	//keys for hash set
	String[] from = { "flag", "txt" ,"desig"};
	//ui elements in the search suggesions
	int[] to = { R.id.flag, R.id.txt , R.id.tv_dest};
	//array list use in the adaper
	List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
	
	//json array use to buils the result
	static JSONArray returnData = null;
	//server url
	static final String SERVER_URL = "http://rdeshapriya.com/vnotifications/webService.php?action=vplussearch&data=";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_search);

		autoComplete = (CustomAutoCompleteTextView) findViewById(R.id.autocomplete);

		autoComplete.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable editable) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String newString = s.toString().trim();
   
				if (isNetworkAvailable() && newString != null && flag) {
					

					params = new HashMap<String, String>();
					params.put("query", s.toString());
					new getJson().execute(params);
				}
				
				if(!isNetworkAvailable()){
					Toast.makeText(getApplicationContext(), "Please check your internet connecton", Toast.LENGTH_SHORT).show();
				}
			}

		});
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.people_search, menu);
//		return true;
//	}

	

	class getJson extends AsyncTask<HashMap<String, String>, String, String> {

		JSONArray jArray;
		//array adpter use to populate the suggesions list
		SimpleAdapter adapter;
		
		@Override
		protected String doInBackground(HashMap<String, String>... key) {
			flag = false;
			try {
				//clear all arraylists if items are there
				if(people.size()>0){
				people.clear();
				avatar.clear();
				designation.clear();
				}
				jArray = postRequestWithData("vplussearch", key[0]);
				for (int i = 0; i < jArray.length(); i++) {
					String suggestKey = jArray.getJSONObject(i).getString(
							"name");
					String dest = jArray.getJSONObject(i).getString("designation");
					people.add(suggestKey);
					avatar.add(R.drawable.person);
					designation.add(dest);

				}

			} catch (Exception e) {
				Log.w("Error", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			//clear the array list if items exist
			if(aList.size() > 0){
				aList.clear();
			}
			
			for (int i = 0; i < people.size(); i++) {
				
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("txt", people.get(i));
				hm.put("flag", Integer.toString(avatar.get(i)));
				hm.put("desig", designation.get(i));
				aList.add(hm);
			}

				adapter = new SimpleAdapter(getBaseContext(), aList,
					R.layout.autocomplete_layout, from, to);
			autoComplete.setAdapter(adapter);
			flag = true;

		}

	}

	public static JSONArray postRequestWithData(String action,
			HashMap<String, String> params) throws JSONException {
		String json_data = encodeData(params);
		try {

			// Construct data
			String data = URLEncoder.encode("action", "UTF-8") + "="
					+ URLEncoder.encode(action, "UTF-8");
			data += "&" + URLEncoder.encode("data", "UTF-8") + "="
					+ URLEncoder.encode(json_data, "UTF-8");
			// Send data
			URL url = new URL(SERVER_URL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();
			StringBuilder answer = new StringBuilder();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				answer.append(line);
			}

			wr.close();
			rd.close();
			returnData = new JSONArray(answer.toString());
			// returnData = new JSONObject(answer.toString());

		} catch (Exception e) {
			// Log.e("ERROR", "");
			// returnData = new JSONObject();
			// returnData.put("message", "ERROR");
		}
		return returnData;
	}

	private static String encodeData(HashMap<String, String> data) {
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, String> entry : data.entrySet()) {
			try {
				jsonObject.put(entry.getKey(), entry.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Log.i("Sent JSON", jsonObject.toString());
		return jsonObject.toString();

	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}
