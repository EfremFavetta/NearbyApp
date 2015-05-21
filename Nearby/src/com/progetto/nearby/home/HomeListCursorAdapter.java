package com.progetto.nearby.home;

import com.progetto.nearby.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeListCursorAdapter extends CursorAdapter {

	private class ViewHolder {
		public TextView lblNome;
		public TextView lblPaese;
		public TextView lblDistanza;
		public ImageView img;
	}
	
	public HomeListCursorAdapter(Context context, Cursor c) {
		super(context, c, 0);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater vInflater = LayoutInflater.from(context);
		View vView = vInflater.inflate(R.layout.row_home_list, null);
		ViewHolder vHolder = new ViewHolder();
		
		vHolder.img = (ImageView)vView.findViewById(R.id.imgRowHomeList);
		vHolder.lblNome = (TextView)vView.findViewById(R.id.lblNomePlace);
		vHolder.lblPaese = (TextView)vView.findViewById(R.id.lblPaesePlace);
		vHolder.lblDistanza = (TextView)vView.findViewById(R.id.lblDistanzaPlace);
		vView.setTag(vHolder);
		
		return vView;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		/*
		int nomeColumnIndex = cursor.getColumnIndex(AlberghiTableHelper.KEY_NOME);
		int imgColumnIndex = cursor.getColumnIndex(AlberghiTableHelper.KEY_VALUTAZIONE);
		int paeseColumnIndex = cursor.getColumnIndex(AlberghiTableHelper.KEY_VALUTAZIONE);
		int distanzaColumnIndex = cursor.getColumnIndex(AlberghiTableHelper.KEY_VALUTAZIONE);
		
		String nome = cursor.getString(nomeColumnIndex);
		String paese = cursor.getString(paeseColumnIndex);
		int distanza = cursor.getInt(distanzaColumnIndex);
		String img = cursor.getString(imgColumnIndex);
		
		ViewHolder vHolder = (ViewHolder)view.getTag();
		vHolder.lblNome.setText(nome);
		vHolder.lblPaese.setText(paese);
		vHolder.lblDistanza.setText("" + distanza + "km");
		*/
	}	
}
