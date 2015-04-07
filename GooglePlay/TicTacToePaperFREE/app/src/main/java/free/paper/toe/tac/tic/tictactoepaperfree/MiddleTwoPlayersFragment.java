package free.paper.toe.tac.tic.tictactoepaperfree;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import free.paper.toe.tac.tic.views.ButtonView;


public class MiddleTwoPlayersFragment extends Fragment implements View.OnClickListener {

    private OnMiddleTwoPlayersFragmentInteractionListener mListener;

    private ButtonView mOneDeviceButton;
    private ButtonView mOnlineButton;

    public MiddleTwoPlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_middle_two_players, container, false);
        mOneDeviceButton = (ButtonView) v.findViewById(R.id.buttonOneDeviceTwoPlayers);
        mOnlineButton = (ButtonView) v.findViewById(R.id.buttonOnlineTwoPlayers);
        mOneDeviceButton.setOnClickListener(this);
        mOnlineButton.setOnClickListener(this);

        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMiddleTwoPlayersFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMiddleTwoPlayersFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mOneDeviceButton.getId()) {
            mListener.onClickOneDeviceTwoPlayersButton();
        } else if(v.getId() == mOnlineButton.getId() ) {
            mListener.onClickOnlineTwoPlayersButton();
        }
    }


    public interface OnMiddleTwoPlayersFragmentInteractionListener {
        public void onClickOneDeviceTwoPlayersButton();
        public void onClickOnlineTwoPlayersButton();
    }

}
