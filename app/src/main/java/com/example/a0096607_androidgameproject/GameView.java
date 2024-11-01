package com.example.a0096607_androidgameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    private float heat = 0.0f;
    private float heatCapacity = 250.0f;

    // Graphics Stuff
    private Canvas canvas;
    private Bitmap bitmap;
    private Rect frameToDraw = new Rect(0,0,250,250);
    private RectF whereToDraw = new RectF(0, 0, 250, 250);

    // Weapon testing
    private Crossbow testWeapon;



    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.coffee);
        bitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, false);
        testWeapon = new Crossbow();
    }


    @Override
    public void run() {
        Log.d("GameView", "Beginning Runtime of Game");

        while (playing){
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
        if (heat - 0.1 < 0) {
            if (heat != 0)
                heat = 0;

            return;
        }

        heat -= 0.1f;

        if (OVERHEATING) {
            if (heat + 75.0f < heatCapacity)
            {
                OVERHEATING = false;
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
            //Log.d("GameView", "You are overheating! Heat is at: " + heat);
            return;
        }

        //Log.d("GameView", "Heat: " + heat);
    }


    // Draw things to the screen
    public void draw() {
        if (surfaceHolder.getSurface().isValid())
        {
            // Background
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);

            // Test Sprite
            whereToDraw.set(0, 0, 500, 500);
            canvas.drawBitmap(bitmap, frameToDraw, whereToDraw, null);
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
                Log.d("GameView_Weapon", "Crossbow's current clip is: " + testWeapon.clipCurrent);
                Log.d("GameView_Weapon", "Crossbow's current ammo is: " + testWeapon.ammoCurrent);
                testWeapon.Activate();

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