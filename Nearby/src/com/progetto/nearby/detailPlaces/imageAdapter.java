package com.progetto.nearby.detailPlaces;

import java.util.ArrayList;

import com.loopj.android.image.SmartImageView;
import com.progetto.nearby.models.Image;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class imageAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<String> gallery;
	
	public imageAdapter(Context context, ArrayList<String> gallery) {
		super();
		this.context = context;
		this.gallery = gallery;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gallery.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		SmartImageView image = new SmartImageView(context);
		image.setImageUrl(gallery.get(arg0));
		return image;
	}

}
