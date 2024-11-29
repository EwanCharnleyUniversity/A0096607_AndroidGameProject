package com.example.a0096607_androidgameproject;


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
