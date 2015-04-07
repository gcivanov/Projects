package free.paper.toe.tac.tic.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import free.paper.toe.tac.tic.tictactoepaperfree.R;
import free.paper.toe.tac.tic.typefaces.TypeFaces;


public class ButtonView extends Button {

    private static MediaPlayer mediaPlayer = null;


    public ButtonView(Context context) {
        super(context);
        if(!isInEditMode())
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.next_page);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode())
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.next_page);
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(!isInEditMode())
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.next_page);
    }



    @Override
    public void setOnClickListener(final OnClickListener l) {
        super.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // do your stuff, in this case play sound
                playSound();

                l.onClick(v);
            }
        });
    }

    private void playSound() {
        mediaPlayer.start();
    }

    private void init(){
        setTransformationMethod(null);

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change - in MainActivity
//        setTypeface(TypeFaces.getTypeFace(getContext(), "fonts/tempussansitc.ttf") , Typeface.NORMAL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        init();

//        int sdk = Build.VERSION.SDK_INT;
//        if(sdk < Build.VERSION_CODES.JELLY_BEAN) {
//            this.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector));
//        } else {
//            this.setBackground(getResources().getDrawable(R.drawable.selector));
//        }

        super.onDraw(canvas);

    }
}
