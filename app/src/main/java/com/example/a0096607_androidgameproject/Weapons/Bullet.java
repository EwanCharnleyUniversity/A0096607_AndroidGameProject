package com.example.a0096607_androidgameproject.Weapons;

import android.graphics.Canvas;
import android.util.Log;

import com.example.a0096607_androidgameproject.Entities.Enemy;
import com.example.a0096607_androidgameproject.Entities.EnemyManager;
import com.example.a0096607_androidgameproject.Graphics.DrawLine;
import com.example.a0096607_androidgameproject.Vector2D;


public class Bullet {
    final private Vector2D startPoint;
    final private Vector2D endPoint = new Vector2D(0,-5000);

    public int damage;
    public int pierce;

    long timeActive = 0;
    long lifespan = 133;

    boolean visible = true;


    public Bullet(Vector2D userPosition, int Damage, int Pierce) {
        damage = Damage;
        pierce = Pierce;

        startPoint = new Vector2D(userPosition.x, userPosition.y);
        endPoint.Addition(startPoint);
    }


    public void Simulate(long time, EnemyManager Enemies) {
        if (!visible) {
            return;
        }

        timeActive += time;

        // https://www.youtube.com/watch?v=USjbg5QXk3g
        // We grab the fraction of our line to the bounding box of the enemy (f)
        // We have f1 and f2 representing the bottom clip and top clip of the line to the enemies y bounding values.
        // TODO: test out non precise rotated vectors for enemy collision (shotgun logic, spray, etc).
        for (Enemy enemy : Enemies.enemies) {
            if (pierce <= 0) {
                return;
            }

            float enemyTop      = enemy.position.y - enemy.bounding.y / 2;
            float enemyBottom   = enemy.position.y + enemy.bounding.y / 2;
            float enemyLeft     = enemy.position.x - enemy.bounding.x / 2;
            float enemyRight    = enemy.position.x + enemy.bounding.x / 2;

            float topFraction = (enemyTop - startPoint.y) / (endPoint.y - startPoint.y);
            float bottomFraction = (enemyBottom - startPoint.y) / (endPoint.y - startPoint.y);

            float clippedLineTop = startPoint.y - (5000 * topFraction);
            float clippedLineBottom = startPoint.y - (5000 * bottomFraction);

            // Basic collision, is bullet within the enemies bounding?
            if (startPoint.x > enemyLeft && startPoint.x < enemyRight) {
                Log.d("Bullet!", "Collision");

                enemy.alive = false;
                pierce--;
            }
        }


        if (timeActive > lifespan) {
            visible = false;
        }
    }


    public void Render(Canvas canvas) {
        if (!visible) { return; }

        DrawLine line = new DrawLine();
        line.Draw(startPoint, endPoint, canvas);

        // Immediately turns off line rendering if it has no pierce, so it renders only once.
        if (pierce <= 0) {
            visible = false;
        }
    }
}


// Bullets are a ray cast using a vector to determine their line of intersection.
// From this ray cast, a temporary visual object could be created to travel the length of the cast, acting as the bullet.

// However, while the ray cast themselves would be computationally simple to simulate, the textures will take up more processing
// power to generate, load, and keep track of. A fairly simple method to reduce performance hit is to make rendering the bullets
// have a cap lower than that of simulating them.

// IE, we have 100 bullets stored out of a cap of 512 (or x). Let's say we visualise a quarter of x while the rest are process in
// raw calculation. since 512 / 4 = 128, the 100 bullets will be rendered.

// However, if we had 256 bullets stored, 128 of those bullets would be simulated but not rendered.
