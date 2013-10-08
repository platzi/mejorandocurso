package com.mejorandola.ejemplo15.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class PlacesMapFragment extends SupportMapFragment {
	private GoogleMap map;
	public static final LatLng MEJORANDOLA = new LatLng(4.667184,-74.059463);
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
        map = getMap();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MEJORANDOLA, 16));
        map.getUiSettings().setZoomControlsEnabled(false);
    }
}
