package com.example.androidhw2;

import model.User;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class TransferFragment extends Fragment implements OnClickListener {
	
	
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_USER = "param2";

    private String mTagTrans;
    private User mUser;
    

    private ArrayAdapter<String> fromArrayAdapter ;
    private ArrayAdapter<String> toArrayAdapter ;
    
    private OnTransferFragmentInteractionListener mListener;

    
    private Spinner mFromIBANSpinner;
    private Spinner mToIBANSpinner;
    private EditText mMoneyEditText;
    private EditText mTextEditText;
    private Button mOK;
    
    
    public static TransferFragment newInstance(String typeTransfer, User user) {
    	TransferFragment fragment = new TransferFragment();
        Bundle args = new Bundle();
        
        args.putSerializable(ARG_USER, user);
        
        args.putString(ARG_PARAM1, typeTransfer);

        fragment.setArguments(args);
        return fragment;
    }

    public TransferFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        	mTagTrans = getArguments().getString(ARG_PARAM1);
        	mUser = (User) getArguments().getSerializable(ARG_USER);
        }
        initAdapters();
    }
    
//    @Override
//    public void onStart() {
//        if( fromArrayAdapter != null)
//        	fromArrayAdapter.notifyDataSetChanged();
//        if( toArrayAdapter != null)
//        	toArrayAdapter.notifyDataSetChanged();
//    	super.onStart();
//    }
    
    private void initAdapters(){
    	
    	fromArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
        		mUser.getMyIBANs());
        fromArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	if(mTagTrans.equals(TypeTransfersTags.BETWEEN_OWN_ACCOUNTS)) {
    		toArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
            		mUser.getMyIBANs());
            toArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		} else if( mTagTrans.equals(TypeTransfersTags.TO_OTHER_ACCOUNTS) ) {
			toArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
	        		mUser.getuIBANs());
	        toArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		}
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);
        
        mFromIBANSpinner = (Spinner) view.findViewById(R.id.fromIbanSpinner);
        mToIBANSpinner = (Spinner) view.findViewById(R.id.toIbanSpinner);
        mMoneyEditText = (EditText) view.findViewById(R.id.moneyEditText);
        mTextEditText = (EditText) view.findViewById(R.id.textEditText);
        
        
        mFromIBANSpinner.setAdapter(fromArrayAdapter);
        mToIBANSpinner.setAdapter(toArrayAdapter);
        
        mOK = (Button) view.findViewById(R.id.okButton);
        
        mOK.setOnClickListener(this);
    	

        return view;
    }

    
	@Override
	public void onClick(View v) {
		if(v.getId() == mOK.getId()) {
			
			if(mMoneyEditText.getText() != null && !mMoneyEditText.getText().toString().equals("")) {
			
				mListener.onTransferOkButtonClicked(mFromIBANSpinner.getSelectedItem().toString(), 
						mToIBANSpinner.getSelectedItem().toString(), Double.parseDouble(mMoneyEditText.getText().toString()), 
						mTextEditText.getText().toString());
			}
		}
	}

	

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTransferFragmentInteractionListener ) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTransferFragmentInteractionListener ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnTransferFragmentInteractionListener {
    	public void onTransferOkButtonClicked(String fromIban, String toIban, double money, String text);
    }

    
}
