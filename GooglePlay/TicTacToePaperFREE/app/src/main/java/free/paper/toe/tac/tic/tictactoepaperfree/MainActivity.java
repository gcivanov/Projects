package free.paper.toe.tac.tic.tictactoepaperfree;


import android.media.AudioManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import free.paper.toe.tac.tic.dialogs.ComingSoonFragment;


public class MainActivity extends FragmentActivity implements StartFragment.OnStartFragmentInteractionListener,
        OptionsFragment.OnOptionsFragmentInteractionListener, OnePlayerFragment.OnOnePlayerFragmentInteractionListener,
        StartGameFragment.OnStartGameFragmentInteractionListener, TwoPlayersFragment.OnTwoPlayersFragmentInteractionListener,
        MiddleTwoPlayersFragment.OnMiddleTwoPlayersFragmentInteractionListener{

    private FrameLayout container;

    private final static String OPTIONS_TAG = "optionsTAg";
    private final static String ONE_PLAYER_MENU_TAG = "onePlayerMenuTag";
    private final static String TWO_PLAYERS_MENU_TAG = "twoMiddlePlayerMenuTag";
    private final static String TWO_PLAYERS_ONE_DEVICE_TAG = "oneDeviceTwoPlayerMenuTag";
    private final static String TWO_PLAYERS_ONLINE_TAG = "onlineTnePlayerMenuTag";
    private final static String START_GAME_TAG = "StartGameTAg";

    private final static String SP_OPTION_NAME = "spnamee";
    private final static String SP_THEME_TAG = "backgraund";
    private final static String SP_AUDIO_TAG = "audioonof";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

//        //check if it`s phone for landscape orientation
//        if(getResources().getBoolean(R.bool.portrait_only)) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Toast.makeText(this, ""+getResources().getDisplayMetrics().density, Toast.LENGTH_LONG).show();
        //!!!!!!!!!!!!

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        container = (FrameLayout) this.findViewById(R.id.container);

        if (savedInstanceState == null) {
            replaceFragment(R.id.container, new StartFragment(), null);
        }

        //set background image:
        container.setBackgroundResource(this.getSharedPreferences(SP_OPTION_NAME, MODE_PRIVATE).getInt(SP_THEME_TAG, Constants.LIGHT_INT));
    }

    @Override
    protected void onStart() {
        ((AudioManager)getSystemService(AUDIO_SERVICE)).setStreamMute(AudioManager.STREAM_SYSTEM,true);

        if(this.getSharedPreferences(SP_OPTION_NAME, MODE_PRIVATE).getBoolean(SP_AUDIO_TAG,true) == false) {
            ((AudioManager)getSystemService(AUDIO_SERVICE)).setStreamMute(AudioManager.STREAM_MUSIC,true);
        }
        super.onStart();
    }


    @Override
    public void onClickOptionsButton() {
        replaceFragment(R.id.container,OptionsFragment.newInstance(this.getSharedPreferences(SP_OPTION_NAME, MODE_PRIVATE).getInt(SP_THEME_TAG, Constants.LIGHT_INT),
                this.getSharedPreferences(SP_OPTION_NAME, MODE_PRIVATE).getBoolean(SP_AUDIO_TAG,true) ), OPTIONS_TAG);
    }

    @Override
    public void onClickOnePlayerButton() {
        replaceFragment(R.id.container, new OnePlayerFragment(), ONE_PLAYER_MENU_TAG);
    }


    @Override
    public void onClickTwoPlayersButton() {
        replaceFragment(R.id.container, new MiddleTwoPlayersFragment(),TWO_PLAYERS_MENU_TAG );
    }

    private void replaceFragment(int container, Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(container, fragment);
        if(tag != null) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        ((AudioManager)getSystemService(AUDIO_SERVICE)).setStreamMute(AudioManager.STREAM_SYSTEM,false);
        ((AudioManager)getSystemService(AUDIO_SERVICE)).setStreamMute(AudioManager.STREAM_MUSIC,false);
        super.onStop();
    }

    @Override
    public void setSound(boolean hasSound) {
        ((AudioManager)getSystemService(AUDIO_SERVICE)).setStreamMute(AudioManager.STREAM_MUSIC,!hasSound);
        this.getSharedPreferences(SP_OPTION_NAME, MODE_PRIVATE).edit().putBoolean(SP_AUDIO_TAG, hasSound).commit();
    }

    @Override
    public void onClickThemeBackground(int id) {
        container.setBackgroundResource(id);
        this.getSharedPreferences(SP_OPTION_NAME, MODE_PRIVATE).edit().putInt(SP_THEME_TAG, id).commit();
    }

    @Override
    public void onClickStartGame(char playWith, char playFirst,
                                          char difficultyLevel,String player1Name, String player2Name) {

        replaceFragment(R.id.container, StartGameFragment.newInstance(playWith, playFirst, difficultyLevel,
                player1Name, player2Name),START_GAME_TAG);

    }

    @Override
    public void popBackStackFromFragmentManager() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onClickOneDeviceTwoPlayersButton() {
//        Toast.makeText(this, "N", Toast.LENGTH_SHORT).show();
        replaceFragment(R.id.container, new TwoPlayersFragment(), TWO_PLAYERS_ONE_DEVICE_TAG);
    }

    @Override
    public void onClickOnlineTwoPlayersButton() {

        new ComingSoonFragment().show(getSupportFragmentManager(), null);
    }

}