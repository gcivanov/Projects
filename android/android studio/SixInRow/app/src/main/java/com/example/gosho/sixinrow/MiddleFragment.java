package com.example.gosho.sixinrow;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MiddleFragment.OnMiddleFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MiddleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiddleFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private OnMiddleFragmentInteractionListener mListener;

//    private RadioGroup mRG;
//    private Button mSizeOneButton;
//    private Button mSizeTwoButton;
//    private Button mSizeThreeButton;

    private int num;

    public static MiddleFragment newInstance() {
        MiddleFragment fragment = new MiddleFragment();
        return fragment;
    }

    public MiddleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        num = 0;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_middle, container, false);

//        mSizeOneButton = (Button) view.findViewById(R.id.sizeOneButton);
//        mSizeTwoButton = (Button) view.findViewById(R.id.sizeTwoButton);
//        mSizeThreeButton = (Button) view.findViewById(R.id.sizeThreeButton);
//
//        mSizeOneButton.setOnClickListener(this);
//        mSizeTwoButton.setOnClickListener(this);
//        mSizeThreeButton.setOnClickListener(this);
//
//
//        mRG = (RadioGroup) view.findViewById(R.id.radioGroup);
//        mRG.setOnCheckedChangeListener(this);



        view.findViewById(R.id.sizeOneButton).setOnClickListener(this);
        view.findViewById(R.id.sizeTwoButton).setOnClickListener(this);
        view.findViewById(R.id.sizeThreeButton).setOnClickListener(this);

        ((RadioGroup) view.findViewById(R.id.radioGroup)).setOnCheckedChangeListener(this);

        return view;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMiddleFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMiddleFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId)
        {
            case R.id.radioButton3:
                num = 3;
                break;
            case R.id.radioButton5:
                num = 5;
                break;
            case R.id.radioButton6:
                num = 6;
                break;
        }
    }

    @Override
    public void onClick(View v) {

        if(num > 0) {
            switch (v.getId()) {
                case R.id.sizeOneButton:
                    if(num == 3) {
                        mListener.startTheGame(num, Const.SIZE_ONE);
                    } else {
                        Toast.makeText(getActivity(), " To play 3x3 you need to choose three (3) " +
                                "consecutive points ",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.sizeTwoButton:
                    mListener.startTheGame(num, Const.SIZE_TWO);
                    break;
                case R.id.sizeThreeButton:
                    mListener.startTheGame(num, Const.SIZE_THREE);
                    break;
            }
        } else {
            Toast.makeText(getActivity(), " To play you need to choose consecutive points "
                    ,Toast.LENGTH_SHORT).show();
        }

    }

    public interface OnMiddleFragmentInteractionListener {

        public void startTheGame(int num, int boardNSize);
    }

}
