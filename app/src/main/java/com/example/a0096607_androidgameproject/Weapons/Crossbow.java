package com.example.a0096607_androidgameproject.Weapons;

import android.content.Context;
import android.util.Log;


public class Crossbow extends BaseWeapon {

    public Crossbow(Context context) {
        super(context);
        clipMax = 3;
        clipCurrent = clipMax;
        Log.d("Crossbow","Constructed!");
    }

    @Override
    public void FireWeapon(Context context) {
        Log.d("Crossbow","has fired!");
    }

    @Override
    public void Simulate(long deltaTime) {
        super.Simulate(deltaTime);
    }
}
