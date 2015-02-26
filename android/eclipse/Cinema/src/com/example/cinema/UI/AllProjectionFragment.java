package com.example.cinema.UI;

import java.util.ArrayList;

import com.example.cinema.R;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class AllProjectionFragment extends Fragment implements View.OnTouchListener, OnItemClickListener {
	private ExpandableListView expandableListView;
	private GestureDetector gestureDetector;
	private FragmentTransaction fragmentTransaction;
	private int idThisFragment;
	private String HALL_FRAGMNET_TAG = "HALLFRAGMENT";
	
	
	
	ArrayList<Integer> ar;
	String[] stringArray;
	String[][] coolString;
	public AllProjectionFragment() {
//		List<Integer> newList = getArguments().getIntegerArrayList("MYKEY!");
//		i v onCreate E OK
//		fragmentTransaction = getFragmentManager().beginTransaction();
	
		ar = new ArrayList<Integer>();
		ar.add(R.drawable.lord_of_the_rings_the_two_towers);
		ar.add(R.drawable.the_shawshank_redemption);
		ar.add(R.drawable.the_godfather);
		ar.add(R.drawable.the_godfather_2);
		ar.add(R.drawable.the_dark_knight);
		ar.add(R.drawable.pulp_fiction);
		ar.add(R.drawable.the_hobbit_inglourious_basterds);
		ar.add(R.drawable.star_wars);
		ar.add(R.drawable.rush);
		ar.add(R.drawable.inglourious_basterds);
		
		
		stringArray = new String[]{" The Lord of the Rings: The Two Towers "," The Shawshank Redemption "," The Godfather ", " The Godfather: Part II ",
				" The Dark Knight "," Pulp Fiction ",
				" The Hobbit: An Unexpected Journey "," Star Wars "," Rush ", " Inglourious Basterds "};
		
		coolString = new String[][]{{"10:10","12:30","15:35","16:50","18:30","19:40","20:10","15:35","16:50"},{"15:35","16:50"},{"18:30","19:40","20:10","15:35","16:50"},
				{"10:10","12:30","18:30","19:40","20:10"},{"15:35","16:50"},{"18:30","19:40","20:10","15:35","16:50","18:30","19:40","20:10"},
				{"10:10","12:30","18:30","19:40","20:10"},{"15:35","16:50"},{"18:30","19:40","20:10","15:35","16:50","18:30","19:40","20:10"},
				{"10:10","12:30","15:35","16:50","18:30","19:40","20:10","15:35","16:50"}};
		
	
	
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setRetainInstance(true);
		super.onCreate(savedInstanceState);
		idThisFragment = this.getId();
		fragmentTransaction = getFragmentManager().beginTransaction();
	}
	
	public void setGestureDetector(GestureDetector gd ) {
		if ( gd != null) {
			gestureDetector = gd;
		}
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


//		fragmentTransaction = getFragmentManager().beginTransaction();
		
		View rootView = inflater.inflate(R.layout.fragment_projections, container,false);
		
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(R.drawable.lord_of_the_rings_the_two_towers);
		ar.add(R.drawable.the_shawshank_redemption);
		ar.add(R.drawable.the_godfather);
		ar.add(R.drawable.the_godfather_2);
		ar.add(R.drawable.the_dark_knight);
		ar.add(R.drawable.pulp_fiction);
		ar.add(R.drawable.the_hobbit_inglourious_basterds);
		ar.add(R.drawable.star_wars);
		ar.add(R.drawable.rush);
		ar.add(R.drawable.inglourious_basterds);
		
		String[] stringArray = {" The Lord of the Rings: The Two Towers "," The Shawshank Redemption "," The Godfather ", " The Godfather: Part II ",
				" The Dark Knight "," Pulp Fiction ",
				" The Hobbit: An Unexpected Journey "," Star Wars "," Rush ", " Inglourious Basterds "};
		
		String[][] coolString = {{"10:10","12:30","15:35","16:50","18:30","19:40","20:10","15:35","16:50"},{"15:35","16:50"},{"18:30","19:40","20:10","15:35","16:50"},
				{"10:10","12:30","18:30","19:40","20:10"},{"15:35","16:50"},{"18:30","19:40","20:10","15:35","16:50","18:30","19:40","20:10"},
				{"10:10","12:30","18:30","19:40","20:10"},{"15:35","16:50"},{"18:30","19:40","20:10","15:35","16:50","18:30","19:40","20:10"},
				{"10:10","12:30","15:35","16:50","18:30","19:40","20:10","15:35","16:50"}};
		
		expandableListView = (ExpandableListView) rootView.findViewById(R.id.listView1);
		

		try {
			expandableListView.setAdapter(new AdapterAllMoviesExpandableListView(getActivity(), ar, stringArray, coolString,this));
			
		} catch (Exception e ) { e.printStackTrace(); }
//		expandableListView.setBackgroundColor(Color.WHITE);
		expandableListView.setClickable(true);
		
		expandableListView.setOnTouchListener(this);
		
		return rootView;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (gestureDetector != null)
			return gestureDetector.onTouchEvent(event);
		return false;
	}
	
	
	//onItemClick-  for GridView
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		((ProjectionsActivity)getActivity()).setNullToGestureDetector();
		TextView tv;
		tv = (TextView) view;
		
		HallFragment hallFr = new HallFragment();
		Bundle b = new Bundle();
		b.putString("key", tv.getTag() .toString());
		
		hallFr.setArguments(b);
		
		
		getActivity().getActionBar().hide();
		fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.fragmentForProjections, hallFr,HALL_FRAGMNET_TAG).commit();
		
	}
	
	
	


}
