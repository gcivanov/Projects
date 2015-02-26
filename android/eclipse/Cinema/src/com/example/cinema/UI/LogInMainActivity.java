package com.example.cinema.UI;

import com.example.cinema.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LogInMainActivity extends FragmentActivity implements OnClickListener{
	
	final static String FRAGMENT_TAG = "FRAGMENT_TAG";
	
	private TextView TVName;
	private TextView TVPass;
	private EditText ETName;
	private EditText ETPass;
	private Button BLogIn;
	private Button BCreateAcc;
	private ImageView imageView;
	
	android.support.v4.app.FragmentTransaction fragmentTransaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_log_in_main);

		
		ETName = (EditText) findViewById(R.id.editTextName);
		ETPass = (EditText) findViewById(R.id.editTextPassword);
		BLogIn = (Button) findViewById(R.id.buttonLogIn);
		BLogIn.setOnClickListener(this);
		BCreateAcc = (Button) findViewById(R.id.buttonCreateAccount);
		BCreateAcc.setOnClickListener(this);
		TVName = (TextView) findViewById(R.id.textViewName);
		TVPass = (TextView) findViewById(R.id.textViewPasswordStartActivity);
		
		imageView = (ImageView) findViewById(R.id.imageView);
		
		
	}
	
	@Override
	public void onClick(View v) {
		if( v.getId() == BLogIn.getId() ) {
//			if(Account.checkPass(ETPass.getText().toString()) && ETName != null) {
				startActivity(new Intent(this, ProjectionsActivity.class));
				finish();
//			}
		}
		if( v.getId() == BCreateAcc.getId()) {
			if(getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) == null ) { 
				addFragment();
			} else {
				fragmentTransaction.show(getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG));
			}
		}
	}
	
	
	private void addFragment() {
		fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.frameLayoutForFragment, 
				new CreateAccountFragment(),FRAGMENT_TAG);
		fragmentTransaction.addToBackStack(null);
		setAllVisibilityFalse();
		fragmentTransaction.commit();
		
	}
	
	@Override
	public void onBackPressed() {
		if(getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) != null)
			fragmentTransaction.hide(getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG));
		
		setAllVisibilityTrue();
		super.onBackPressed();		
	}
	
	
	private void setAllVisibilityTrue() {
		ETName.setVisibility(View.VISIBLE);
		ETPass.setVisibility(View.VISIBLE);
		BLogIn.setVisibility(View.VISIBLE);
		BCreateAcc.setVisibility(View.VISIBLE);
		TVName.setVisibility(View.VISIBLE);
		TVPass.setVisibility(View.VISIBLE);
		imageView.setVisibility(View.VISIBLE);
	}
	private void setAllVisibilityFalse() {
		ETName.setVisibility(View.INVISIBLE);
		ETPass.setVisibility(View.INVISIBLE);
		BLogIn.setVisibility(View.INVISIBLE);
		BCreateAcc.setVisibility(View.INVISIBLE);
		TVName.setVisibility(View.INVISIBLE);
		TVPass.setVisibility(View.INVISIBLE);
		imageView.setVisibility(View.INVISIBLE);
	}
	
	
}