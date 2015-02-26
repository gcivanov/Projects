package com.example.cinema.UI;

import com.example.cinema.R;
import com.example.cinema.c.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountFragment extends android.support.v4.app.Fragment implements OnClickListener{
	
	private EditText etName;
	private EditText etPassword;
	private EditText etRePassword;
	private EditText etEmail;
	private Button bEnter;
	
	public CreateAccountFragment(){
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setRetainInstance(true);
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create_account, container,false);
		

		etName = (EditText) view.findViewById(R.id.editTextName);
		
		etPassword = (EditText) view.findViewById(R.id.editTextPassword);
		
		etRePassword = (EditText) view.findViewById(R.id.editTextRePassword); 
		
		etEmail = (EditText) view.findViewById(R.id.editTextEmail);
		
		bEnter = (Button) view.findViewById(R.id.buttonEnter);
		bEnter.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		
		if( v.getId() == bEnter.getId() ) {
			if( etName.getText().toString() != null && Account.checkPass(etPassword.getText().toString()) &&
					etPassword.getText().toString().equals(etRePassword.getText().toString()) &&
					Account.checkEmail(etEmail.getText().toString())) {
				
				Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
				
			} else {
				Toast.makeText(getActivity(), "Not OK", Toast.LENGTH_SHORT).show();
			}	
		}
	}
	
}
