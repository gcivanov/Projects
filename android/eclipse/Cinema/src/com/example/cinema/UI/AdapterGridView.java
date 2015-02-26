package com.example.cinema.UI;



import java.util.ArrayList;
import java.util.List;

import com.example.cinema.R;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AdapterGridView extends BaseAdapter {
	
	private final static String NOTIFICATION_ACTION = "com.example.cinema.notification_area";
	private final static String NOTIFICATION_KEY_STRING_TITLE = "NotificationDataStringTitle";
	private final static String NOTIFICATION_KEY_STRING_TEXT = "NotificationDataStringText";
	private final static String NOTIFICATION_KEY_INT = "NotificationDataInt";
	
	private Context myContext;
	private String[] myTimes;
	private String movieName = "";
	private boolean clickabalButton = false;
	private int ticketPicId;
	private static final String TAG = "tagAdapter";
	
	
	private List<Drawable> listDr;
	
	public AdapterGridView(Context context , String[] times ) {
		if(context != null && times != null) {
			myContext = context;
			myTimes = times;
			
		}
		listDr = new ArrayList<Drawable>();
		
	}
	public void setTicketPicId(int ticketPicId ) {
		this.ticketPicId = ticketPicId;
	}
	
	public void setClickable(boolean isClickable){
		clickabalButton = isClickable;
	}
	
	public void setMovieName(String name) {
		if(name != null) {
			movieName = name;
		}
	}
	
	@Override
	public int getCount() {
		return myTimes.length;
	}

	@Override
	public Object getItem(int position) {
		return myTimes[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public boolean isEnabled(int position) {
		return true;//super.isEnabled(position);
	}
	
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) convertView;

		if (view == null) {
			view = new TextView(myContext);
			view.setTextSize(20f);
			view.setGravity(Gravity.CENTER);
			view.setBackgroundResource(R.drawable.button_shape);
		}
		
		String title = myTimes[position];
		view.setText(title);
		if(movieName != null)
			view.setTag(movieName + " at " + title);
		else 
			view.setTag("reservation at " + title);
		
		
		view.setTag(movieName + " at " + title);

		return view;
	}
	
	
//	@Override
	public void onClick(View v) {
		if( clickabalButton ) {
			
			
			final Button but = (Button) v;
			
			//Alert Dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
	    	builder
	    	.setTitle("Book Movies Tickets")
	    	.setMessage("Are you sure? "+"\n"+movieName+" at "+but.getText())
	    	.setIcon(android.R.drawable.ic_dialog_alert)
	    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	    public void onClick(DialogInterface dialog, int which) {
	    	    	callNotificationReceiver(but.getText().toString());
	    	    }
	    	})
	    	.setNegativeButton("No", null)
	    	.show();
	    		
		}
	}
	
	private void callNotificationReceiver (String time) {
		
		Intent notifIntent = new Intent(NOTIFICATION_ACTION);
		notifIntent.setType("TEXT/*");
		notifIntent.putExtra(NOTIFICATION_KEY_STRING_TITLE, movieName + " at " + time);
		notifIntent.putExtra(NOTIFICATION_KEY_STRING_TEXT, time + " dont forget");
		notifIntent.putExtra(NOTIFICATION_KEY_INT, ticketPicId);
		
		
		AlarmManager alarmManager = (AlarmManager) myContext.getSystemService(Context.ALARM_SERVICE);
		
		PendingIntent myAlarmIntent = PendingIntent.getBroadcast(myContext, 0, notifIntent, 0);
		
		alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime()+ 5*1000, myAlarmIntent);
		
	}
}