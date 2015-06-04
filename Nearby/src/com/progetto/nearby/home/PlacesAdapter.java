package com.progetto.nearby.home;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.progetto.nearby.R;
import com.progetto.nearby.models.Place;

public class PlacesAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Place> PlacesList;
	
	public PlacesAdapter(Context context, ArrayList<Place> placesList) {
		super();
		this.context = context;
		PlacesList = placesList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PlacesList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return PlacesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Place newpoi = (Place) getItem(position);
		return newpoi.id;
	}

	private class PlacesViewHolder
	{
		TextView txtNome,txtDistanza, txtCitta;
		ImageView imgPlace;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		View places_view = null;
		PlacesViewHolder holder = new PlacesViewHolder();
		if(convertView != null)
			places_view = convertView;
		else
		{
			places_view = inflater.inflate(R.layout.cell_places, null);
			holder.imgPlace = (ImageView) places_view.findViewById(R.id.imgPlace);
			holder.txtNome = (TextView) places_view.findViewById(R.id.txtNomePlace);
			holder.txtCitta = (TextView) places_view.findViewById(R.id.txtCittaPlace);
			holder.txtDistanza = (TextView) places_view.findViewById(R.id.txtDistanzaPlace);
			places_view.setTag(holder);
		}
		
		Place places = (Place) getItem(position);
		PlacesViewHolder getPlaceHolder = (PlacesViewHolder) places_view.getTag();
		getPlaceHolder.txtNome.setText(places.nome);
		getPlaceHolder.txtCitta.setText(places.città);
		getPlaceHolder.txtDistanza.setText("" + places.distanza + " Km");
		return places_view;
	}

}
