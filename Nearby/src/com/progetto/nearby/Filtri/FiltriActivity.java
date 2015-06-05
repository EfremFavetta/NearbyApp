package com.progetto.nearby.Filtri;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.progetto.nearby.R;
import com.progetto.nearby.Tools;

public class FiltriActivity extends Activity {

	/*private TextView text;
	private Button insert;
	private AsyncHttpClient client;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtri);
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
				JSONObject object = new JSONObject();
				StringEntity entity = null;
				try {
					object.put("title", "zecchi gay!");
					entity = new StringEntity(object.toString());
					entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				client.post(getApplicationContext(), "http://nearby.altervista.org/test/index.php/insert_offer", entity, "application/json", new JsonHttpResponseHandler(){

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							JSONArray response) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, headers, response);
						Log.d("result", response.toString());
						Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseString, Throwable throwable) {
						// TODO Auto-generated method stub
						super.onFailure(statusCode, headers, responseString, throwable);
					}
					
				});
			}
		});
		
	}*/
	
	public final static String TAG = "FILTRI_ACTIVITY";
	
	SharedPreferences sharedPreferences;
	
	private SeekBar seekbarDistanza;
	private Spinner spinnerCategorie;
	private RadioGroup radioTipologia;
	private Button btnCerca;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtri);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		sharedPreferences = getSharedPreferences(Tools.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
		
		seekbarDistanza = (SeekBar)findViewById(R.id.seek_bar_distanza);
		spinnerCategorie = (Spinner)findViewById(R.id.spinnerCategorie);
		radioTipologia = (RadioGroup)findViewById(R.id.rdoTipologia);
		btnCerca = (Button)findViewById(R.id.btnCerca);
		
		
		// Setta barra della distanza
		int distanza = sharedPreferences.getInt(Tools.PREFERNCES_DISTANZA, Tools.FILTRO_DISTANZA_DEFAULT);
		int progress;
		switch (distanza) {
		case 500:
			progress = 0;
			break;
			
		case 1000:
			progress = 1;
			break;
			
		case 2000:
			progress = 2;
			break;
			
		case 5000:
		default:
			progress = 3;
			break;
			
		case 10000:
			progress = 4;
			break;
			
		case 20000:
			progress = 5;
			break;
			
		case 50000:
			progress = 6;
			break;
		}
		seekbarDistanza.setProgress(progress);
		
		
		// Setta spinner categorie
		List<String> lstCategorie;
		//caricare categorie
		//ArrayAdapter<String> categorieAdapter = new ArrayAdapter<String>(this, R.id.spinnerCategorie, lstCategorie);
			//categorieAdapter.setDropDownViewResource(resource);
		//spinnerCategorie.setAdapter(categorieAdapter);
		
		
		// Setta radioButton tipologia (Attività commerciale o POI)
		boolean flag = sharedPreferences.getBoolean(Tools.PREFERNCES_TIPOLOGIA, true);
		if(flag)
			radioTipologia.check(R.id.rbAC);
		else
			radioTipologia.check(R.id.rbPOI);
		
		
		btnCerca.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	protected void onDestroy() { // Salvo lo stato dei filtri all'uscita dell'activity
		
		// Salva distanza
		int distanza;
		switch (seekbarDistanza.getProgress()) {
			case 0:
				distanza = 500;
				break;
				
			case 1:
				distanza = 1000;
				break;
				
			case 2:
				distanza = 2000;
				break;
				
			case 3:
			default:
				distanza = 5000;
				break;
				
			case 4:
				distanza = 10000;
				break;
				
			case 5:
				distanza = 20000;
				break;
				
			case 6:
				distanza = 50000;
				break;
		}
		
		
		// Salva categoria
		String categoria = spinnerCategorie.getSelectedItem().toString();
		
		
		// Salva tipologia (Attività commerciale o POI)
		// true = selezionato "Attivita commerciale"
		// false = selezionato "POI"
		boolean flag = radioTipologia.getCheckedRadioButtonId() == R.id.rbAC;
		
		
		sharedPreferences.edit()
			.putInt(Tools.PREFERNCES_DISTANZA, distanza)
			.putString(Tools.PREFERNCES_CATEGORIA, categoria)
			.putBoolean(Tools.PREFERNCES_TIPOLOGIA, flag)
			.apply();
		
		super.onDestroy();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
        	onBackPressed();
            return true;
        }
		return super.onOptionsItemSelected(item);
	}
	
}
