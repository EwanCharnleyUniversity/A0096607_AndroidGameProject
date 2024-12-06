package com.example.a0096607_androidgameproject.Entities;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.a0096607_androidgameproject.Graphics.TextureCache;
import com.example.a0096607_androidgameproject.R;
import com.example.a0096607_androidgameproject.Vector2D;
import com.example.a0096607_androidgameproject.Graphics.WorldTexture;



public abstract class Entity {

    public Vector2D position;
    public Vector2D velocity;
    public Vector2D bounding;

    public WorldTexture imageTexture;

    // TODO: abstract position, velocity, and texture generation
    // Bounding could plausibly be relegated to an internal function.
    public Entity(TextureCache textures, Context context, Vector2D Bounding, int Texture) {
        position = new Vector2D(0,0);
        velocity = new Vector2D(0,0.025f);

        bounding = Bounding;

        imageTexture = new WorldTexture(textures, (int)bounding.x,(int)bounding.y, Texture, context);
    }

    public void Simulate(long deltaTime) {}


    public void Draw(Canvas view) {
        Rect pos = new Rect(
                (int)(position.x - bounding.x / 2),
                (int)(position.y - bounding.y / 2),
                (int)(position.x + bounding.x / 2),
                (int)(position.y + bounding.y / 2)
        );

        imageTexture.Draw(view, pos);
    }

    public void Simulate(Vector2D userPosition) {}
}