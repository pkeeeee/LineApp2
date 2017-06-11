package com.example.dima1516.lineapp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dima1516 on 11/01/2017.
 */

public class MyView extends View
{

    Random rdm = new Random();
    Paint paint = new Paint();
    int color = Color.BLACK;

    List<Point> pointsDown = new ArrayList<Point>();
    List<Point> pointsUp = new ArrayList<Point>();
    List<Integer> colors = new ArrayList<Integer>();
    int currentContact = -1;

    public MyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        // Procesar evento
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
            {
                currentContact++;
                color = Color.rgb(this.rdm.nextInt(255), this.rdm.nextInt(255), this.rdm.nextInt(255));
                colors.add(color);
                pointsDown.add(new Point());
                pointsDown.get(currentContact).x = event.getX();
                pointsDown.get(currentContact).y = event.getY();
                break;
            }

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            {
                pointsUp.add(new Point());
                pointsUp.get(currentContact).x = event.getX();
                pointsUp.get(currentContact).y = event.getY();
                this.invalidate();
                break;
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        for (int i = 0; i <= currentContact; i++)
        {
            paint.setColor(colors.get(i));
            canvas.drawLine(pointsUp.get(i).x, pointsUp.get(i).y, pointsDown.get(i).x, pointsDown.get(i).y, paint);
        }
    }
}

class Point
{
    float x, y;
}
