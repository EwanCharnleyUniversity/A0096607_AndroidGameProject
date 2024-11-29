package com.example.a0096607_androidgameproject.Weapons;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import com.example.a0096607_androidgameproject.Vector2D;


// Bullet Manager handles all bullets on scene, increasing performance in the process.
public class BulletManager {

    private static final int BULLET_CAPACITY = 512;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();


    public BulletManager() {}

    public synchronized void SpawnBullet(Vector2D userPosition) {
        // Re-initializes the bullet if the capacity is exceeded.
        if (bullets.size() >= BULLET_CAPACITY) {
            Bullet reference = bullets.get(0);
            bullets.remove(reference);

            reference = new Bullet(userPosition, 1, 1);
            bullets.add(bullets.size(), reference);

            //Log.d("Bullet Manager", "Bullet recycled!");
        }
        else {
            bullets.add(new Bullet(userPosition, 1, 1));
        }

        //Log.d("Bullet Manager", "Bullets stored: " + bullets.size() + "     Bullet Capacity: " + BULLET_CAPACITY);
    }


    public synchronized void SimulateBullets(long time) {

        if (bullets.isEmpty()) {
            return;
        }

        for(Bullet target : bullets) {
            target.Simulate(time);
        }
    }


    public synchronized void RenderBullets(Canvas canvas) {
        if (bullets.isEmpty()) {
            return;
        }

        int limit = 0;
        for (Bullet target : bullets) {
            if (target.visible) { limit++; }

            if (limit > BULLET_CAPACITY / 4) { break; }
            target.Render(canvas);
        }
        //Log.d("Bullet Renderer", "limit: " + limit);
    }
}