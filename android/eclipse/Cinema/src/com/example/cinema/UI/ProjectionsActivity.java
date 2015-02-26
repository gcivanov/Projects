package com.example.cinema.UI;

import com.example.cinema.R;

import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;

public class ProjectionsActivity extends FragmentActivity {//ActionBarActivity{ //implements View.OnTouchListener {
	
	FragmentTransaction fragmentTransaction;

	private final static String TAG_FOR_FRAMENT1 = "MY_TAG_FOR_FRAGMENT1";
	private final static String TAG_FOR_FRAMENT2 = "MY_TAG_FOR_FRAGMENT2";
	private GestureDetector gestureDetector;
	
//	private ViewPager viewPager;
	
	private ActionBar actBar;
	private Tab allMoviesTab;
	private Tab myMoviesTab;
	private AllProjectionFragment allProjFragment;
	private MyProjectionFragment myProjFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_projections);
		
		actBar = getActionBar();
		
		
		actBar.setDisplayShowTitleEnabled(false);
	    actBar.setDisplayShowHomeEnabled(false);
		
	    allProjFragment = new AllProjectionFragment();
	    myProjFragment = new MyProjectionFragment();
	    
	    actBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
	    allMoviesTab = actBar.newTab().setText("All Movies").
	    		setTabListener(new ActionBarListener(allProjFragment,TAG_FOR_FRAMENT1));
		myMoviesTab = actBar.newTab().setText("My Movies").
				setTabListener(new ActionBarListener(myProjFragment , TAG_FOR_FRAMENT2));
		
		actBar.addTab(allMoviesTab);
				
		actBar.addTab(myMoviesTab);
	    
		setGestureDetector();
	    
	    setOnGestureDetectorForFragments( gestureDetector);
		
	}
	
	public void setOnGestureDetectorForFragments(GestureDetector gestureDetector) {
		if(gestureDetector != null) {
		    allProjFragment.setGestureDetector(gestureDetector);
		    myProjFragment.setGestureDetector(gestureDetector);
		}
	}
	
	public void setNullToGestureDetector (){
		gestureDetector = null;
	}
	public void setGestureDetector() {
		gestureDetector = new GestureDetector(this, new ScreenGestureDetectorProjections() {

			@Override
			public void onRight() {
				actBar.selectTab(allMoviesTab);
			}

			@Override
			public void onLeft() {
				actBar.selectTab(myMoviesTab);
			}

			@Override
			public void onBottom() {
			}

			@Override
			public void onTop() {	
			}
		});
	}
	
	private boolean firstBackButtonClick = false;
	private String HALL_FRAGMNET_TAG = "HALLFRAGMENT";
	
	@Override
	public void onBackPressed() {
		
		if(firstBackButtonClick) {
			super.onBackPressed();
		} else if ( getFragmentManager().findFragmentByTag(HALL_FRAGMNET_TAG) != null) {
			
			setGestureDetector();
			
			getActionBar().show();
			FragmentTransaction fragmentTrans = getFragmentManager().beginTransaction();
			fragmentTrans.replace(R.id.fragmentForProjections, allProjFragment,TAG_FOR_FRAMENT1).commit();
			
		} else {
		
			firstBackButtonClick = true;
			
			final Toast toast = Toast.makeText(this, "Please press Back Button for EXIT", Toast.LENGTH_SHORT);
			toast.show();
			
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					toast.cancel();
					firstBackButtonClick = false;
				}
			}, 1500);
		}
	}
	
	class ActionBarListener implements ActionBar.TabListener {
		
		private android.app.Fragment myFragment;
		private String MY_TAG ;
		private FragmentManager fragmentManager = getFragmentManager();
		
		ActionBarListener() {}
		
		public ActionBarListener(android.app.Fragment fragment, String tag) {
			if( fragment != null ) {
				this.myFragment = fragment;
				MY_TAG = tag;
			}
		}
		
		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
//			viewPager.setCurrentItem(tab.getPosition());
			
			
			if(myFragment != null && fragmentManager.findFragmentByTag(MY_TAG) == null ) {
				ft.replace(R.id.fragmentForProjections, myFragment, MY_TAG);
			} else if ( fragmentManager.findFragmentByTag(MY_TAG).isHidden() ) {
				ft.show(fragmentManager.findFragmentByTag(MY_TAG));
			}
		}	

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			if(fragmentManager.findFragmentByTag(MY_TAG) != null)
				ft.hide(fragmentManager.findFragmentByTag(MY_TAG));
			
		}
		
		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			
		}
	}
	
	//for gestDetect 
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(gestureDetector != null)
			return gestureDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
//	
//	class ViewPagerAdapter extends FragmentPagerAdapter {
//		private android.support.v4.app.FragmentManager fragmentManager;
//
//		public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
//			super(fm);
//			fragmentManager = fm;
//		}
//
//		@Override
//		public Fragment getItem(int arg0) {
//			switch(arg0) {
//				case 0:
//					actBar.selectTab(allMoviesTab);
//					return fragmentManager.findFragmentByTag(TAG_FOR_FRAMENT1);
//				case 1:
//					actBar.selectTab(myMoviesTab);
//					return fragmentManager.findFragmentByTag(TAG_FOR_FRAMENT2);
//			}
//			
//			return null;
//		}
//
//		@Override
//		public int getCount() {
//			return 2;
//		}
//	}
	
	}