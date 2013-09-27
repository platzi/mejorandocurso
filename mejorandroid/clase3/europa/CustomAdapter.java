package com.mejorandola.ejemplo.data;

import java.util.ArrayList;

import com.mejorandola.ejemplo.models.Room;

import android.content.Context;
import android.widget.ArrayAdapter;

public class  CustomAdapter extends ArrayAdapter<Room> {

	public CustomAdapter(Context context, ArrayList<Room> objects) {
		super(context, -1, objects);
		// TODO Auto-generated constructor stub
	}
}
