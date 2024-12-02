package com.example.a0096607_androidgameproject.Mechanics;

import android.util.Log;


// Handles player stats such as lives, cash, points, and heat.
public class BaseManager {
    public int health = 200;
    public int score = 0;
    public float money = 0;

    public HeatManager heat;


    public BaseManager() {
        heat = new HeatManager();
    }


    public void Simulate() {
        heat.Simulate();
        //Log.d("Base", String.valueOf(heat.heatStored));
    }


    public void UpdateHealth(int Health) {
        health += Health;
    }

    public void UpdateScore(int Score) {
        score += Score;
    }

    public void UpdateMoney(int Money) {
        score += Money;
    }
}
