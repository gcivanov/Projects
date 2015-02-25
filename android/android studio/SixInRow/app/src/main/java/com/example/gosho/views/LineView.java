package com.example.gosho.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

import com.example.gosho.sixinrow.R;

/**
 * Created by gosho on 24.1.2015 г..
 */
public class LineView extends View {

    private Context context;

    private int startX, startY, endX, endY;
    private int width;

    public LineView(Context context, int startX, int startY, int endX, int endY, int width) {
        super(context);
        this.context = context;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
    }




    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
//        int i = context.getResources().getColor(R.color.ripple_material_dark);

        paint.setColor(Color.GREEN );
        paint.setStrokeWidth(width);

//        for(int i = 0 ; i < 900 ; i+=10) {
//            canvas.drawLine(i, i, i+10, i+10, paint);
//        }

        canvas.drawLine(startX, startY, endX, endY, paint);

//        canvas.drawLine(0, 0, 700, 700, paint);
//        canvas.drawLine(700, 0, 0, 700, paint);

//        super.onDraw(canvas);
    }



}
