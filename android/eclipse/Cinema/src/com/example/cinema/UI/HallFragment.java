package com.example.cinema.UI;


import com.example.cinema.R;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore.RightsStatus;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HallFragment extends Fragment implements View.OnTouchListener, OnClickListener, OnItemClickListener {
	
	private ImageView leftButton;
	private ImageView rightButton ;
	private TextView numReservation;
	private GridView seats;
	private int numSeats;
	private int numOfClicks;
	
	private String textFromArg;
	
	private Button reservation;
	
	
	
	public HallFragment() {
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setRetainInstance(true);
		textFromArg = getArguments().getString("key");
		
		onTouch(null, null);
		numSeats = 1;
		numOfClicks = 0;
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_hall, container, false);
		
		leftButton = (ImageView) v.findViewById(R.id.imageViewLeftButton);
		rightButton = (ImageView) v.findViewById(R.id.imageViewRightButton);
		numReservation = (TextView) v.findViewById(R.id.textViewNumRes);
		
		leftButton.setOnClickListener(this);
		rightButton.setOnClickListener(this);
		
		seats = (GridView) v.findViewById(R.id.gridViewSeats);
		
		reservation = (Button) v.findViewById(R.id.buttonReservation);
		reservation.setOnClickListener(this);
		
		seats.setAdapter(new AdapterHallSeats(getActivity(), 25));
		seats.setOnItemClickListener(this);
		
		
		return v;// super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == rightButton.getId() && numSeats < 11 ) {
			numReservation.setText("" + ++numSeats);
		}
		if(v.getId() == leftButton.getId()) {
			if(numSeats > 1 && numSeats > numOfClicks) {
				numReservation.setText("" + --numSeats);
			}
		}
		if(v.getId() == reservation.getId() && numSeats == numOfClicks) {
			makeDialog();
		}
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(null == view.getTag() && numOfClicks < numSeats ) {
			++numOfClicks;
			view.setBackgroundColor(Color.BLACK);
			view.setTag("o");
			
		}
		else {
			if( numOfClicks > 0 && null != view.getTag() && view.getTag().toString().equals("o") )
				--numOfClicks;
				view.setBackgroundResource(getActivity().getResources().getIdentifier("button_seat_free", "drawable", getActivity().getPackageName()));
				view.setTag(null);
		}
	}
	
	
	public void makeDialog() {
		
		//Alert Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder
    	.setTitle("Book Movies Tickets")
    	.setMessage("Are you sure? "+"\n"+" " + numSeats + " seat(s) " + textFromArg )
    	.setIcon(android.R.drawable.ic_dialog_alert)
    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int which) {
    	    	callNotificationReceiver(textFromArg);
    	    }
    	})
    	.setNegativeButton("No", null)
    	.show();
	    
	}
	
	private final static String NOTIFICATION_ACTION = "com.example.cinema.notification_area";
	private final static String NOTIFICATION_KEY_STRING_TITLE = "NotificationDataStringTitle";
	private final static String NOTIFICATION_KEY_STRING_TEXT = "NotificationDataStringText";
	private final static String NOTIFICATION_KEY_INT = "NotificationDataInt";
	
	private void callNotificationReceiver (String nameAndTime) {
		
		Intent notifIntent = new Intent(NOTIFICATION_ACTION);
		notifIntent.setType("TEXT/*");
		notifIntent.putExtra(NOTIFICATION_KEY_STRING_TITLE, nameAndTime);
		notifIntent.putExtra(NOTIFICATION_KEY_STRING_TEXT, " dont forget");
		notifIntent.putExtra(NOTIFICATION_KEY_INT, getActivity().getResources().getIdentifier("movie_ticket", "drawable", getActivity().getPackageName()));
		
		
		AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
		
		PendingIntent myAlarmIntent = PendingIntent.getBroadcast(getActivity(), 0, notifIntent, 0);
		
		alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime()+ 5*1000, myAlarmIntent);
		
		
	}

	
}
