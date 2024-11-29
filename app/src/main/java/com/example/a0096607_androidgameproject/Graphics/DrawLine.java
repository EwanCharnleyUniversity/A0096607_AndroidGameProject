package com.example.a0096607_androidgameproject.Graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.a0096607_androidgameproject.Vector2D;


// Just a class to quickly and dirtily represent vectors.
public class DrawLine {
    public Paint line = new Paint();

    public DrawLine() {
        line.setColor(Color.RED);
        line.setStyle(Paint.Style.STROKE);
        line.setStrokeWidth(5.f);
        line.setAntiAlias(true);
    }

    public void Draw(Vector2D origin, Vector2D head, Canvas canvas) {
        canvas.drawLine(origin.x, origin.y, head.x, head.y, line);
    }
}
