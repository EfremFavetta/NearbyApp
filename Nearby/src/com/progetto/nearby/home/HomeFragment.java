package com.progetto.nearby.home;

import com.progetto.nearby.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		
		super.onCreateView(inflater, container, savedInstanceState);
		
		return rootView;
	}

	public static Fragment newInstance() {
		HomeFragment fragment = new HomeFragment();
		
		return fragment;
	}
	
}
