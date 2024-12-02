package com.example.a0096607_androidgameproject.Mechanics;

import android.util.Log;


// A generalised manager for the games heat mechanic.
public class HeatManager {
    public boolean OVERHEATING = false;

    public float heatStored = 0.f;
    public float heatCapacity = 100.f;
    public float heatLoss = 0.25f;


    public HeatManager() {}


    // An overhead to give overheating more time to remain active.
    // Overhead is a fouth of the capacity, might change to a hard value.
    private float Overhead() {
        return heatCapacity / 4;
    }

    private void HeatTick() {
        heatStored -= heatLoss;

        if (heatStored < 0) {
            heatStored = 0.f;
        }

        if (OVERHEATING && heatStored + Overhead() < heatCapacity) {
            OVERHEATING = false;
        }

        if (heatStored >= heatCapacity) {
            OVERHEATING = true;
        }

        //Log.d("Heat Manager", "Heat Storage at: " + heatStored);
    }


    public void Simulate() {
        this.HeatTick();
    }


    public void DumpHeat(float heat) {
        heatStored += heat;
    }
}
