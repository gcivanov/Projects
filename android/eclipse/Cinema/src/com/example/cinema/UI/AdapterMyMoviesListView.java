package com.example.cinema.UI;

import java.security.InvalidParameterException;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterMyMoviesListView extends BaseAdapter {

	private Context myContext;
	private List<Integer> myListOfPicIDs;
	private String[] myMoviesName;
	private String[][] myMoviesTime;
	AdapterGridView myAdapter;
	
	// String[] coolString =
	// {"10:10","12:30","15:35","16:50","18:30","19:40","20:10","15:35","16:50","18:30","19:40","20:10"};

	// http://stackoverflow.com/questions/8166497/custom-adapter-for-list-view

	public AdapterMyMoviesListView(Context c, List<Integer> list,
			String[] moviesName, String[][] time ) throws Exception {
		
		if (c != null && list != null && moviesName != null && time != null
				&& list.size() == moviesName.length
				&& time.length == moviesName.length) {
			myContext = c;
			myListOfPicIDs = list;
			myMoviesName = moviesName;
			myMoviesTime = time;
			
//			Toast.makeText(myContext, " constr", Toast.LENGTH_SHORT).show();
		} else {
			throw new InvalidParameterException(
					" invalid parameters in CinemaAdapter(...)");
		}
	}

	@Override
	public int getCount() {
		return myListOfPicIDs.size();
	}

	@Override
	public Object getItem(int position) {
		if (myMoviesName != null) {
			return myMoviesName[position];
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return myListOfPicIDs.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyMoviesView view = (MyMoviesView) convertView;

		if (view == null) {
			view = new MyMoviesView(myContext, null);
		}

		view.setImage(myListOfPicIDs.get(position));
		view.setTitle(myMoviesName[position]);
		view.setSchedule(myMoviesTime[position], null);
		
		return view;
	}

}


