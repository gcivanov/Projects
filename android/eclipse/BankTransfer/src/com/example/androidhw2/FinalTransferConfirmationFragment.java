package com.example.androidhw2;

import model.BankTransfer;
import model.User;

import com.example.androidhw2.TransferFragment.OnTransferFragmentInteractionListener;

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
import android.widget.Spinner;
import android.widget.TextView;

public class FinalTransferConfirmationFragment extends Fragment implements OnClickListener{
	
	private final static String ARG = "arfsfask";
	
	private OnFinalTransferConfirmationFragmentInteractionListener mListener;
	
	private BankTransfer mBankTransfer;
	
	private String message;
	
	private Button mCancelButton;
	private Button mConfirmButton;
	private TextView mTextView;
	
	
    public static FinalTransferConfirmationFragment newInstance(String message) {
    	FinalTransferConfirmationFragment fragment = new FinalTransferConfirmationFragment();
        Bundle args = new Bundle();
        
        args.putString(ARG, message);
        

        fragment.setArguments(args);
        return fragment;
    }

    public FinalTransferConfirmationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        	message = getArguments().getString(ARG);
        }
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finaltransferconfirmation, container, false);
        mTextView = (TextView) view.findViewById(R.id.confTextView);
        mConfirmButton = (Button) view.findViewById(R.id.confirmTransButton);
        mCancelButton = (Button) view.findViewById(R.id.cancelTransButton);
        
        mTextView.setText(message);
    	
        mCancelButton.setOnClickListener(this);
        mConfirmButton.setOnClickListener(this);

        return view;
    }
    
    
	@Override
	public void onClick(View v) {
		if(v.getId() == mCancelButton.getId()) {
			mListener.onCansleConfirmButtonClick();
		} else if ( v.getId() == mConfirmButton.getId() ) {
			mListener.onConfirmButtonClick();
		}
	}



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFinalTransferConfirmationFragmentInteractionListener ) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFinalTransferConfirmationFragmentInteractionListener ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFinalTransferConfirmationFragmentInteractionListener {
    	public void onCansleConfirmButtonClick();
    	public void onConfirmButtonClick();
    }



	
}
