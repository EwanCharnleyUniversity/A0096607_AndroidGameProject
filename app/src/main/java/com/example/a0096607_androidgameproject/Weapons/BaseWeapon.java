package com.example.a0096607_androidgameproject.Weapons;

import android.content.Context;
import android.util.Log;

import com.example.a0096607_androidgameproject.Entities.Entity;
import com.example.a0096607_androidgameproject.Vector2D;


public abstract class BaseWeapon extends Entity {
    // Ammunition and Clip
    public int clipCurrent, clipMax;

    public BaseWeapon(Context context) {
        super(context);
        position.Y = 1700;
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
        Log.d("BaseWeapon", "User Input detected!");
        position.X = userPosition.X - (bounding.X / 2);
    }

    public abstract void FireWeapon(Context context);
}

