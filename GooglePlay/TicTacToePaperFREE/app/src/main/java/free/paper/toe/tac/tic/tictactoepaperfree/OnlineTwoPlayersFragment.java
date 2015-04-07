package free.paper.toe.tac.tic.tictactoepaperfree;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

import java.util.ArrayList;


public class OnlineTwoPlayersFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public static OnlineTwoPlayersFragment newInstance(String param1, String param2) {
        OnlineTwoPlayersFragment fragment = new OnlineTwoPlayersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OnlineTwoPlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_two_players, container, false);
    }


//    GoogleApiClient mGoogleApiClient;
//    int RC_SELECT_PLAYERS =12;
//
//    public void onStartMatchClicked(View view) {
////        mGoogleApiClient = new GoogleApiClient.Builder(getActivity());
//
//        Intent intent =
//                Games.TurnBasedMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 7, true);
//        startActivityForResult(intent , RC_SELECT_PLAYERS );
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SELECT_PLAYERS) {
//            if (requestCode != Activity.RESULT_OK) {
//                // user canceled
//                return;
//            }
//
//            // Get the invitee list.
//            final ArrayList<String> invitees =
//                    data.getStringArrayListExtra(Games.EXTRA_PLAYER_IDS);
//
//            // Get auto-match criteria.
//            Bundle autoMatchCriteria = null;
//            int minAutoMatchPlayers = data.getIntExtra(
//                    Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, 0);
//            int maxAutoMatchPlayers = data.getIntExtra(
//                    Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, 0);
//
//            if (minAutoMatchPlayers > 0) {
//                autoMatchCriteria = RoomConfig.createAutoMatchCriteria(
//                        minAutoMatchPlayers, maxAutoMatchPlayers, 0);
//            } else {
//                autoMatchCriteria = null;
//            }
//
//            TurnBasedMatchConfig tbmc = TurnBasedMatchConfig.builder()
//                    .addInvitedPlayers(invitees)
//                    .setAutoMatchCriteria(autoMatchCriteria)
//                    .build();
//
//            // Create and start the match.
//            Games.TurnBasedMultiplayer
//                    .createMatch(mGoogleApiClient, tbmc)
//                    .setResultCallback(new MatchInitiatedCallback());
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

    public interface OnFragmentInteractionListener {
    }

}
