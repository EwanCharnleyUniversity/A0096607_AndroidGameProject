package com.example.a0096607_androidgameproject.Entities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.a0096607_androidgameproject.Weapons.Bullet;

import java.util.ArrayList;


public class EnemyManager {
    private final int ENEMY_LIMIT = 512;
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    long spawnerTime = 0;


    public EnemyManager() {}

    public synchronized void AddEnemy(Context context) {
        if (enemies.size() >= ENEMY_LIMIT) {
            Enemy reference = enemies.get(0);
            enemies.remove(reference);

            reference = new Enemy(context);
            enemies.add(enemies.size(), reference);

            //Log.d("Enemy Manager", "Enemy recycled!");
        }
        else {
            enemies.add(new Enemy(context));
        }

        //Log.d("Enemy Manager", "Enemies stored: " + enemies.size() + "     Enemy Capacity: " + ENEMY_LIMIT);
    }

    public synchronized void SimulateEnemies(long deltaTime) {

        if (enemies.isEmpty()) {
            return;
        }

        for(Enemy target : enemies) {
            target.Simulate(deltaTime);
        }
    }

    public synchronized void Spawner(Context context, long deltaTime) {
        spawnerTime -= deltaTime;

        if (spawnerTime <= 0) {
            AddEnemy(context);
            spawnerTime = 100;
        }
    }

    public synchronized void RenderEnemies(Canvas canvas) {
        if (enemies.isEmpty()) {
            return;
        }

        int limit = 0;
        for(Enemy target : enemies) {
            limit++;
            if (limit > ENEMY_LIMIT / 4) {
                break;
            }

            target.Draw(canvas);
        }
    }
}
