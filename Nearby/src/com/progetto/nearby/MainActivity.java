package com.progetto.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.progetto.nearby.home.HomeActivity;

public class MainActivity extends Activity {

	private Button btnHome, btnScopri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnHome = (Button) findViewById(R.id.btnHome);
		btnHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
				startActivity(homeIntent);
				MainActivity.this.finish();
			}
		});
	}
}
