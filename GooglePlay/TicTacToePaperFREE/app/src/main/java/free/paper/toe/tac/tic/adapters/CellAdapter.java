package free.paper.toe.tac.tic.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import free.paper.toe.tac.tic.tictactoepaperfree.Constants;
import free.paper.toe.tac.tic.tictactoepaperfree.R;

public class CellAdapter extends BaseAdapter {

    private final static int PADDING = 8;

    private final String EMPTY_TAG = "empty";
    private final String FULL_TAG = "full";

    private Context context;
    private int cellSize;
    private char [][]matrix;
    private Drawable xSymbol;
    private Drawable oSymbol;



    public CellAdapter(Context context, char[][] matrix, int cellSize) {
        this.context = context;
        this.matrix = matrix;
        this.cellSize = cellSize;



    }


    @Override
    public int getCount() {
        return matrix.length*matrix[0].length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view = (ImageView) convertView;
        int indexOne = position/matrix.length;
        int indexTwo = position%matrix.length;

        if(view == null) {
            view = new ImageView(context);
            view.setLayoutParams(new AbsListView.LayoutParams(cellSize, cellSize));
            view.setPadding(PADDING,PADDING,PADDING,PADDING);
            view.setTag(EMPTY_TAG);
        }

        if(view.getTag().equals(EMPTY_TAG)) {
            switch (matrix[indexOne][indexTwo]) {
                case Constants.O: view.setImageResource(R.drawable.game_o_animation);
                    ((Animatable)view.getDrawable()).start();
                    break;
                case Constants.X: view.setImageResource(R.drawable.game_x_animation);
                    ((Animatable)view.getDrawable()).start();
                    break;
                default:
                    view.setImageBitmap(null);
            }
        }

        return view;
    }
}
