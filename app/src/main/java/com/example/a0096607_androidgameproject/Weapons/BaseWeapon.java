package com.example.a0096607_androidgameproject.Weapons;

import android.util.Log;



public class BaseWeapon {
    // Ammunition and Clip
    public int ammoCurrent, ammoMax, clipCurrent, clipMax;

    // When the weapon is activated, we check its ammo
    public void Activate() {
        if (clipCurrent <= 0) {
            if (ammoCurrent <= 0) {
                Log.d("BaseWeapon", "Ammo is exhausted! Cannot reload!");
                return;
            }

            Log.d("BaseWeapon", "Clip has been exhausted! Reloading weapon.");

            ReloadWeapon();
        }

        FireWeapon();
        clipCurrent--;
    }

    // Actual firing logic, we'll override this on a weapon per weapon basis.
    public void FireWeapon() {
        Log.d("BaseWeapon", "Fired!");
    }

    // Reload the clip with active Ammunition.
    public void ReloadWeapon() {
        if (clipMax >= ammoCurrent) {
            clipCurrent += ammoCurrent;
            ammoCurrent = 0;
        }
        else
        {
            clipCurrent = clipMax;
            ammoCurrent -= clipMax;
        }
    }
}

