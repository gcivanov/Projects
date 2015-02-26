package com.example.cinema.UI;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverNotification extends BroadcastReceiver {
	
	private final static String KEY_STRING_TITLE = "NotificationDataStringTitle";
	private final static String KEY_STRING_TEXT = "NotificationDataStringText";
	private final static String KEY_INT = "NotificationDataInt";
	
	
	@Override
	public void onReceive(Context myContext, Intent intent) {
		
		long[] vib = {1255,1255,1255};
		PendingIntent myIntent = PendingIntent.getActivity(myContext, 0, new Intent(myContext,ProjectionsActivity.class),
    			Intent.FLAG_ACTIVITY_NEW_TASK);
		
//		Toast.makeText(myContext, "BroadcastReceivers called ", Toast.LENGTH_LONG).show();
		
		Notification.Builder notificationBuilder = new Notification.Builder(myContext);
		notificationBuilder.setTicker("RESERVATION")
			.setAutoCancel(true)
			.setContentIntent(myIntent)
			.setSmallIcon(android.R.drawable.stat_sys_warning)
			.setContentTitle(intent.getExtras().getString(KEY_STRING_TITLE))
			.setContentText(intent.getExtras().getString(KEY_STRING_TEXT))
			.setVibrate(vib);
		
		int picId = intent.getExtras().getInt(KEY_INT);
		
		if(picId != 0) {
			notificationBuilder.setSmallIcon(picId);
		}
			
		NotificationManager notM = (NotificationManager) myContext.getSystemService(Context.NOTIFICATION_SERVICE);
		notM.notify(1, notificationBuilder.build());
		
	}

}
