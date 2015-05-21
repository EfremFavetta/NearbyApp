package com.progetto.nearby.home;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.progetto.nearby.R;
import com.progetto.nearby.Tools;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class HomeFragment extends MapFragment implements OnMapReadyCallback, LoaderCallbacks<Cursor>  {

	private GoogleMap googleMap;
	private ListView lstPlaces;
	private static boolean isFirstTimeOpen = true;
	private HomeListCursorAdapter cursorAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		
		((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment)).getMapAsync(this);
		
		lstPlaces = (ListView)rootView.findViewById(R.id.lstPlaces);
		cursorAdapter = new HomeListCursorAdapter(getActivity(), null);
		lstPlaces.setAdapter(cursorAdapter);
		
		lstPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				enterDetails(id);
			}
		});
		
		super.onCreateView(inflater, container, savedInstanceState);
		return rootView;
	}

	protected void enterDetails(long id) {
		/*
		Intent intent = new Intent(getActivity(), DettagliPlaceActivity.class);
    	intent.putExtra(DettagliPlaceActivity.ID_PLACE, (int)id);
    	
        startActivity(intent);
        */
	}

	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
		
		return fragment;
	}

	@Override
	public void onMapReady(GoogleMap map) {
		googleMap = map;
		if(googleMap != null) {
			googleMap.setMyLocationEnabled(true);
	        //Tools.gpsTracker = new GPSTracker(getActivity());
	        
	        if(Tools.gpsProvider.canGetLocation()){
	        	centerMyPosition();
	        } else {
	        	if(isFirstTimeOpen) {
	        		Tools.gpsProvider.showSettingsAlert();
	        		isFirstTimeOpen = false;
	        	}
	        	CameraPosition cameraPosition = new CameraPosition
						.Builder()
				        .target(new LatLng(43.028316, 12.460283))
				        .zoom(5.2f)
				        .build();
	        	googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	        }
	        
	        googleMap.getUiSettings().setZoomControlsEnabled(true);
	        decoreMap();
	    	googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
				@Override
				public void onInfoWindowClick(Marker marker) {
					/*
					Intent intent = new Intent(getActivity(), DettagliPlaceActivity.class);
			    	intent.putExtra(DettagliPlaceActivity.ID_PLACE, Tools.markersList.get(marker).id);
			        startActivity(intent);
			        */
				}
			});
		}
	}
	
	private void decoreMap() {
		// TODO Auto-generated method stub
		
	}

	private void centerMyPosition() {
    	CameraPosition cameraPosition = new CameraPosition
    									.Builder()
								        //.target(Tools.gpsTracker.getLatLng())
								        .zoom(5.8f)
								        .build();
    	googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
	
	@Override
	public void onDestroy() {
		if(googleMap != null)
			googleMap.setMyLocationEnabled(false);
		super.onDestroy();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		cursorAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		cursorAdapter.swapCursor(null);
	}	
}
