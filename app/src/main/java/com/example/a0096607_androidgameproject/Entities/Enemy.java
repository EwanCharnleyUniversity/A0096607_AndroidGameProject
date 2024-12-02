package com.example.a0096607_androidgameproject.Entities;

import android.content.Context;
import android.graphics.Canvas;

import com.example.a0096607_androidgameproject.R;
import com.example.a0096607_androidgameproject.Vector2D;

import java.util.Random;


public class Enemy extends Entity {

    public boolean alive = true;

    public Enemy(Context context) {
        super(context, new Vector2D(300,100), R.drawable.kerfus);
        position.x = new Random().nextInt(1000);
        position.y = 0;
    }

    @Override
    public void Simulate(long deltaTime) {
        if (!alive) {
            return;
        }

        if (position.y >= 2000) {
            alive = false;
        }

        position.y += 0.1f * deltaTime;
    }

    @Override
    public void Draw(Canvas view) {
        if (!alive) {
            return;
        }

        super.Draw(view);
    }
}
