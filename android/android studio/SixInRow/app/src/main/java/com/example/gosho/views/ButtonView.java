package com.example.gosho.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.gosho.sixinrow.R;

/**
 * TODO: document your custom view class.
 */
public class ButtonView extends Button {

    private MediaPlayer mediaPlayer;


    public ButtonView(Context context) {
        super(context);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.next_page);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.next_page);
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.next_page);
    }



    @Override
    public void setOnClickListener(final OnClickListener l) {
        super.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // do your stuff, in this case play sound
                playSound();

                // delegate the click event to the initial listener
                l.onClick(v);
            }
        });
    }

    private void playSound() {
        mediaPlayer.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector));
        } else {
            this.setBackground(getResources().getDrawable(R.drawable.selector));
        }

        super.onDraw(canvas);

    }
}
