package com.example.a0096607_androidgameproject.Weapons;

import android.util.Log;

public class Crossbow extends BaseWeapon {

    public Crossbow() {
        ammoMax = 12;
        clipMax = 3;
        ammoCurrent = ammoMax;
        clipCurrent = clipMax;
        Log.d("Crossbow","Constructed!");
    }
}
