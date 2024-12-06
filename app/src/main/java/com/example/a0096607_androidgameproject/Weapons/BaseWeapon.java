package com.example.a0096607_androidgameproject.Weapons;

import android.content.Context;
import android.util.Log;

import com.example.a0096607_androidgameproject.Entities.Entity;
import com.example.a0096607_androidgameproject.Graphics.TextureCache;
import com.example.a0096607_androidgameproject.R;
import com.example.a0096607_androidgameproject.Vector2D;

import java.util.Random;


public abstract class BaseWeapon extends Entity {
    // Ammunition and Clip
    public int clipCurrent, clipMax;

    public BaseWeapon(TextureCache textures, Context context) {
        super(textures, context, new Vector2D(250,250), R.drawable.coffee);
        position.x = new Random().nextInt(1000);
        position.y = 2000;
    }

    // When the weapon is activated, we check its ammo
    public void Activate() {
        if (clipCurrent <= 0) {
            Log.d("BaseWeapon", "Clip has been exhausted! Reloading weapon.");
            ReloadWeapon();

        } else {
            FireWeapon();
            clipCurrent--;
        }
    }

    // Actual firing logic, we'll override this on a weapon per weapon basis.
    public void FireWeapon() {
        Log.d("BaseWeapon", "Fired!");
    }

    // Reload the clip with active Ammunition.
    public void ReloadWeapon() {
        clipCurrent = clipMax;
    }

    @Override
    public void Simulate(Vector2D userPosition) {
        position.x = userPosition.x;
    }

    public abstract void FireWeapon(Context context);
}

