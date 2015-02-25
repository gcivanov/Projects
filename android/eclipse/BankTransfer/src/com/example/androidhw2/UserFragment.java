package com.example.androidhw2;

import model.User;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class UserFragment extends Fragment implements OnClickListener{
	
	private final static String TAG_USER = "usertag";
	

	private OnUserFragmentInteractionListener mListener;
	
	
	private User mUser;
	
	private ArrayAdapter<String> myArrayAdapter ;
    private ArrayAdapter<String> uArrayAdapter ;
    
	
	
    private TextView mUsernameTextView;
    private ListView mMyIbansListView;
    private ListView mUIbansListView;
    private Button mAddIbanButton;
    private EditText mAddUIbanEditText;
	
	
	public UserFragment(){}
	
	public static UserFragment newInstance(User user ){
		UserFragment uf = new UserFragment();
		
		Bundle bundle = new Bundle();
		
		bundle.putSerializable(TAG_USER, user);
		
		uf.setArguments(bundle);
		
		return uf;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getArguments() != null) {
			mUser = (User) getArguments().getSerializable(TAG_USER);
		}
		if(myArrayAdapter == null)
			initAdapters();
	}

	private void initAdapters(){
    	
    	myArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
        		mUser.getMyIBANs());
        myArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
		uArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
	    		mUser.getuIBANs());
	    uArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user, container, false);
		
		mUsernameTextView = (TextView) view.findViewById(R.id.usernameTextView);
		mMyIbansListView = (ListView) view.findViewById(R.id.myIbansListView);
		mUIbansListView = (ListView) view.findViewById(R.id.uIbansListView);
		mAddUIbanEditText = (EditText) view.findViewById(R.id.addNewIbanEditText);
		
		mAddIbanButton = (Button) view.findViewById(R.id.addUIbansButton);
		
		
		mUsernameTextView.setText("USER: "+mUser.getUsername());
		mMyIbansListView.setAdapter(myArrayAdapter);
		mUIbansListView.setAdapter(uArrayAdapter);
		mAddIbanButton.setOnClickListener(this);
		
		
		return view;
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == mAddIbanButton.getId()) {
			
			if(mAddUIbanEditText != null && mAddUIbanEditText.getText().toString().length() > 0) {
				//check IBAN - real iban or fake iban ..
				
				String iban = mAddUIbanEditText.getText().toString();
				
				mListener.onAddNewUIbanClick(iban);
				
				uArrayAdapter.notifyDataSetChanged();
			}
			
		}
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		try{
			mListener = (OnUserFragmentInteractionListener) getActivity();
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
                    + " must implement OnUserFragmentInteractionListener");
		}
		super.onAttach(activity);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}
	
	
	public interface OnUserFragmentInteractionListener {
		public void onAddNewUIbanClick(String iban);
	}


	
	
	
}
