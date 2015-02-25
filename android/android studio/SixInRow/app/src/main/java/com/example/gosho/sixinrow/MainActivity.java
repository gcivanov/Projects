package com.example.gosho.sixinrow;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

import com.example.gosho.views.LineView;


public class MainActivity extends FragmentActivity implements StartFragment.OnStartFragmentInteractionListener,
                MiddleFragment.OnMiddleFragmentInteractionListener, GameFragment.OnGameFragmentInteractionListener {


    final static String START_TAG = "START";
    final static String MIDDLE_TAG = "MIDDLE";
    final static String GAME_TAG = "GAME";

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();


        if (findViewById(R.id.fragmentbuffer) != null) {

            container = (FrameLayout) findViewById(R.id.fragmentbuffer);

            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                container.setBackgroundDrawable(getResources().getDrawable(R.drawable.gangsta_pikachu));
            } else {
                container.setBackground(getResources().getDrawable(R.drawable.gangsta_pikachu));
            }

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            replaceFragment(R.id.fragmentbuffer, new StartFragment(), START_TAG);

        }



//        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new String[]{"","",""} );

    }

    private void replaceFragment(int id, Fragment fragment, String tag ) {
        transaction = manager.beginTransaction();
        transaction.replace(id, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }


    @Override
    public void onStartButtonClick() {
        replaceFragment(R.id.fragmentbuffer, new MiddleFragment(), MIDDLE_TAG );
    }

    @Override
    public void onExitButtonClick() {
        this.finish();
    }

    @Override
    public void startTheGame(int num, int boardNSize) {
        replaceFragment(R.id.fragmentbuffer, GameFragment.newInstance(num, boardNSize, 0, 0), GAME_TAG);

    }

    @Override
    public void gameOver(final int winner,final int num,final int boardSze,final int playerWinns,final int compWins) {


        new PlayNewGameFragment(){

            @Override
            public String textTextView() {
                switch (winner) {
                    case 0: return "No winner \n Play new game???";
                    case -1: return "AI win \n Play new game???";
                    case 1: return "You win !!! \n Play new game???";
                }
                return null;
            }

            @Override
            public void onYesButtonClick() {
                manager.popBackStack();

                replaceFragment(R.id.fragmentbuffer, GameFragment.newInstance(num, boardSze, playerWinns, compWins), GAME_TAG);
            }

            @Override
            public void onNoButtonClick() {
                manager.popBackStack();
            }
        }.show(manager, "DIALOG_FRAGMENT");
    }

    @Override
    public void onBackPressed() {
//        MediaPlayer mediaPlayer = MediaPlayer.create(this , R.raw.next_page);
//        mediaPlayer.start();

        if(manager.findFragmentByTag(START_TAG) != null && manager.findFragmentByTag(START_TAG).isVisible()) {
            this.finish();
            return;
        }
        super.onBackPressed();
    }
}
