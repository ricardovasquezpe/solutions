package com.project.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyCustomView extends View {

    public MyCustomView(Context context) {
        super(context);
        init(null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas, Color.BLACK, getWidth()/2, getHeight()/2, 500);
        drawCircle(canvas, Color.WHITE, getWidth()/2, getHeight()/2, 120);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        drawRhombus(canvas, paint, getWidth()/2, getHeight()/2, 300);

        drawCircle(canvas, Color.RED, getWidth()/2, getHeight()/2, 55);
    }

    public void drawRhombus(Canvas canvas, Paint paint, int x, int y, int width) {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y + halfWidth+350);
        path.lineTo(x+93 - halfWidth, y);
        path.lineTo(x, y - halfWidth-350);
        path.lineTo(x-93 + halfWidth, y);
        path.lineTo(x, y + halfWidth+350);
        path.close();

        canvas.drawPath(path, paint);
    }

    public void drawCircle(Canvas canvas, int color, int x, int y, int radius){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }
}
