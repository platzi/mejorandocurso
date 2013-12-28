package com.mejorandola.ejemplo20.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mejorandola.ejemplo20.R;
import com.mejorandola.ejemplo20.data.App;
import com.mejorandola.ejemplo20.fragments.dialogs.SelectMapTypeDialog;
import com.mejorandola.ejemplo20.models.Place;

public class PlacesMapFragment extends SupportMapFragment implements InfoWindowAdapter,
																	 SelectMapTypeDialog.DialogListener {
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
        
        map.setMyLocationEnabled(true);
        
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.places_map_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
    		case R.id.action_draw_route:
    			drawRoute();
    			return true;        
        	case R.id.action_show_all_markers:
        		showAllMarkers();
        		return true;
        	case R.id.action_show_traffic:
        		boolean checked = item.isChecked();
        		item.setChecked(!checked);
        		map.setTrafficEnabled(!checked);
        		return true;
        	case R.id.action_map_type:
                SelectMapTypeDialog dialog = new SelectMapTypeDialog();
                dialog.show(getFragmentManager(), "maptype");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }    

	private void showAllMarkers() {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        Iterator<Entry<String, Marker>> iter = places_marker_map.entrySet().iterator();
        while (iter.hasNext()) {
            Marker m = iter.next().getValue();
            builder.include(m.getPosition());
        }
        CameraUpdate update = CameraUpdateFactory.newLatLngBounds(builder.build(), 100);
        map.animateCamera(update);
	}
	
    public void drawRoute() {
		ArrayList<LatLng> points = new ArrayList<LatLng>();
        Iterator<Entry<String, Place>> iter = places_map.entrySet().iterator();
        while (iter.hasNext()) {
        	points.add(iter.next().getValue().getLatLng());
        }    	
        PolylineOptions options = new PolylineOptions()
                .addAll(points)
                .color(0xFFCC0000)
                .width(8);
        map.addPolyline(options);
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

	@Override
	public void onSelectMapTypeDialogSelected(int type) {
        int map_type = GoogleMap.MAP_TYPE_NORMAL;
        switch (type) {
            case SelectMapTypeDialog.TIPO_NORMAL:
            	map_type = GoogleMap.MAP_TYPE_NORMAL;
                break;
            case SelectMapTypeDialog.TIPO_HIBRIDO:
            	map_type = GoogleMap.MAP_TYPE_HYBRID;
                break;
            case SelectMapTypeDialog.TIPO_SATELITE:
            	map_type = GoogleMap.MAP_TYPE_SATELLITE;
                break;
            case SelectMapTypeDialog.TIPO_TERRENO:
            	map_type = GoogleMap.MAP_TYPE_TERRAIN;
                break;
        }
        map.setMapType(map_type);
	}
	
	public int getCurrentMapType() {
        int map_type = GoogleMap.MAP_TYPE_NORMAL;
        switch(map.getMapType()){
            case GoogleMap.MAP_TYPE_NORMAL:
                map_type = SelectMapTypeDialog.TIPO_NORMAL;
                        break;
            case GoogleMap.MAP_TYPE_HYBRID:
                map_type = SelectMapTypeDialog.TIPO_HIBRIDO;
                break;
            case GoogleMap.MAP_TYPE_SATELLITE:
                map_type = SelectMapTypeDialog.TIPO_SATELITE;
                break;
            case GoogleMap.MAP_TYPE_TERRAIN:
                map_type = SelectMapTypeDialog.TIPO_TERRENO;        
        }       
        return map_type;
	}
}
