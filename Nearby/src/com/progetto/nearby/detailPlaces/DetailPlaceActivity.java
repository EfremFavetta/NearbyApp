package com.progetto.nearby.detailPlaces;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.progetto.nearby.R;
import com.progetto.nearby.Tools;
import com.progetto.nearby.models.Place;

public class DetailPlaceActivity extends Activity {

	private Gallery galleryPlace;
	private TextView txtNome, txtdescrizione, txtGPS, txtPhone, txtCitta;
	private imageAdapter imageAdapter;
	private int idPlace;
	private Place place;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		idPlace = (int) getIntent().getExtras().getLong(Place.tag_id);
		if(idPlace > 0)
			getPlace(idPlace);
		setupGUI();
	}

	private void setupGUI() {
		// TODO Auto-generated method stub
		galleryPlace = (Gallery) findViewById(R.id.galleryPlace);
		txtNome = (TextView) findViewById(R.id.txtDetNome);
		txtdescrizione = (TextView) findViewById(R.id.txtDetDescr);
		txtGPS = (TextView) findViewById(R.id.txtDetGPS);
		txtPhone = (TextView) findViewById(R.id.txtDetTelephone);
		txtCitta = (TextView) findViewById(R.id.txtDetTown);
	}

	private void getPlace(int idPlace) {
		// TODO Auto-generated method stub
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(Tools.GET_DETAIL_URL + idPlace, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				if(response != null)
				{
					place = Place.decodeJSON(response);
					updateDetailGUI();
				}
				else
					Toast.makeText(getApplicationContext(), 
							"Errore", Toast.LENGTH_LONG).show();
			}
			
			private void updateDetailGUI() {
				// TODO Auto-generated method stub
				Log.d("gg", place.gallery.toString());
				imageAdapter = new imageAdapter(getApplicationContext(), place.gallery);
				galleryPlace.setAdapter(imageAdapter);
				txtNome.setText(place.nome);
				txtdescrizione.setText(place.description);
				txtGPS.setText("" + place.lat + " - " + place.longit);
				txtPhone.setText(place.telefono);
				txtCitta.setText(place.città);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Toast.makeText(DetailPlaceActivity.this, "Errore nel recupero dei dati", Toast.LENGTH_LONG).show();
				super.onFailure(statusCode, headers, responseString, throwable);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONArray errorResponse) {
				Toast.makeText(DetailPlaceActivity.this, "Errore nel recupero dei dati", Toast.LENGTH_LONG).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(DetailPlaceActivity.this, "Errore nel recupero dei dati", Toast.LENGTH_LONG).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}
			
		});
	}
}
