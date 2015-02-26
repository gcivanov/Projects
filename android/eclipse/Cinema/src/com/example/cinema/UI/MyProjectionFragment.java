package com.example.cinema.UI;

import java.util.ArrayList;

import com.example.cinema.R;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MyProjectionFragment extends Fragment implements View.OnTouchListener {
	
	private GestureDetector gestureDetector;
	
	
	ArrayList<Integer> ar;
	String[] moviesArr;
	String[][] coolString;
	public MyProjectionFragment(){
		
		
		
		ar = new ArrayList<Integer>();
		ar.add(R.drawable.lord_of_the_rings_the_two_towers);
		ar.add(R.drawable.the_godfather_2);
//		ar.add(R.drawable.the_hobbit_inglourious_basterds);
//		ar.add(R.drawable.star_wars);
//		ar.add(R.drawable.rush);
//		ar.add(R.drawable.inglourious_basterds);
		
		moviesArr = new String[]{" The Lord of the Rings: The Two Towers ", " The Godfather: Part II "};//,
//				" The Hobbit: An Unexpected Journey "," Star Wars "," Rush ", " Inglourious Basterds "};
		
		coolString = new String[][]{{"16:50"},{"15:35","16:50"}};
//		,{"20:10"},{"15:35","16:50"},{"18:30"},
//				{"18:30","19:40","20:10","15:35","16:50","18:30","19:40","20:10"}};
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		setRetainInstance(true);
		super.onCreate(savedInstanceState);
		
	}
	
	public void setGestureDetector ( GestureDetector gestureDetector) {
		if(gestureDetector != null) {
			this.gestureDetector = gestureDetector;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ListView listView = new ListView(getActivity());
		
		
		
		
		try {
			listView.setAdapter(new AdapterMyMoviesListView(getActivity(), ar , moviesArr, coolString));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		listView.setBackgroundColor(Color.WHITE);
		
		listView.setClickable(false);
		
		listView.setOnTouchListener(this);
		
		return listView;
	}
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(gestureDetector != null)
			return gestureDetector.onTouchEvent(event);
		return false;
	}


	
}
