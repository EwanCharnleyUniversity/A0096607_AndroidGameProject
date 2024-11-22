package com.example.a0096607_androidgameproject.Entities;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.a0096607_androidgameproject.R;
import com.example.a0096607_androidgameproject.Vector2D;
import com.example.a0096607_androidgameproject.WorldTexture;



public abstract class Entity {

    public Vector2D position;
    public Vector2D velocity;
    public Vector2D bounding;

    public WorldTexture imageTexture;

    // TODO: abstract position, velocity, and texture generation
    // Bounding could plausibly be relegated to an internal function.
    public Entity(Context context) {
        position = new Vector2D(500,500);
        velocity = new Vector2D(0,0.025f);

        bounding = new Vector2D(250,250);

        imageTexture = new WorldTexture((int)bounding.X,(int)bounding.Y, R.drawable.coffee, context);
    }

    public void Simulate() {}


    public void Draw(Canvas view) {

        Rect pos = new Rect(
                (int)position.X,
                (int)position.Y,
                (int)(position.X + bounding.X),
                (int)(position.Y + bounding.Y)
        );

        imageTexture.Draw(view, pos);
    }

    public abstract void Simulate(Vector2D userPosition);
}
