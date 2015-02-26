package com.example.cinema.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.cinema.R;
import com.example.cinema.custom.view.ExpandableHeightGridView;

public class MyMoviesView extends RelativeLayout {

	private ImageView movieIcon;
	private TextView movieTitle;
//	private GridView movieSchedule;
	private ExpandableHeightGridView movieSchedule;

	public MyMoviesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflateLayouts();
		initMembers();
	}

	private void inflateLayouts() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflateMainLayout(inflater);
	}
	
	private void inflateMainLayout(LayoutInflater inflater) {
		inflater.inflate(R.layout.adapter_list_item_my_movies, this, true);
		
	}

	private void initMembers() {
		movieIcon = (ImageView) findViewById(R.id.movie_thumbnail);
		movieTitle = (TextView) findViewById(R.id.movie_title);
		movieSchedule = (ExpandableHeightGridView) findViewById(R.id.movie_schedule);
	}
	
	
	
	public void setSchedule(String[] schedule, OnItemClickListener onItemClick) {
		
		movieSchedule.setAdapter(new AdapterGridView(getContext(),schedule));
		
		movieSchedule.setOnItemClickListener(onItemClick);
	}

	public void setImage(int image) {
		movieIcon.setImageResource(image);
	}

	public void setTitle(String title) {
		movieTitle.setText(title);
	}
}
