package free.paper.toe.tac.tic.tictactoepaperfree;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import free.paper.toe.tac.tic.views.ButtonView;


public class TwoPlayersFragment extends Fragment implements View.OnClickListener {




    private final static String SP_TWO_PLAYERS_NAME = "sponeplayername";
    private final static String SP_PLAYER1_NAME_TAG = "spplayeronenametag";
    private final static String SP_PLAYER2_NAME_TAG = "spplayertwoenametag";
    private final static String SP_PLAYER1_PLAY_WITH_TAG = "spplayeroneplaywithtag";
    private final static String SP_PLAY_FIRST_TAG ="spplayfirssttag";

    private MediaPlayer mediaPlayer;

    private OnTwoPlayersFragmentInteractionListener mListener;

    private EditText mPlayer1NameEditText;
    private EditText mPlayer2NameEditText;
    private ImageButton mXPlayer1PlayWithImageButton;
    private ImageButton mOPlayer1PlayWithImageButton;
    private ImageButton mXPlayFirstImageButton;
    private ImageButton mOPlayFirstImageButton;
    private ButtonView mStartButton;

    private char player1PlayWith;
    private char playFirst;

    public TwoPlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.next_page);

        player1PlayWith = getActivity().getSharedPreferences(SP_TWO_PLAYERS_NAME, Context.MODE_PRIVATE)
                .getString(SP_PLAYER1_PLAY_WITH_TAG, ""+Constants.X).charAt(0);
        playFirst = getActivity().getSharedPreferences(SP_TWO_PLAYERS_NAME, Context.MODE_PRIVATE)
                .getString(SP_PLAY_FIRST_TAG,""+Constants.X).charAt(0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two_players, container, false);
        mPlayer1NameEditText = (EditText) v.findViewById(R.id.editTextFirstPlayerName);
        mPlayer2NameEditText = (EditText) v.findViewById(R.id.editTextSecondPlayerName);

        mXPlayer1PlayWithImageButton = (ImageButton) v.findViewById(R.id.imageButtonXFirstPlayWithTwoPlayers);
        mOPlayer1PlayWithImageButton= (ImageButton) v.findViewById(R.id.imageButtonOFirstPlayWithTwoPlayers);
        mXPlayFirstImageButton = (ImageButton) v.findViewById(R.id.imageButtonXPlayFirstTwoPlayers);
        mOPlayFirstImageButton = (ImageButton) v.findViewById(R.id.imageButtonOPlayFirstTwoPlayers);
        mStartButton = (ButtonView) v.findViewById(R.id.buttonStartTwoPlayers);

        init();

        return v;
    }

    private void init(){
        switch(playFirst) {
            case Constants.X: mXPlayFirstImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
                break;
            case Constants.O: mOPlayFirstImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
                break;
        }
        switch(player1PlayWith) {
            case Constants.X: mXPlayer1PlayWithImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
                break;
            case Constants.O: mOPlayer1PlayWithImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
                break;
        }

        mPlayer1NameEditText.setText(getActivity().getSharedPreferences(SP_TWO_PLAYERS_NAME, Context.MODE_PRIVATE)
                .getString(SP_PLAYER1_NAME_TAG,""));
        mPlayer2NameEditText.setText(getActivity().getSharedPreferences(SP_TWO_PLAYERS_NAME, Context.MODE_PRIVATE)
                .getString(SP_PLAYER2_NAME_TAG,""));

        mXPlayer1PlayWithImageButton.setOnClickListener(this);
        mOPlayer1PlayWithImageButton.setOnClickListener(this);
        mXPlayFirstImageButton.setOnClickListener(this);
        mOPlayFirstImageButton.setOnClickListener(this);
        mStartButton.setOnClickListener(this);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTwoPlayersFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTwoPlayersFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mOPlayer1PlayWithImageButton.getId() && player1PlayWith != Constants.O ){
            mediaPlayer.start();
            mOPlayer1PlayWithImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
            mXPlayer1PlayWithImageButton.setBackgroundResource(Constants.X_MENU_ID);
            player1PlayWith = Constants.O;

        } else if(v.getId() == mXPlayer1PlayWithImageButton.getId() && player1PlayWith != Constants.X ){
            mediaPlayer.start();
            mOPlayer1PlayWithImageButton.setBackgroundResource(Constants.O_MENU_ID);
            mXPlayer1PlayWithImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
            player1PlayWith = Constants.X;
        } else if(v.getId() == mOPlayFirstImageButton.getId() && playFirst != Constants.O ){
            mediaPlayer.start();
            mOPlayFirstImageButton.setBackgroundResource(Constants.O_MENU_CLICK_ID);
            mXPlayFirstImageButton.setBackgroundResource(Constants.X_MENU_ID);
            playFirst=Constants.O;

        } else if(v.getId() == mXPlayFirstImageButton.getId() && playFirst != Constants.X ){
            mediaPlayer.start();
            mOPlayFirstImageButton.setBackgroundResource(Constants.O_MENU_ID);
            mXPlayFirstImageButton.setBackgroundResource(Constants.X_MENU_CLICK_ID);
            playFirst=Constants.X;

        } else if(v.getId() == mStartButton.getId()) {
            SharedPreferences.Editor spe = getActivity().getSharedPreferences(SP_TWO_PLAYERS_NAME, Context.MODE_PRIVATE).edit();
            spe.putString(SP_PLAYER1_PLAY_WITH_TAG,""+player1PlayWith);
            spe.putString(SP_PLAY_FIRST_TAG,""+playFirst);
            spe.putString(SP_PLAYER1_NAME_TAG, mPlayer1NameEditText.getText().toString());
            spe.putString(SP_PLAYER2_NAME_TAG, mPlayer2NameEditText.getText().toString());
            spe.commit();
            mListener.onClickStartGame(player1PlayWith, playFirst, Constants.PLAYER_LEVEL,
                    ((mPlayer1NameEditText.getText().length() > 0 )? mPlayer1NameEditText.getText()
                            :"player 1").toString(), ((mPlayer2NameEditText.getText().length() > 0 )?
                            mPlayer2NameEditText.getText() :"player 1").toString());
            }
    }

    public interface OnTwoPlayersFragmentInteractionListener {
        public void onClickStartGame(char playWith, char playFirst, char difficultyLevel,
                                  String player1Name, String player2Name);
    }

}
