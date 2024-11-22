package com.example.a0096607_androidgameproject;


// Vector2D alleviates calculations relating to worldspace position.
public class Vector2D {
    public float X, Y;

    public Vector2D(float xCoord, float yCoord) {
        X = xCoord;
        Y = yCoord;
    }

    public void Addition(Vector2D input) {
        this.X += input.X;
        this.Y += input.Y;
    }
}
