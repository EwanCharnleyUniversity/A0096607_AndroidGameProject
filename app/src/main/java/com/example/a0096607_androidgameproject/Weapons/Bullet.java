package com.example.a0096607_androidgameproject.Weapons;

import android.graphics.Canvas;

import com.example.a0096607_androidgameproject.Graphics.DrawLine;
import com.example.a0096607_androidgameproject.Vector2D;


public class Bullet {
    final private Vector2D startPoint;
    final private Vector2D endPoint = new Vector2D(0,-5000);

    public int damage;
    public int pierce;

    long timeActive = 0;
    long lifespan = 133;

    boolean visible;


    public Bullet(Vector2D userPosition, int Damage, int Pierce) {
        damage = Damage;
        pierce = Pierce;
        visible = true;

        startPoint = new Vector2D(userPosition.x, userPosition.y);
        endPoint.Addition(startPoint);
    }

    public void Simulate(long time) {
        timeActive += time;

        if (timeActive > lifespan) {
            visible = false;
        }

    }


    public void Render(Canvas canvas) {
        if (!visible) { return; }

        DrawLine line = new DrawLine();
        line.Draw(startPoint, endPoint, canvas);
    }
}


// Bullets are a raycast using a vector to determine their line of intersection.
// From this raycast, a temporary visual object could be created to travel the length of the cast, acting as the bullet.

// However, while the raycast themselves would be computationally simple to simulate, the textures will take up more processing
// power to generate, load, and keep track of. A fairly simple method to reduce performance hit is to make rendering the bullets
// have a cap lower than that of simulating them.

// IE, we have 100 bullets stored out of a cap of 512 (or x). Let's say we visualise a quarter of x while the rest are process in
// raw calculation. since 512 / 4 = 128, the 100 bullets will be rendered.

// However, if we had 256 bullets stored, 128 of those bullets would be simulated but not rendered.
