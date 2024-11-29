package com.example.a0096607_androidgameproject.Entities;

import android.content.Context;
import android.graphics.Canvas;

import java.util.Random;


public class Enemy extends Entity {

    boolean alive = true;

    public Enemy(Context context) {
        super(context);
        position.x = new Random().nextInt(1000);
        position.y = 0;
    }

    @Override
    public void Simulate() {
        if (!alive) {
            return;
        }
        position.y += 2.5f;
    }

    @Override
    public void Draw(Canvas view) {
        if (!alive) {
            return;
        }

        super.Draw(view);
    }
}
