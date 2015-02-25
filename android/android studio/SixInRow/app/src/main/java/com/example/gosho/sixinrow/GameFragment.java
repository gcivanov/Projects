package com.example.gosho.sixinrow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gosho.com.example.gosho.adapters.CellsAdapter;
import com.example.gosho.ninrow.Board;
import com.example.gosho.views.LineView;

import org.w3c.dom.Text;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class GameFragment extends Fragment implements GridView.OnItemClickListener {

    private static final String NUM_PAR = "NUM_PAR";
    private static final String SIZE_PAR = "SIZE_PAR";
    private static final String  PLAYER_WIN_TAG = "COUNT_PLAYER_WINS";
    private static final String  COMP_WIN_TAG = "COMP_WINS";


    private int playerWinsCount;
    private int compWinsCount;

    private Board mBoard;

    private MediaPlayer mediaPlayer;
    private FrameLayout mLayout;


    private CellsAdapter adapter;

    private GridView mGridView;
    private TextView mResultTextView;

    private OnGameFragmentInteractionListener mListener;

    public static GameFragment newInstance(int num, int N, int playerWins, int compWins) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putInt(NUM_PAR, num);
        args.putInt(SIZE_PAR, N);
        args.putInt(PLAYER_WIN_TAG, playerWins);
        args.putInt(COMP_WIN_TAG, compWins);
        fragment.setArguments(args);


        return fragment;
    }

    public GameFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(getActivity(), ""+mBoard, Toast.LENGTH_SHORT).show();
        if (getArguments() != null) {
            mediaPlayer = MediaPlayer.create(getActivity() , R.raw.make_move);
            int connectN= getArguments().getInt(NUM_PAR);
            int boardNSize = getArguments().getInt(SIZE_PAR);
            playerWinsCount = getArguments().getInt(PLAYER_WIN_TAG);
            compWinsCount = getArguments().getInt(COMP_WIN_TAG);

            mBoard = new Board(boardNSize, connectN, 'x', 'o');

//            Toast.makeText(getActivity(), ""+mBoard, Toast.LENGTH_SHORT).show();

            int width = getWindowWidthSize();
            adapter = new CellsAdapter(getActivity(), mBoard, width / mBoard.getBoardSize());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        mLayout = (FrameLayout) view.findViewById(R.id.gameFramgnetLayout);

        mGridView = (GridView) view.findViewById(R.id.gridView);

        mResultTextView = (TextView) view.findViewById(R.id.resultTextView);
        mResultTextView.setText(String.format("P: %d | %d :C",playerWinsCount, compWinsCount));

        if( adapter != null) {
            mGridView.setAdapter( adapter );
            mGridView.setNumColumns(mBoard.getBoardSize());
        }

        mGridView.setOnItemClickListener( this );
        return view;
    }



    private boolean canClick = true;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(adapter.getItem(position) == mBoard.getEmptySymb() && canClick) {
            mediaPlayer.start();
            canClick = false;

            mBoard.setMatrixPlayerElement(position / mBoard.getBoardSize(), position % mBoard.getBoardSize());
            adapter.notifyDataSetChanged();


            if( gameOver() )
                return;

            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(500);

                        getActivity().runOnUiThread( new Runnable() {
                            @Override
                            public void run() {
                                mBoard.compMove();
                                adapter.notifyDataSetChanged();

                                gameOver();
                                canClick = true;

                            }
                        } );

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }




    private boolean gameOver(){
        char endOfGameSymb = mBoard.endOfGame();
        if (endOfGameSymb != mBoard.getEmptySymb()) {

            if(endOfGameSymb != mBoard.getNeotralSumb()) {

//                mGridView.setOnItemClickListener(null);

                View[][] views = adapter.getViews();
                com.example.gosho.ninrow.Point[] p = mBoard.getLastCoord();

                View[] winnerViews = new View[]{views[p[0].x][p[0].y], views[p[1].x][p[1].y]};

                if(mBoard.getBoardSize() == 3 ) {
                    Toast.makeText(getActivity(), "x " + getRelativeLeft(winnerViews[0]) + "|"+getRelativeTop(winnerViews[0]) , Toast.LENGTH_SHORT).show();

                    drawLineEndGame(getRelativeLeft(winnerViews[0]) + winnerViews[0].getWidth() / 2, getRelativeTop(winnerViews[0]),
                            getRelativeLeft(winnerViews[1]) + winnerViews[1].getWidth() / 2, getRelativeTop(winnerViews[1]), 12, endOfGameSymb);
                } else if(mBoard.getBoardSize() == 10) {
                    drawLineEndGame(getRelativeLeft(winnerViews[0]) + winnerViews[0].getWidth() / 2, getRelativeTop(winnerViews[0]) - winnerViews[0].getHeight() / 4,
                            getRelativeLeft(winnerViews[1]) + winnerViews[1].getWidth() / 2, getRelativeTop(winnerViews[1])- winnerViews[1].getHeight() / 4, 12, endOfGameSymb);
                } else if(mBoard.getBoardSize() != 10) {
                    drawLineEndGame(getRelativeLeft(winnerViews[0]) + winnerViews[0].getWidth() / 2, getRelativeTop(winnerViews[0]) - winnerViews[0].getHeight() / 2,
                            getRelativeLeft(winnerViews[1]) + winnerViews[1].getWidth() / 2, getRelativeTop(winnerViews[1])- winnerViews[1].getHeight() / 2, 12, endOfGameSymb);
                }


                    Toast.makeText(getActivity(), endOfGameSymb +" GAME OVER " +getRelativeLeft(winnerViews[0])+" "+ getRelativeTop(winnerViews[0])+" " +
                                    getRelativeLeft(winnerViews[1])+ " " + getRelativeTop(winnerViews[1]), Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(getActivity(), endOfGameSymb + " GAME OVER - winner: NO", Toast.LENGTH_SHORT).show();
            }


            return true;
        }
        return false;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnGameFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnGameFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private int getWindowWidthSize(){
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int width;

        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < Build.VERSION_CODES.JELLY_BEAN) {
            width = display.getWidth();
        } else {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }

        return width;
    }


    private int getRelativeLeft(View myView) {
        int left = 0;
        Log.v("TAGG","    " +myView.getParent());

        if (myView.getParent() == myView.getRootView()) { // tuka 1
            left = myView.getLeft();
        } else if(myView.getParent() == null) {
            left = myView.getLeft() + getRelativeLeft(mGridView);
        } else {
            left = myView.getLeft() + getRelativeLeft((View) myView.getParent()); // TUKA 2
        }
        return left ;
    }


    private int getRelativeTop(View myView) {
        int top;
        if ( myView.getParent() == myView.getRootView()) {
            top = myView.getTop();
        } else if(myView.getParent() == null) {
            top = (myView.getLeft() + getRelativeTop(mGridView));
        } else {
            top =myView.getTop() + getRelativeTop((View) myView.getParent());
        }
        return top;
    }

    //draw line with Thread and runOnUiThread
    private void drawLineEndGame(final int startX, final int startY, final int endX, final int endY, final int lineWidth, final char winner){
//        Toast.makeText(getActivity(), "S: " + startY + " " + startY +" E: "+ endX +" " + endY , Toast.LENGTH_SHORT).show();
        Log.v("tagw", " " + startX + " " + startY + " " + endX + " " + endY );
        Thread lineThred = new Thread(new Runnable() {
            @Override
            public void run() {
                int forX = startX;
                int forY = startY;

                int stepX = (endX - startX)/10;
                int stepY = (endY - startY)/10;

                while ((forX <= endX && forY <= endY)) {

                    final int x1 = forX;
                    final int y1 = forY;

                    final int x2 = forX + stepX;
                    final int y2 = forY + stepY;

//                    Log.v("TAGGG", "  X:  " + forX + "  Y:  " + forY);

                    getActivity().runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    mLayout.addView(new LineView(getActivity(), x1, y1, x2, y2, 10));
                                }
                            }
                    );

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    forX += stepX;
                    forY += stepY;
                }

                if(winner == mBoard.getCompSymb()) {
                    ++compWinsCount;
                    mListener.gameOver(-1, mBoard.getConnectN(), mBoard.getBoardSize(), playerWinsCount, compWinsCount);
                } else if(winner == mBoard.getPlayerSymb()) {
                    ++playerWinsCount;
                    mListener.gameOver(1, mBoard.getConnectN(), mBoard.getBoardSize(), playerWinsCount, compWinsCount);
                } else if(winner == mBoard.getNeotralSumb()) {
                    mListener.gameOver(0, mBoard.getConnectN(), mBoard.getBoardSize(), playerWinsCount, compWinsCount);
                }

            }
        });
        lineThred.start();
    }



    private class DrawSmoothLine extends AsyncTask<LineCoordinates, LineView, Integer> {

        @Override
        protected Integer doInBackground(LineCoordinates... params) {
            return 0;
        }

        @Override
        protected void onProgressUpdate(LineView... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
        }

    }


    public interface OnGameFragmentInteractionListener {
        public void gameOver(int winner, int num, int boardSze, int playerWinns, int compWins);
    }

}
