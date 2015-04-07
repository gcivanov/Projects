package free.paper.toe.tac.tic.tictactoepaperfree;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import free.paper.toe.tac.tic.typefaces.TypeFaces;


public class OnePlayerFragment extends Fragment implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener{

    private final static String SP_ONE_PLAYER_NAME = "sponeplayername";
    private final static String SP_PLAY_WITH_TAG = "spplaywithtag";
    private final static String SP_PLAY_FIRST_TAG = "spplayfirsttag";
    private final static String SP_DIFF_LEVEL_TAG = "spdiffleveltag";
    private final static String SP_PLAYER_NAME_TAG = "playernametag";

    private MediaPlayer mediaPlayer;

    private TextView mPlayerNameTextView;
    private EditText mPlayerNameEditText;
    private TextView mPlayFirstTextView;
    private ImageButton mXPlayFirstImageButton;
    private ImageButton mOPlayFirstImageButton;
    private TextView mPlayWithTextView;
    private ImageButton mXPlayWithImageButton;
    private ImageButton mOPlayWithImageButton;
    private Button mStartButton;

    private RadioGroup mOnePlayerRadioGroup;
    private RadioButton mEasyRadioButton;
    private RadioButton mMediumRadioButton;
    private RadioButton mHardRadioButton;

    private OnOnePlayerFragmentInteractionListener mListener;

    private char playWith;
    private char playFirst;
    private char diffLevel;

    public static OnePlayerFragment newInstance() {
        OnePlayerFragment fragment = new OnePlayerFragment();

        return fragment;
    }

