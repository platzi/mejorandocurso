package com.mejorandola.ejemplo16.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mejorandola.ejemplo16.data.App;
import com.mejorandola.ejemplo16.models.Place;

public class PlacesMapFragment extends SupportMapFragment {
	private GoogleMap map;
	private HashMap<String, Marker> places_marker_map = new HashMap<String, Marker>();
	public static final LatLng MEJORANDOLA = new LatLng(4.667184,-74.059463);
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		map = getMap();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(MEJORANDOLA, 16));
        map.getUiSettings().setZoomControlsEnabled(false);
        
		App app = (App)getActivity().getApplicationContext();
		ArrayList<Place> places = app.getPlaces();
		for (Place place : places) {
  		  Marker marker = map.addMarker(new MarkerOptions()
        	.position(place.getLatLng())
        	.title(place.getTitle())
        	.snippet(place.getDescription())
        	.icon(BitmapDescriptorFactory.fromResource(place.getResourceMarker())));
  		    		  
  		  places_marker_map.put(place.getTitle(), marker);
		}         
    }
}
