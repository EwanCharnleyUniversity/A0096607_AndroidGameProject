package com.example.a0096607_androidgameproject.Weapons;

import android.graphics.Canvas;
import android.util.Log;

import com.example.a0096607_androidgameproject.Entities.Enemy;
import com.example.a0096607_androidgameproject.Entities.EnemyManager;
import com.example.a0096607_androidgameproject.Graphics.DrawLine;
import com.example.a0096607_androidgameproject.Vector2D;

import static com.example.a0096607_androidgameproject.Algebra.LineToLineDetection;
import static com.example.a0096607_androidgameproject.Algebra.VectorRotationInRange;
import static com.example.a0096607_androidgameproject.Algebra.VectorSubtraction;

import java.util.Vector;


public class Bullet {
    final private Vector2D originPoint;
    final private Vector2D endPoint;

    public int damage;
    public int pierce;

    long timeActive = 0;
    long lifespan = 133;

    boolean visible = true;


    public Bullet(Vector2D userPosition, int Damage, int Pierce) {
        damage = Damage;
        pierce = Pierce;

        originPoint = new Vector2D(userPosition.x, userPosition.y);
        endPoint = new Vector2D(userPosition.x, userPosition.y);

        // Rotation test
        Vector2D rotation = new Vector2D(0,-5000);
        //rotation = VectorRotationInRange(rotation, 5);
        endPoint.Addition(rotation);
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

            Vector2D enemyBL = new Vector2D(
                    enemy.position.x - enemy.bounding.x / 2,
                    enemy.position.y + enemy.bounding.y / 2
            );
            Vector2D enemyBR = new Vector2D(
                    enemy.position.x + enemy.bounding.x / 2,
                    enemy.position.y + enemy.bounding.y / 2
            );
            Vector2D enemyTL = new Vector2D(
                    enemy.position.x - enemy.bounding.x / 2,
                    enemy.position.y - enemy.bounding.y / 2
            );
            Vector2D enemyTR = new Vector2D(
                    enemy.position.x + enemy.bounding.x / 2,
                    enemy.position.y - enemy.bounding.y / 2
            );

            LineToLineDetection(originPoint, endPoint, enemyBL, enemyBR);
            LineToLineDetection(originPoint, endPoint, enemyBR, enemyTR);
            LineToLineDetection(originPoint, endPoint, enemyTR, enemyTL);
            LineToLineDetection(originPoint, endPoint, enemyTL, enemyBL);
        }


        if (timeActive > lifespan) {
            visible = false;
        }
    }


    public void Render(Canvas canvas) {
        if (!visible) { return; }

        DrawLine line = new DrawLine();
        line.Draw(originPoint, endPoint, canvas);


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
