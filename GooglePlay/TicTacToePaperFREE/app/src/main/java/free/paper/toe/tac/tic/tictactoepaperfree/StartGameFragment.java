package free.paper.toe.tac.tic.tictactoepaperfree;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import free.paper.toe.tac.tic.adapters.CellAdapter;
import free.paper.toe.tac.tic.dialogs.MyDialogFragment;
import free.paper.toe.tac.tic.views.ButtonView;
import free.paper.toe.tac.tic.alphabetaalgorithm.Board;

public class StartGameFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String ARG_PLAY_WITH_FIRST_PLAYER = "param1";
    private static final String ARG_PLAY_FIRST = "param2";
    private static final String ARG_DIFF_LEVEL = "param3";
    private static final String ARG_PLAYER1_NAME = "param4";
    private static final String ARG_PLAYER2_NAME = "param5";

    private TextView mScoreTextView;
    private GridView mBoardGridView;
    private ButtonView mNewGameButton;

    private String player1Name;
    private String player2Name;
    private char player1Symbol;
    private char player2Symbol;
    private char diffLevel;
    private Handler handler;

    private boolean isPlayer1Turn;
    private int player1Wins = 0;
    private int player2Wins = 0;

    private Board boardB;

    private CellAdapter adapter;

    private static PlayerWinDialog mWinnerDialog;
    private static StandoffDialog mStandoffDialog;



    private OnStartGameFragmentInteractionListener mListener;


    public static StartGameFragment newInstance(char playWithFirstPlayer,char playFirst,char diffLevel,
                                                String player1Name, String player2Name) {
        StartGameFragment fragment = new StartGameFragment();
        Bundle args = new Bundle();
        args.putChar(ARG_PLAY_WITH_FIRST_PLAYER, playWithFirstPlayer);
        args.putChar(ARG_PLAY_FIRST, playFirst);
        args.putChar(ARG_DIFF_LEVEL, diffLevel);
        args.putString(ARG_PLAYER1_NAME, player1Name);
        args.putString(ARG_PLAYER2_NAME, player2Name);
        fragment.setArguments(args);
        return fragment;
    }

    public StartGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player1Name = getArguments().getString(ARG_PLAYER1_NAME);

            player2Name = getArguments().getString(ARG_PLAYER2_NAME);
            player1Symbol = getArguments().getChar(ARG_PLAY_WITH_FIRST_PLAYER, Constants.X);
            player2Symbol = (player1Symbol == Constants.X)? Constants.O : Constants.X;

            if(getArguments().getChar(ARG_PLAY_FIRST) == player1Symbol){
                isPlayer1Turn = true;
            } else {
                isPlayer1Turn = false;
            }


            diffLevel =getArguments().getChar(ARG_DIFF_LEVEL, Constants.PLAYER_LEVEL);
            switch (diffLevel) {
                case Constants.MEDIUM_LEVEL: boardB = new Board(2,player1Symbol, player2Symbol);
                    break;
                case Constants.EASY_LEVEL: boardB = new Board(1,player1Symbol, player2Symbol);
                    break;
                default:
                    boardB = new Board(-1,player1Symbol, player2Symbol);
            }
            handler = new Handler();
            adapter = new CellAdapter(getActivity(),
                    boardB.getBoard() ,getWindowSmallerSize()/3);

            mWinnerDialog = new PlayerWinDialog();
            mStandoffDialog = new StandoffDialog();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start_game, container, false);

        mScoreTextView = (TextView) view.findViewById(R.id.scoreTextView);
        mBoardGridView = (GridView) view.findViewById(R.id.boardGridView);
        mNewGameButton = (ButtonView) view.findViewById(R.id.newGameButton);
