package com.example.cinema.UI;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterHallSeats extends BaseAdapter{
	
	private final static int padding = 6;
	private Context context;
	private int num;
	
	
	public AdapterHallSeats(Context context,int num) {
		if(context != null)
			this.context = context;
		this.num=num;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView viewButton = (TextView) convertView;
		if(viewButton == null) {
			viewButton = new TextView(context);
			viewButton.setPadding(padding, padding, padding, padding);
			viewButton.setText(""+ ++position);
			viewButton.setBackgroundResource(context.getResources().getIdentifier("button_seat_free", "drawable", context.getPackageName()));
			
		}
		
		
		return viewButton;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return num;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
