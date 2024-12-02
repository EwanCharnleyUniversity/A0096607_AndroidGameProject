package com.example.a0096607_androidgameproject;


import android.util.Log;

// Vector2D alleviates calculations relating to worldspace position.
public class Vector2D {
    public float x, y;

    public Vector2D(float xCoordinate, float yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
    }

    public float Magnitude() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    private double Degrees(double degrees) {
        return degrees * Math.PI / 180;
    }

    public void Rotate(double degrees) {
        float tempX = ((float)Math.cos(Degrees(degrees)) * x) - ((float)Math.sin(Degrees(degrees)) * y);
        float tempY = ((float)Math.sin(Degrees(degrees)) * x) + ((float)Math.cos(Degrees(degrees)) * y);
        x = tempX;
        y = tempY;
    }

    // Operators
    public void Addition(Vector2D rhs) {
        this.x += rhs.x;
        this.y += rhs.y;
    }

    public void Subtraction(Vector2D rhs) {
        this.x -= rhs.x;
        this.y -= rhs.y;
    }

    public void Multiplication(Vector2D rhs) {
        this.x *= rhs.x;
        this.y *= rhs.y;
    }

    public void Division(Vector2D rhs) {
        this.x /= rhs.x;
        this.y /= rhs.y;
    }

    public void ScalarMultiplier(float rhs) {
        this.x *= rhs;
        this.y *= rhs;
    }
}
