package com.mejorandola.ejemplo06.data;

import java.util.ArrayList;

import com.mejorandola.ejemplo06.R;
import com.mejorandola.ejemplo06.models.Room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class  CustomAdapter extends ArrayAdapter<Room> {
	ArrayList<Room> data;
	LayoutInflater inflater;
	
	public CustomAdapter(Context context, ArrayList<Room> objects) {
		super(context, -1, objects);
		this.data = objects;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    ViewHolder holder;
	    Room current_room = data.get(position);
	    String room_type = current_room.getRoomType();
	    
	    if (convertView == null) {
	        convertView = inflater.inflate(R.layout.list_row, null);

	        holder = new ViewHolder();
	        holder.img = (ImageView) convertView.findViewById(R.id.img_row);
	        holder.title = (TextView) convertView.findViewById(R.id.txt_row_title);
	        holder.subtitle = (TextView) convertView.findViewById(R.id.txt_row_subtitle);
	        
	        convertView.setTag(holder);
	    } else {
	        holder = (ViewHolder) convertView.getTag();
	    }

	    int img_resource = -1;
	    if (room_type.equals(Room.STANDARD_ROOM)) {
	    	img_resource = R.drawable.hotel1;
	    } else {
	    	img_resource = R.drawable.hotel2; 
	    }
	    
	    holder.title.setText(current_room.getRoomNumber());
	    holder.subtitle.setText(room_type);
	    holder.img.setImageResource(img_resource);
	    return convertView;
	}
	
	private static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView subtitle;
	}	
}
