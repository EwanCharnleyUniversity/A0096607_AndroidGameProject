package com.example.a0096607_androidgameproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Canvas;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.a0096607_androidgameproject.Entities.Bullet;
import com.example.a0096607_androidgameproject.Weapons.Crossbow;


public class GameView extends SurfaceView implements Runnable {

    private Thread gameThread;
    private SurfaceHolder surfaceHolder;

    private boolean playing = true;

    // Time Variables and FPS
    private int FPS = 60;
    private long TIME;
    private long TIMESINCE;

    // Heat System
    private boolean OVERHEATING = false;
    private int heat = 0;
    private int heatCapacity = 250;

    // Graphics Stuff
    private Canvas canvas;

    // Class Tests
    private Crossbow testWeapon;


    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        testWeapon = new Crossbow(context);

    }


    @Override
    public void run() {
        Log.d("GameView", "Beginning Runtime of Game");

        while (playing) {
            TIMESINCE = System.currentTimeMillis() - TIME;

            if (TIMESINCE > 1000 / FPS) {
                update();
                draw();
                TIME = System.currentTimeMillis();
            }
        }
    }

    // Handles the global heat value
    // May need to refactor later.
    public void HeatTick() {
        if (heat - 1 < 0) {
            if (heat != 0)
                heat = 0;

            return;
        }

        heat -= 1;

        if (OVERHEATING) {
            if (heat + 75.0f < heatCapacity)
            {
                OVERHEATING = false;
                return;
            }
            else {
                return;
            }
        }

        if (heat > heatCapacity) {
            OVERHEATING = true;
        }
    }


    // Updates all in-game actions based on tick speed.
    public void update() {
        HeatTick();
        if (OVERHEATING) {
            return;
        }
    }


    // Draw things to the screen
    public void draw() {
        if (surfaceHolder.getSurface().isValid())
        {
            // Background
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);

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
                testWeapon.Activate();
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