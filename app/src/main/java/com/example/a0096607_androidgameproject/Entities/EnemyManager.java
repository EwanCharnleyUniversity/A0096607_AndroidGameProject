package com.example.a0096607_androidgameproject.Entities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.a0096607_androidgameproject.Graphics.TextureCache;
import com.example.a0096607_androidgameproject.Weapons.Bullet;

import java.util.ArrayList;
import java.util.Vector;


public class EnemyManager {
    private final int ENEMY_LIMIT = 1;
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public EnemyManager() {}

    public synchronized void AddEnemy(TextureCache textures, Context context) {
        //Log.d("Enemy Manager", "size: " + enemies.size());

        // Checks the current enemy pool for any dead enemies. If any are found, they are put into a dead enemy pool
        // and the first dead enemy is recycled immediately, rather than generate new enemies up until the cap is reached.
        Vector<Integer> deadEnemyPool = new Vector<Integer>();
        for (int i = 0; i != enemies.size(); i++) {
            if (!enemies.get(i).alive) {
                deadEnemyPool.add(i);
            }
        }

        if (!deadEnemyPool.isEmpty()) {
            Enemy reference = enemies.get(deadEnemyPool.get(0));
            enemies.remove(reference);

            reference = new Enemy(textures, context);
            enemies.add(enemies.size(), reference);

            return;
        }

        // Otherwise, if the enemy pool is reached with no death, we recycle the first enemy immediately as to prevent
        // indefinite generation.
        if (enemies.size() == ENEMY_LIMIT) {
            Enemy reference = enemies.get(0);
            enemies.remove(reference);

            reference = new Enemy(textures, context);
            enemies.add(enemies.size(), reference);
        }
        else {
            enemies.add(new Enemy(textures, context));
        }
    }

    public synchronized void SimulateEnemies(long deltaTime) {

        if (enemies.isEmpty()) {
            return;
        }

        for(Enemy target : enemies) {
            if (target.alive) {
                target.Simulate(deltaTime);
            }
        }
    }

    // Spawner logic
    private long spawnerTime = 0;
    private long spawnTime = 1000;
    public synchronized void Spawner(TextureCache textures, Context context, long deltaTime) {

        spawnerTime -= deltaTime;

        if (spawnerTime <= 0) {
            spawnerTime = spawnTime;
            AddEnemy(textures, context);
        }
    }

    public synchronized void RenderEnemies(Canvas canvas) {
        if (enemies.isEmpty()) {
            return;
        }

        int limit = 0;
        for(Enemy target : enemies) {
            limit++;
            if (limit > 128) {
                break;
            }

            target.Draw(canvas);
        }
    }
}
