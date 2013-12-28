package com.mejorandola.ejemplo20.models;

import com.mejorandola.ejemplo20.R;

import com.google.android.gms.maps.model.LatLng;

public class Place {
	private int type;	
	private String title;
	private double[] location;
	private String description;
	private String picture_url;

	public final static int TYPE_HOSTEL = 0;
	public final static int TYPE_STANDARD = 1;
	public final static int TYPE_LUXURY = 2;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getResourceMarker() {
		int resource = 0;
		switch (type) {
			case TYPE_HOSTEL:
				resource = R.drawable.marker_hostel;
				break;
			case TYPE_STANDARD:
				resource = R.drawable.marker_standard;
				break;
			case TYPE_LUXURY:
				resource = R.drawable.marker_luxury;
				break;				
		}
		return resource;
	}
	public double[] getLocation() {
		return location;
	}
	public LatLng getLatLng() {
		return new LatLng(location[0], location[1]);
	}
	public void setLocation(double[] location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPictureUrl() {
		return picture_url;
	}
	
	public void setPictureUrl(String picture_url) {
		this.picture_url = picture_url;
	}
	
	public String toString() {
		return "Type: " + type +
			   "\nTitle: " + title + 
			   "\nDescription: " + description +
			   "\nPicture url: " + picture_url +
			   "\nLocation: " + location[0] + "," + location[1];
	}
}
