package com.example.gosho.com.example.gosho.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gosho.ninrow.Board;
import com.example.gosho.sixinrow.R;

/**
 * Created by gosho on 13.1.2015 г..
 */
public class CellsAdapter extends BaseAdapter {

    private final static int BOARD_MARGING = 8;

    private Context context;
    private View views[][];

    private int size;

    private Board mBoard;

    public CellsAdapter( Context context, Board board, int size ){
        this.mBoard = board;
        this.context = context;
        this.size = size;
        views = new View[mBoard.getBoardSize()][mBoard.getBoardSize()];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view = (ImageView) convertView;

        if(view == null) {
            view = new ImageView(context);
            view.setLayoutParams(new AbsListView.LayoutParams(size,size));
            views[position/mBoard.getBoardSize()][position%mBoard.getBoardSize()] = view;
        }

        char matrixEl = mBoard.getMatrixElement(position/mBoard.getBoardSize(), position%mBoard.getBoardSize());
        if(matrixEl == mBoard.getEmptySymb()) {
            view.setBackgroundResource(R.drawable.cells_shape);
        } else if(matrixEl == mBoard.getPlayerSymb()) {
            view.setBackgroundResource(R.drawable.cell_x);
        } else {
            view.setBackgroundResource(R.drawable.cell_o);
        }

        return view;
    }

    private int[] calcoltateRowsAndColsNumb(int position) {
        return new int[]{position/mBoard.getBoardSize(), position%mBoard.getBoardSize()  };
    }


    public void setBoard(Board board) {
        this.mBoard = board;
    }
    public View[][] getViews() { return  views; }

    @Override
    public int getCount() {
        return mBoard.getBoardSize()*mBoard.getBoardSize();
    }

    @Override
    public Object getItem(int position) {
        return mBoard.getMatrixElement(position/mBoard.getBoardSize(), position%mBoard.getBoardSize());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
