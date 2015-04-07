package free.paper.toe.tac.tic.tictactoepaperfree;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import free.paper.toe.tac.tic.views.ButtonView;


public class StartFragment extends Fragment implements View.OnClickListener {

    private OnStartFragmentInteractionListener mListener;

    private ButtonView mOnePlayerButton;
    private ButtonView mTwoPlayersButton;
    private ButtonView mOptionsButton;

    public static StartFragment newInstance(String param1, String param2) {
        StartFragment fragment = new StartFragment();
        return fragment;
    }

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mOnePlayerButton = (ButtonView) rootView.findViewById(R.id.buttonOnePlayer);
        mTwoPlayersButton = (ButtonView) rootView.findViewById(R.id.buttonTwoPlayers);
        mOptionsButton = (ButtonView) rootView.findViewById(R.id.buttonOptions);

        mOnePlayerButton.setOnClickListener(this);
        mTwoPlayersButton.setOnClickListener(this);
        mOptionsButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == mOptionsButton.getId()) {
            mListener.onClickOptionsButton();
        } else if(v.getId() == mOnePlayerButton.getId()) {
            mListener.onClickOnePlayerButton();
        } else if(v.getId() == mTwoPlayersButton.getId()) {
            mListener.onClickTwoPlayersButton();
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStartFragmentInteractionListener) activity;
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


    public interface OnStartFragmentInteractionListener {
        public void onClickOptionsButton();
        public void onClickOnePlayerButton();
        public void onClickTwoPlayersButton();
    }

}
