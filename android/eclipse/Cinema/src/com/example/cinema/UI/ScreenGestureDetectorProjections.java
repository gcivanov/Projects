package com.example.cinema.UI;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public abstract class ScreenGestureDetectorProjections extends SimpleOnGestureListener {
	
	private final static int MIN_SPEED = 80;
	private final static int MIN = 80;

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}
	
	abstract public void onRight();
	abstract public void onLeft();
	abstract public void onBottom();
	abstract public void onTop();
	
	
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		
		boolean result = false;
		
		float diffX = e2.getX() - e1.getX();
		float diffY = e2.getY() - e1.getY();
		if ( Math.abs(diffX) > Math.abs(diffY) ) {
			
			if( Math.abs(diffX) > MIN && Math.abs(velocityX) > MIN_SPEED ) {
				result = true;
				if ( diffX > 0 ) {
					onRight();
				} else {
					onLeft();
				}
			}
		} else {
			if( Math.abs(diffY) > MIN && Math.abs(velocityY) > MIN_SPEED ) {
				if( diffY > 0 ) {
					onBottom();
				} else {
					onTop();
				}
			}
		}
		
		return result;
	}

}
