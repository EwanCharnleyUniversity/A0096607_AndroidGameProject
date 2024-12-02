package com.example.a0096607_androidgameproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Canvas;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.a0096607_androidgameproject.Entities.EnemyManager;
import com.example.a0096607_androidgameproject.Mechanics.BaseManager;
import com.example.a0096607_androidgameproject.Weapons.BulletManager;
import com.example.a0096607_androidgameproject.Weapons.Crossbow;


public class GameView extends SurfaceView implements Runnable {

    private Thread gameThread;
    private SurfaceHolder surfaceHolder;

    private boolean playing = true;

    // Time Variables and FPS
    private final int FPS = 60;
    private long clock;
    private long deltaTime;

    // Graphics Stuff
    private Canvas canvas;

    // Managers
    BaseManager base;
    BulletManager bullets;
    EnemyManager enemies;

    // Class Tests
    private Crossbow testWeapon;


    public GameView(Context context) {
        super(context);

        base = new BaseManager();
        bullets = new BulletManager();
        enemies = new EnemyManager();

        surfaceHolder = getHolder();
        testWeapon = new Crossbow(context);
    }


    @Override
    public void run() {
        Log.d("GameView", "Beginning Runtime of Game");

        while (playing) {
            deltaTime = System.currentTimeMillis() - clock;

            if (deltaTime > 1000 / FPS) {
                update();
                draw();
                enemies.Spawner(getContext(), deltaTime);
                bullets.SimulateBullets(deltaTime, enemies);
                clock = System.currentTimeMillis();
            }
        }
    }


    // Updates all in-game actions based on tick speed.
    public void update() {
        base.Simulate();

        if (base.heat.OVERHEATING) {
            Log.d("Gameview","You are Overheating!");
            return;
        }

        enemies.SimulateEnemies(deltaTime);
    }


    // Draw things to the screen
    public void draw() {
        if (surfaceHolder.getSurface().isValid())
        {
            // Background
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);

            bullets.RenderBullets(canvas);
            enemies.RenderEnemies(canvas);

            testWeapon.Draw(canvas);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


    // When the view is not being run.
    public void pause() {
        playing = false;

        try {
            // Attempt to rejoin with the Main Activity
            Log.d("GameView", "Paused");
            gameThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Resume the View
    public void resume() {
        playing = true;
        gameThread = new Thread(this);

        Log.d("GameView", "Resumed");

        gameThread.start();
    }



    // Basic Touch Input.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                if (base.heat.OVERHEATING) {
                    break;
                }

                //testWeapon.Activate();
                bullets.SpawnBullet(testWeapon.position);
                //heatManager.DumpHeat(15f);

                break;

            case MotionEvent.ACTION_MOVE:
                testWeapon.Simulate(new Vector2D(event.getRawX(),event.getRawY()));

                break;
        }
        return true;
    }
}


/* Thread of main game
* - Run activity
* - Resume activity
* - Pause activity
*
* contains all of the stuff such as global variables, entities, sprites, etc.
* Main thread deals with user input and other assorted communication to the game thread.
* Otherwise game thread is running by sheer inertia from whatever functions and classes are contained within it.
* */