    public OnePlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.next_page);

        playWith = getActivity().getSharedPreferences(SP_ONE_PLAYER_NAME, Context.MODE_PRIVATE)
                .getString(SP_PLAY_WITH_TAG, ""+Constants.X).charAt(0);
        playFirst = getActivity().getSharedPreferences(SP_ONE_PLAYER_NAME, Context.MODE_PRIVATE)
                .getString(SP_PLAY_FIRST_TAG,""+Constants.X).charAt(0);
        diffLevel = getActivity().getSharedPreferences(SP_ONE_PLAYER_NAME, Context.MODE_PRIVATE)
                .getString(SP_DIFF_LEVEL_TAG,""+Constants.HARD_LEVEL).charAt(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_player, container, false);

        mPlayerNameTextView = (TextView) view.findViewById(R.id.playerNameTextView);
        mPlayerNameEditText = (EditText) view.findViewById(R.id.playerNameEditText);

        mPlayWithTextView = (TextView) view.findViewById(R.id.textViewPlayWithOnePlayer);
        mOPlayWithImageButton = (ImageButton) view.findViewById(R.id.imageButtonOPlayWithOnePlayer);
        mXPlayWithImageButton = (ImageButton) view.findViewById(R.id.imageButtonXPlayWithOnePlayer);

        mPlayFirstTextView = (TextView) view.findViewById(R.id.textViewPlayFirstOnePlayer);
        mOPlayFirstImageButton = (ImageButton) view.findViewById(R.id.imageButtonOPlayFirstOnePlayer);
        mXPlayFirstImageButton = (ImageButton) view.findViewById(R.id.imageButtonXPlayFirstOnePlayer);

        mOnePlayerRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroupOnePlayer);
        mEasyRadioButton = (RadioButton) view.findViewById(R.id.easyLevelRadioButton);
        mMediumRadioButton = (RadioButton) view.findViewById(R.id.mediumLevelRadioButton);
        mHardRadioButton = (RadioButton) view.findViewById(R.id.hardLevelRadioButton);

        mStartButton = (Button) view.findViewById(R.id.buttonStartOnePlayer);

        init();

        return view;
    }

    private void init(){
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change - in MainActivity
//        Typeface typeface = TypeFaces.getTypeFace(getActivity(), "fonts/tempussansitc.ttf");
//        mPlayerNameTextView.setTypeface(typeface, Typeface.NORMAL);
//        mPlayerNameEditText.setTypeface(typeface, Typeface.NORMAL);
//        mPlayWithTextView.setTypeface(typeface, Typeface.NORMAL);
//        mPlayFirstTextView.setTypeface(typeface, Typeface.NORMAL);

        switch(playFirst) {
            case Constants.X: mXPlayFirstImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
                break;
            case Constants.O: mOPlayFirstImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
                break;
        }
//        Toast.makeText(getActivity(), " "+playWith , Toast.LENGTH_SHORT).show();
        switch(playWith) {
            case Constants.X: mXPlayWithImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
                break;
            case Constants.O: mOPlayWithImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
                break;
        }
        switch(diffLevel) {
            case Constants.EASY_LEVEL: mEasyRadioButton.setChecked(true);
                break;
            case Constants.MEDIUM_LEVEL: mMediumRadioButton.setChecked(true);
                break;
            case Constants.HARD_LEVEL: mHardRadioButton.setChecked(true);
                break;
        }
        checkTextColors();

        mPlayerNameEditText.setText(getActivity().getSharedPreferences(SP_ONE_PLAYER_NAME, Context.MODE_PRIVATE)
                .getString(SP_PLAYER_NAME_TAG,""));

        mOPlayWithImageButton.setOnClickListener(this);
        mXPlayWithImageButton.setOnClickListener(this);
        mOPlayFirstImageButton.setOnClickListener(this);
        mXPlayFirstImageButton.setOnClickListener(this);
        mStartButton.setOnClickListener(this);

        mOnePlayerRadioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mOPlayFirstImageButton.getId() && playFirst != Constants.O ){
            mediaPlayer.start();
            mOPlayFirstImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
            mXPlayFirstImageButton.setBackgroundResource(Constants.X_MENU_ID);
            playFirst=Constants.O;

        } else if(v.getId() == mXPlayFirstImageButton.getId() && playFirst != Constants.X ){
            mediaPlayer.start();
            mOPlayFirstImageButton.setBackgroundResource(Constants.O_MENU_ID);
            mXPlayFirstImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
            playFirst=Constants.X;

        } else if(v.getId() == mOPlayWithImageButton.getId() && playWith != Constants.O ){
            mediaPlayer.start();
            mOPlayWithImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
            mXPlayWithImageButton.setBackgroundResource(Constants.X_MENU_ID);
            playWith = Constants.O;

        } else if(v.getId() == mXPlayWithImageButton.getId() && playWith != Constants.X ){
            mediaPlayer.start();
            mOPlayWithImageButton.setBackgroundResource(Constants.O_MENU_ID);
            mXPlayWithImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
            playWith = Constants.X;

        } else if(v.getId() == mStartButton.getId()) {
            SharedPreferences.Editor spe = getActivity().getSharedPreferences(SP_ONE_PLAYER_NAME, Context.MODE_PRIVATE).edit();
            spe.putString(SP_PLAY_WITH_TAG,""+playWith);
            spe.putString(SP_PLAY_FIRST_TAG,""+playFirst);
            spe.putString(SP_DIFF_LEVEL_TAG,""+diffLevel);
            spe.putString(SP_PLAYER_NAME_TAG, mPlayerNameEditText.getText().toString());
            spe.commit();
            mListener.onClickStartGame(playWith, playFirst, diffLevel,
                    ((mPlayerNameEditText.getText().length() > 0 )
                            ? mPlayerNameEditText.getText().toString()
                            :"player 1"), "AI");
        }
    }

    private void checkTextColors(){
        if(diffLevel == Constants.EASY_LEVEL && mEasyRadioButton.getCurrentTextColor() !=
                Color.parseColor(Constants.BLACK_COLOR) ) {
            mEasyRadioButton.setTextColor(Color.parseColor(Constants.BLACK_COLOR));
            mMediumRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));
            mHardRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));

        } else if(diffLevel == Constants.MEDIUM_LEVEL && mMediumRadioButton.getCurrentTextColor() !=
                Color.parseColor(Constants.BLACK_COLOR)  ) {
            mEasyRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));
            mMediumRadioButton.setTextColor(Color.parseColor(Constants.BLACK_COLOR));
            mHardRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));

        } else if(diffLevel == Constants.HARD_LEVEL && mHardRadioButton.getCurrentTextColor() !=
                Color.parseColor(Constants.BLACK_COLOR)  ) {
            mEasyRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));
            mMediumRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));
            mHardRadioButton.setTextColor(Color.parseColor(Constants.BLACK_COLOR));

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == mEasyRadioButton.getId() && diffLevel != Constants.EASY_LEVEL) {
            mediaPlayer.start();
            diffLevel = Constants.EASY_LEVEL;
            checkTextColors();
        } else if(checkedId == mMediumRadioButton.getId() && diffLevel != Constants.MEDIUM_LEVEL) {
            mediaPlayer.start();
            diffLevel = Constants.MEDIUM_LEVEL;
            checkTextColors();
        } else if(checkedId == mHardRadioButton.getId() && diffLevel != Constants.HARD_LEVEL) {
            mediaPlayer.start();
            diffLevel = Constants.HARD_LEVEL;
            checkTextColors();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnOnePlayerFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnOnePlayerFragmentInteractionListener {
        public void onClickStartGame(char playWith, char playFirst, char difficultyLevel,
                                              String player1Name, String player2Name);
    }
}