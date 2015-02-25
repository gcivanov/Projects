package com.example.androidhw2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class StartFragment extends Fragment implements OnClickListener {
	
    private Button mStartButton;
    private Button mExitButton;
    private EditText mUsername;
    private EditText mPass;
    
    
	private OnStartFragmentInteractionListener mListener;
	
    public StartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        mStartButton = (Button) view.findViewById(R.id.startButton);
        mExitButton = (Button) view.findViewById(R.id.exitButton);
        mUsername = (EditText) view.findViewById(R.id.usernameEditText);
        mPass = (EditText) view.findViewById(R.id.passEditText);

        mStartButton.setOnClickListener(this);
        mExitButton.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        if(mListener != null) {

            if(v.getId() == mStartButton.getId()) {
//            	if(!mPass.getText().toString().equals("") && !mUsername.getText().toString().equals("")) {
            		mListener.onStartButtonClick(mUsername.getText().toString(), mPass.getText().toString());
//            	}
                
            } else if(v.getId() == mExitButton.getId()) {
                mListener.onExitButtonClick();
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStartFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStartFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnStartFragmentInteractionListener {
        public void onStartButtonClick(String name, String pass);
        public void onExitButtonClick();
    }

}
