package com.example.androidhw2;

import java.util.ArrayList;
import java.util.List;

import model.BankTransfer;
import model.ProtectedBankTransfer;
import model.PrivateBankTransfer;
import model.User;

import com.example.androidhw2.FinalTransferConfirmationFragment.OnFinalTransferConfirmationFragmentInteractionListener;
import com.example.androidhw2.StartFragment.OnStartFragmentInteractionListener;
import com.example.androidhw2.TransferFragment.OnTransferFragmentInteractionListener;
import com.example.androidhw2.TypeTransferFragment.OnTypeTransferFragmentInteractionListener;
import com.example.androidhw2.UserFragment.OnUserFragmentInteractionListener;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnStartFragmentInteractionListener,
		OnTypeTransferFragmentInteractionListener, OnTransferFragmentInteractionListener, OnFinalTransferConfirmationFragmentInteractionListener,
		OnUserFragmentInteractionListener{
	
	private final static String START_TAG = "starttag";
	private final static String TYPE_TAG = "typetag";
	private final static String TRANSFER_TAG = "transfertag";
	private final static String FINAL_CON_TAG = "finalcontag";
	private final static String USER_TAG = "usertag";
	
	private ActionBar mAB;
	 
    private FragmentTransaction transaction;
    private FragmentManager manager;
    
    private User mUser;
    private BankTransfer mBankTransfer;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getFragmentManager();
		
		mAB = getSupportActionBar();
		mAB.setDisplayHomeAsUpEnabled(false);
		mAB.setDisplayShowTitleEnabled(false);
		mAB.hide();
		
		if (findViewById(R.id.fragmentbuffer) != null) {
			
			replaceFragment(R.id.fragmentbuffer, new StartFragment(), START_TAG, false);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.profile) {
			if(manager.findFragmentByTag(USER_TAG) == null) {
				replaceFragment(R.id.fragmentbuffer, UserFragment.newInstance(mUser), USER_TAG, true);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    private void replaceFragment(int id, Fragment fragment, String tag, boolean addToBackStack ) {
        transaction = manager.beginTransaction();
        transaction.replace(id, fragment, tag);
        
        if(addToBackStack == true) {
//        	Toast.makeText(this, " "+tag, Toast.LENGTH_SHORT).show();
        	transaction.addToBackStack(tag);
        }
        
        transaction.commit();
    }
    
    private void popAllFromFragmentBackStack(){
    	for(int i = 0; i < manager.getBackStackEntryCount(); ++i) {    
    	    manager.popBackStack();
    	}
    }
    

	@Override
	public void onStartButtonClick(String username, String password) {
		//check username and password .....
		if(username != null && !username.equals("") ) {
			mUser = new User(username); //...
		} else {
			mUser = new User("Gosho");
		}
		
		List<String> mIbns = new ArrayList<String>();
		mIbns.add("BG80 BNBG 9661 1020 3456 78");
		mIbns.add("BG80 BNBG 9677 1920 3113 80");
		mIbns.add("BG80 BNBG 0090 1217 3322 92");
		mIbns.add("BG81 BMBG 9221 1222 3456 22");

		List<String> uIbns = new ArrayList<String>();
		uIbns.add("BG90 BNBG 9661 1020 3456 78");
		uIbns.add("BG90 BNBG 9677 1920 3113 80");
		uIbns.add("BG90 BNBG 0090 1217 3322 92");
		uIbns.add("BG91 BMBG 9221 1222 3456 22");


		mUser.setMyIBANs(mIbns);
		mUser.setuIBANs(uIbns);
		
		
		
		mAB.show();
		replaceFragment(R.id.fragmentbuffer, new TypeTransferFragment(), TYPE_TAG, true);
	}

	@Override
	public void onExitButtonClick() {
		this.finish();
		
	}
	

	@Override
	public void onTypeTransactionsClick(String typeTag) {
		TransferFragment tf = null;
		
		if(typeTag.equals(TypeTransfersTags.BETWEEN_OWN_ACCOUNTS)) {
			mBankTransfer = new PrivateBankTransfer();
		} else if( typeTag.equals(TypeTransfersTags.TO_OTHER_ACCOUNTS) ) {
			mBankTransfer = new ProtectedBankTransfer();
		}
		
		if(mUser != null) {
			tf = TransferFragment.newInstance(typeTag, mUser);	
		}
		replaceFragment(R.id.fragmentbuffer, tf, TRANSFER_TAG, true);
		
	}

	@Override
	public void onTransferOkButtonClicked(String fromIban, String toIban,
			double money, String text) {
		mBankTransfer.setFromIBAN(fromIban);
		mBankTransfer.setToIBAN(toIban);
		mBankTransfer.setMoney(money);
		mBankTransfer.setText(text);
		//TODO
		mBankTransfer.initTransfer();
		
		
		new AuthorizationDialogFragment() {
			@Override
			public void onConfirmClick(String input) {
				if( input != null ) {
					try{
						
						if(mBankTransfer.makeAuthorization(Integer.parseInt(input))){
							
							replaceFragment(R.id.fragmentbuffer, FinalTransferConfirmationFragment.newInstance(mBankTransfer.toString()),
									FINAL_CON_TAG, true);
							
						} else {
							Toast.makeText(getActivity(), " Incorrect password ", Toast.LENGTH_SHORT).show();
						}
						
					} catch (NumberFormatException e) {
						e.printStackTrace();
						Toast.makeText(getActivity(), " Incorrect password " , Toast.LENGTH_SHORT).show();
					} 
				}
				
			}
		}.show(manager, "mydialogtag");;
		
	}
	

	@Override
	public void onCansleConfirmButtonClick() {
		popAllFromFragmentBackStack();
		replaceFragment(R.id.fragmentbuffer, new TypeTransferFragment(), TYPE_TAG, true);
	}

	@Override
	public void onConfirmButtonClick() {
		
		new AlertDialog.Builder(this)
	    .setTitle("confirm transfer")
	    .setMessage("Are you sure you want to confirm transfer?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	//TODO
	        	mBankTransfer.execTransfer();
	        	
	        	popAllFromFragmentBackStack();
	        	replaceFragment(R.id.fragmentbuffer, new TypeTransferFragment(), TYPE_TAG, true);
	        	
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	    .show();
	}


	
	@Override
	public void onBackPressed() {
	    if (manager.getBackStackEntryCount() > 1){
	    	manager.popBackStackImmediate();
	    } else {
	    	this.finish();
	    }
	}

	@Override
	public void onAddNewUIbanClick(String iban) {
		mUser.addUIBAN(iban);
	}

}
