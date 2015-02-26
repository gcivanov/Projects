package com.example.cinema.UI;

import java.security.InvalidParameterException;
import java.util.List;

import com.example.cinema.R;
import com.example.cinema.custom.view.ExpandableHeightGridView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

 

public class AdapterAllMoviesExpandableListView extends BaseExpandableListAdapter{
	private static final String TAG = "MyActivity";
	
	private Context myContext;
	private List<Integer> moviesPics;
	private String[] moviesNames;
	private String[][] coolString;
	private AdapterGridView gvAdapter ;
	private OnItemClickListener gridViewOnItemClickListener;
	
	
	public AdapterAllMoviesExpandableListView(Context c , List<Integer> moviesListPicsIDs , String[] moviesNameStringArray , String[][] moviesTimeProj ) throws InvalidParameterException {
		
		if( c != null && moviesListPicsIDs != null && moviesNameStringArray != null && moviesTimeProj!= null &&
				moviesListPicsIDs.size() == moviesNameStringArray.length && moviesTimeProj.length == moviesNameStringArray.length ) {
			
			myContext = c;
			moviesPics = moviesListPicsIDs;
			moviesNames = moviesNameStringArray;
			coolString = moviesTimeProj;
			
			Log.v(TAG, "Constr");
			
		} else {
			throw new InvalidParameterException(" invalid parameters in CinemaAdapter(...)");
		}
	}
	
	public AdapterAllMoviesExpandableListView(Context c , List<Integer> moviesListPicsIDs ,
		String[] moviesNameStringArray , String[][] moviesTimeProj, OnItemClickListener gridViewOnItemClickListener ) throws InvalidParameterException {
		
		if( c != null && moviesListPicsIDs != null && moviesNameStringArray != null && moviesTimeProj!= null &&
				moviesListPicsIDs.size() == moviesNameStringArray.length && moviesTimeProj.length == moviesNameStringArray.length &&
				gridViewOnItemClickListener != null ) {
			
			myContext = c;
			moviesPics = moviesListPicsIDs;
			moviesNames = moviesNameStringArray;
			coolString = moviesTimeProj;
			this.gridViewOnItemClickListener = gridViewOnItemClickListener;
			
		} else {
			throw new InvalidParameterException(" invalid parameters in CinemaAdapter(...)");
		}
	}
	
	public void setOnItemClickListenerForGridView(OnItemClickListener onItemClickListener) {
		if( onItemClickListener != null )
			this.gridViewOnItemClickListener = onItemClickListener;
	}
	
	@Override
	public int getGroupCount() {
		return moviesPics.size();
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		return moviesNames[groupPosition];
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return coolString[groupPosition][childPosition];
	}
	
	@Override
	public long getGroupId(int groupPosition) {
		return moviesPics.get(groupPosition);
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		

		Log.v(TAG, " getGroupVIew");
		
		View rowGroup = convertView;
		HolderGroup holderGroup = new HolderGroup();
		
		if(rowGroup == null ) {
			
			LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowGroup = inflater.inflate(R.layout.adapter_layout_group, null);
			
			
			
			holderGroup.textV = (TextView) rowGroup.findViewById(R.id.textView);
			holderGroup.imageV = (ImageView) rowGroup.findViewById(R.id.imageView);
			
			rowGroup.setTag(holderGroup);
		} else {
			holderGroup = (HolderGroup) rowGroup.getTag();
		}
		
		holderGroup.imageV.setImageResource(moviesPics.get(groupPosition));
		holderGroup.textV.setText(moviesNames[groupPosition]);
		
		return rowGroup;
	}

	
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		Log.v(TAG, " getChild ");
		View rowChild = convertView;
		HolderChild holderChild = new HolderChild();
		
		if(rowChild == null ) {	
			LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowChild = inflater.inflate(R.layout.adapter_layout_child, null);
			
			holderChild.gridV = (ExpandableHeightGridView) rowChild.findViewById(R.id.gridView1);
			
			rowChild.setTag(holderChild);
			
			
		} else {
			holderChild = (HolderChild) rowChild.getTag();
		}
		
		gvAdapter = new AdapterGridView(myContext , coolString[groupPosition]);
		gvAdapter.setTicketPicId(myContext.getResources().getIdentifier("movie_ticket", "drawable", myContext.getPackageName()));
		
		gvAdapter.setMovieName(moviesNames[groupPosition]);
		
		
		holderChild.gridV.setAdapter(gvAdapter);
		
		holderChild.gridV.setOnItemClickListener( gridViewOnItemClickListener );
		
		return rowChild;
	}
	
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	class HolderGroup {
		ImageView imageV;
		TextView textV;
		
		//
		View view;
	}
	class HolderChild {
		ExpandableHeightGridView gridV;
	}
	

}
