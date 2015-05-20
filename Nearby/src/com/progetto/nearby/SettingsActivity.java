package com.progetto.nearby;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SettingsActivity extends Activity {

	private TextView text;
	private Button insert;
	private AsyncHttpClient client;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		text = (TextView) findViewById(R.id.textView1);
		insert = (Button) findViewById(R.id.button1);
		client = new AsyncHttpClient();
		client.get("http://nearby.altervista.org/api.php?action=get_places", new JsonHttpResponseHandler()
		{

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				text.setText("" + response.toString());
			}
			
		});
		insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONObject jsonParams = new JSONObject();
				
		        try {
					jsonParams.put("notes", "Test api support");
					HttpEntity entity;
					entity = new StringEntity(jsonParams.toString());
					//entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
					client.post(getApplicationContext(), "http://nearby.altervista.org/api.php?action=insert_offerts", entity, "application/json",
			                new JsonHttpResponseHandler(){

								@Override
								public void onSuccess(int statusCode,
										Header[] headers, JSONObject response) {
									// TODO Auto-generated method stub
									//super.onSuccess(statusCode, headers, response);
									Log.d("2", response.toString());
									Log.d("2", response.toString());
									
									text.setText("" + response.toString());
								}
			        	
			        });
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
			}
		});
		
	}
}
