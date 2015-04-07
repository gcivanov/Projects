package free.paper.toe.tac.tic.tictactoepaperfree;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import free.paper.toe.tac.tic.typefaces.TypeFaces;


public class OptionsFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_BACKGRAUIND = "param1";
    private static final String ARG_AUDIO = "param2";

    private int themeBackgroundId;
    private boolean hasAudio;


    private OnOptionsFragmentInteractionListener mListener;

    //UI elements
    private CheckBox mSoundCheckBox;
    private TextView mThemeTextView;
    private RadioButton mDarkThemeRadioButton;
    private RadioButton mLightThemeRadioButton;


    private MediaPlayer mediaPlayer;

    public static OptionsFragment newInstance(int themeBackgroundId, boolean hasAudio) {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_BACKGRAUIND, themeBackgroundId);
        args.putBoolean(ARG_AUDIO, hasAudio);
        fragment.setArguments(args);

        return fragment;


    }

    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.next_page);
        if (getArguments() != null) {
            this.themeBackgroundId = getArguments().getInt(ARG_BACKGRAUIND);
            this.hasAudio = getArguments().getBoolean(ARG_AUDIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_options, container, false);

        mSoundCheckBox = (CheckBox) root.findViewById(R.id.soundCheckBox);
        mThemeTextView = (TextView) root.findViewById(R.id.themeTextView);
        mDarkThemeRadioButton = (RadioButton) root.findViewById(R.id.darkThemeRadioButton);
        mLightThemeRadioButton = (RadioButton) root.findViewById(R.id.lightThemeRadioButton);

        setTextFotsInit();

        return root;

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mSoundCheckBox.getId()) {
            mediaPlayer.start();
            mListener.setSound(mSoundCheckBox.isChecked());
        }else if(v.getId() == mDarkThemeRadioButton.getId()) {
            if(mDarkThemeRadioButton.isChecked()) {
                mListener.onClickThemeBackground(Constants.DARK_INT);
                mediaPlayer.start();
            }
        }else if(v.getId() == mLightThemeRadioButton.getId()) {
            if(mLightThemeRadioButton.isChecked()) {
                mListener.onClickThemeBackground(Constants.LIGHT_INT);
                mediaPlayer.start();
            }
        }
        checkColors();
    }


    private void setTextFotsInit(){
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change - in MainActivity
//        Typeface typeface = TypeFaces.getTypeFace(getActivity(), "fonts/tempussansitc.ttf");
//        mSoundCheckBox.setTypeface(typeface, Typeface.NORMAL);
//        mThemeTextView.setTypeface(typeface, Typeface.NORMAL);
//        mDarkThemeRadioButton.setTypeface(typeface, Typeface.NORMAL);
//        mLightThemeRadioButton.setTypeface(typeface, Typeface.NORMAL);

        mSoundCheckBox.setChecked(hasAudio);


        if(themeBackgroundId == Constants.DARK_INT) {
            mDarkThemeRadioButton.setChecked(true);
        } else {
            mLightThemeRadioButton.setChecked(true);
        }
        checkColors();

        mSoundCheckBox.setOnClickListener(this);
        mDarkThemeRadioButton.setOnClickListener(this);
        mLightThemeRadioButton.setOnClickListener(this);


    }
    private void checkColors(){
        if(mSoundCheckBox.isChecked() && mSoundCheckBox.getCurrentTextColor()
                != Color.parseColor(Constants.BLACK_COLOR)){
            mSoundCheckBox.setTextColor(Color.parseColor(Constants.BLACK_COLOR));
        }else if ( !mSoundCheckBox.isChecked() && mSoundCheckBox.getCurrentTextColor()
                != Color.parseColor(Constants.LIVER_COLOR)){
            mSoundCheckBox.setTextColor(Color.parseColor(Constants.LIVER_COLOR));
        }

        if(mDarkThemeRadioButton.isChecked() &&( mDarkThemeRadioButton.getCurrentTextColor()
                != Color.parseColor(Constants.BLACK_COLOR) || mLightThemeRadioButton.getCurrentTextColor()
                != Color.parseColor(Constants.LIVER_COLOR) ) ) {

            mLightThemeRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));
            mDarkThemeRadioButton.setTextColor(Color.parseColor(Constants.BLACK_COLOR));

        } else if(mLightThemeRadioButton.isChecked() &&( mDarkThemeRadioButton.getCurrentTextColor()
                != Color.parseColor(Constants.LIVER_COLOR) || mLightThemeRadioButton.getCurrentTextColor()
                != Color.parseColor(Constants.BLACK_COLOR) ) ) {

            mDarkThemeRadioButton.setTextColor(Color.parseColor(Constants.LIVER_COLOR));
            mLightThemeRadioButton.setTextColor(Color.parseColor(Constants.BLACK_COLOR));
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnOptionsFragmentInteractionListener) activity;
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

    public interface OnOptionsFragmentInteractionListener {
        public void setSound(boolean hasSound);
        public void onClickThemeBackground(int id);
    }

}
