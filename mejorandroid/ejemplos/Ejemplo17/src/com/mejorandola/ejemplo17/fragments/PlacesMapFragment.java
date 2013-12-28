package com.mejorandola.ejemplo17.fragments;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mejorandola.ejemplo17.R;
import com.mejorandola.ejemplo17.data.App;
import com.mejorandola.ejemplo17.models.Place;

public class PlacesMapFragment extends SupportMapFragment implements InfoWindowAdapter {
	private GoogleMap map;	
	private RequestQueue image_request_queue;
	private LruCache<Place, Bitmap> thumbnails;
	private HashMap<String, Place> places_map = new HashMap<String, Place>();
	private HashMap<String, Marker> places_marker_map = new HashMap<String, Marker>();
	
	private static final int CACHE_SIZE_BYTES = 4 * 1024 * 1024; // 4 MB
	public static final LatLng MEJORANDOLA = new LatLng(4.667184,-74.059463);
		
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		map = getMap();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(MEJORANDOLA, 16));
        map.getUiSettings().setZoomControlsEnabled(false);
        map.setInfoWindowAdapter(this);
        
        thumbnails = new LruCache<Place, Bitmap>(CACHE_SIZE_BYTES) {
            @Override
            protected int sizeOf(Place key, Bitmap value) {
                return value.getByteCount();

            }};        
        image_request_queue = Volley.newRequestQueue(getActivity());
        image_request_queue.start();
        
		App app = (App)getActivity().getApplicationContext();
		for (Place place : app.getPlaces()) {
  		  Marker marker = map.addMarker(new MarkerOptions()
        	.position(place.getLatLng())
        	.title(place.getTitle())
        	.snippet(place.getDescription())
        	.icon(BitmapDescriptorFactory.fromResource(place.getResourceMarker())));
  		    		  
  		  places_marker_map.put(place.getTitle(), marker);
  		  places_map.put(place.getTitle(), place);
		}         
    }

	@Override
	public View getInfoContents(final Marker marker) {
		final Place current_place = places_map.get(marker.getTitle());
        if (thumbnails.get(current_place) == null) {
        	image_request_queue.add(
                    new ImageRequest(current_place.getPictureUrl(), new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                        	thumbnails.put(current_place, bitmap);
                            if (marker.isInfoWindowShown()) {
                                marker.showInfoWindow();
                            };
                        }
                    }, 256, 256, Config.ARGB_4444, null));
        }
        
        View window = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_info_window, null);
        
        TextView txt_title = (TextView)window.findViewById(R.id.txt_title);
        TextView txt_snippet = (TextView)window.findViewById(R.id.txt_snippet);
        ImageView img_thumbnail = (ImageView)window.findViewById(R.id.img_thumbnail);
        txt_title.setText(marker.getTitle());
        txt_snippet.setText(marker.getSnippet());
        
        Bitmap bitmap = thumbnails.get(current_place);
        if (bitmap != null) {
        	img_thumbnail.setImageBitmap(bitmap);
        }
        
        return window;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		return null;
	}
}
