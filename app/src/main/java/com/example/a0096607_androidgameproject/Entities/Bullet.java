package com.example.a0096607_androidgameproject.Entities;


import android.content.Context;

import com.example.a0096607_androidgameproject.Vector2D;

public class Bullet extends Entity {
    int damage, pierce;

    public Bullet(Context context) {
        super(context);
    }

    @Override
    public void Simulate() {

        position.Addition(new Vector2D(0,100));
    }

    @Override
    public void Simulate(Vector2D userPosition) {}
}
