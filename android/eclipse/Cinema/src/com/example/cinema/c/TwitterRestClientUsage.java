package com.example.cinema.c;

import org.json.*;

import com.loopj.android.http.*;


public class TwitterRestClientUsage {
	 public static void getPublicTimeline() {
		 
        TwitterRestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray timeline) {
                // Pull out the first event on the public timeline
                JSONObject firstEvent;
				try {
					firstEvent = (JSONObject) timeline.get(0);
//					String tweetText = firstEvent.getString("code");
					String tweetText = firstEvent.getString("");
					
					System.out.println(tweetText + "  //  " + firstEvent.toString());
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("s");
				}
                
				
            }
        });
	 }
	 public static void main(String[] args) {
		 getPublicTimeline();
	 }
	 
	 
}
