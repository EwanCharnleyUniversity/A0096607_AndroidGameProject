package com.example.a0096607_androidgameproject;

import java.util.Random;



public class Algebra {
    // Reverses the input degrees and converts it into true sin/cos/tan format.
    private static double Degrees(double degrees) {
        return degrees * Math.PI / 180;
    }

    // Rotates a vector by a specified Degrees.
    public static Vector2D VectorRotation(Vector2D parent, double degrees) {
        return new Vector2D(
                (float)Math.cos(Degrees(degrees)) * parent.x - (float)Math.sin(Degrees(degrees)) * parent.y,
                (float)Math.sin(Degrees(degrees)) * parent.x + (float)Math.cos(Degrees(degrees)) * parent.y
        );
    }

    // Rotates a vector randomly within a selected range.
    public static Vector2D VectorRotationInRange(Vector2D parent, double range) {
        double randomRange = range * new Random().nextDouble() - range / 2;
        return new Vector2D(
                (float)Math.cos(Degrees(randomRange)) * parent.x - (float)Math.sin(Degrees(randomRange)) * parent.y,
                (float)Math.sin(Degrees(randomRange)) * parent.x + (float)Math.cos(Degrees(randomRange)) * parent.y
        );
    }
}
