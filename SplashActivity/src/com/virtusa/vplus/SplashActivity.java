package com.virtusa.vplus;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				finish();
				Intent loginIntent = new Intent(SplashActivity.this,LoginActivity.class);
				startActivity(loginIntent);
				overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
			}
		}, 2000);
	}
}
