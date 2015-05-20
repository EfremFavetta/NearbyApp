package com.progetto.nearby.navigationdrawer;

import java.util.ArrayList;

import com.progetto.nearby.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerItemCustomAdapter extends BaseAdapter {

	Context context;
	ArrayList<DrawerItemObject> items;

	public DrawerItemCustomAdapter(Context context, ArrayList<DrawerItemObject> data) {
		this.context = context;
		this.items = data;
	}

	@Override
	public int getCount() {

		return items.size();
	}

	@Override
	public DrawerItemObject getItem(int index) {
		return items.get(index);
	}

	@Override
	public long getItemId(int index) {
		DrawerItemObject items = getItem(index);
		return items.id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		if (convertView != null) {
			view = convertView;
		} else {
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.drawer_list_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.imgDrower);
			TextView textViewValore = (TextView) view
					.findViewById(R.id.txtTitleDrower);
			ViewHolder myHolder = new ViewHolder();
			myHolder.textViewValore = textViewValore;
			myHolder.imageView = imageView;
			view.setTag(myHolder);

		}

		DrawerItemObject myItems = items.get(position);

		ViewHolder holder = (ViewHolder) view.getTag();

		holder.textViewValore.setText("" + myItems.name);
		holder.imageView.setImageResource(myItems.icon);

		return view;
	}

	private class ViewHolder {
		TextView textViewValore;
		ImageView imageView;
	}

}