//        mBoardGridView.setLayoutParams(new LinearLayout.LayoutParams(getWindowSmallerSize(),getWindowSmallerSize()) );
        updateScore();

        mBoardGridView.setAdapter(adapter);
        mBoardGridView.setOnItemClickListener(this);

        mNewGameButton.setOnClickListener(this);



        checkFirstStep();
        return view;
    }

    private void checkFirstStep(){
        if(isPlayer1Turn == false && diffLevel != Constants.PLAYER_LEVEL) {
            boardB.placeARandomMove(player2Symbol);
            adapter.notifyDataSetChanged();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isPlayer1Turn = true;
                }
            },500);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if( diffLevel != Constants.PLAYER_LEVEL ) {
            playVsComp( position );
        } else {
            playVsPlayer( position );
        }
    }

    public void playVsComp(int position){
        if(isPlayer1Turn) {
//            Toast.makeText(getActivity(), ""+boardB.isEmptyElement(position), Toast.LENGTH_SHORT).show();
            if (boardB.isEmptyElement(position)) {
                isPlayer1Turn = false;

                boardB.placeAMove(position, player1Symbol);
                adapter.notifyDataSetChanged();


                if (!hasWinner()) {
                    if(boardB.getPlayer2Turns() <= 1) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boardB.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, player2Symbol);
                                boardB.placeARandomMove(player2Symbol);
                                adapter.notifyDataSetChanged();

                                if (!hasWinner()) {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            isPlayer1Turn = true;

                                        }
                                    }, 450);
                                }
                            }
                        }, 600);
                    }else{
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                        boardB.placeARandomMove(player2Symbol);
                                boardB.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, player2Symbol);
                                boardB.placeAMove(boardB.returnBestMove(), player2Symbol);
                                adapter.notifyDataSetChanged();


                                if (!hasWinner()) {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            isPlayer1Turn = true;

                                        }
                                    }, 450);
                                }
                            }
                        }, 600);
                    }
                }
            }
        }
    }

    public void playVsPlayer(int position) {
        if(isPlayer1Turn) {
            if (boardB.isEmptyElement(position)) {
                isPlayer1Turn = false;

                boardB.placeAMove(position, player1Symbol);
                adapter.notifyDataSetChanged();


                hasWinner();

            }
        } else {
            if (boardB.isEmptyElement(position)) {
                isPlayer1Turn = true;

                boardB.placeAMove(position, player2Symbol);
                adapter.notifyDataSetChanged();


                hasWinner();
            }
        }
    }

    private boolean hasWinner(){
        if (boardB.hasWinner(player1Symbol)) {
            isPlayer1Turn = true;
            mWinnerDialog.setWinner(player1Name);
            if(!mWinnerDialog.isAdded()) {
                mWinnerDialog.show(getActivity().getSupportFragmentManager(), null);
            }

            mBoardGridView.setOnItemClickListener(null);
            ++player1Wins;
            return true;

        } else if (boardB.hasWinner(player2Symbol)) {
            isPlayer1Turn = false;
            mWinnerDialog.setWinner(player2Name);
            if(!mWinnerDialog.isAdded()) {
                mWinnerDialog.show(getActivity().getSupportFragmentManager(), null);
            }

            mBoardGridView.setOnItemClickListener(null);
            ++player2Wins;
            return true;

        } else if(boardB.hasNoWinner()){

//            isPlayer1Turn = (isPlayer1Turn)?false:true;

            if(!mStandoffDialog.isAdded()) {
                mStandoffDialog.show(getActivity().getSupportFragmentManager() , null);
            }

            mBoardGridView.setOnItemClickListener(null);
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mNewGameButton.getId()){
            newGame();
        }
    }

    private void newGame(){
//        Toast.makeText(getActivity(), "? "+isPlayer1Turn, Toast.LENGTH_SHORT).show();

        boardB.resetBoard();

        adapter.notifyDataSetChanged();
        mBoardGridView.setOnItemClickListener(this);
        updateScore();
        checkFirstStep();
    }

    private void updateScore(){
        int space = 12;
        String name2 = player2Name;
        if(player2Name.length() > 12){
            name2 = String.format("%"+space+"."+space+"s",player2Name);
        } else {
            int num = space-player2Name.length();
            name2 = String.format("%s"+"%"+num+"."+num+"s" ,player2Name,"");
        }

        mScoreTextView.setText(String.format("%"+space+"."+space+"s   %d:%d   %s"
                ,player1Name,  player1Wins, player2Wins, name2));
    }
    private int getWindowSmallerSize(){
        WindowManager wm = (WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int width, height;

        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < Build.VERSION_CODES.JELLY_BEAN) {
            width = display.getWidth();
            height = display.getHeight();
        } else {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        }

        return (width < height)? width: height;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStartGameFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStartGameFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnStartGameFragmentInteractionListener {
        public void popBackStackFromFragmentManager();
    }


    public class PlayerWinDialog extends MyDialogFragment{
        private String winner;

        public void setWinner(String winner){
            this.winner = winner;
        }

        @Override
        public String setText() {
            return player1Name + " win !\n" +
                    " Play new game?";
        }

        @Override
        public void yesButtonClicked() {
            newGame();
        }

        @Override
        public void noButtonClicked() {
            mListener.popBackStackFromFragmentManager();
        }
    }

    public class StandoffDialog extends MyDialogFragment{

        @Override
        public String setText() {

//            return "X no winners ..\n Play new game?";
            return "No winner.. Very SAD !\nPlay new game?";
        }

        @Override
        public void yesButtonClicked() {
            newGame();
        }

        @Override
        public void noButtonClicked() {
            mListener.popBackStackFromFragmentManager();
        }
    }
}
