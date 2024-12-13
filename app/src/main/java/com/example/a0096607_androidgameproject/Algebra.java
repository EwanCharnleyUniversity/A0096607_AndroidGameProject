package com.example.a0096607_androidgameproject;

import android.util.Log;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


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

    public static Vector2D VectorAddition(Vector2D lhs, Vector2D rhs) {
        return new Vector2D(lhs.x + rhs.x, lhs.y + rhs.y);
    }

    public static Vector2D VectorSubtraction(Vector2D lhs, Vector2D rhs) {
        return new Vector2D(lhs.x - rhs.x, lhs.y - rhs.y);
    }

    // https://paulbourke.net/geometry/pointlineplane/
    public static void LineToLineDetection(Vector2D parentOrigin, Vector2D parentEnd, Vector2D targetOrigin, Vector2D targetEnd) {
        // Two lines, Parent and Target.
        // To find the point where Parent equals Target.
        // parentOrigin  - X1 Y1
        // parentEnd     - X2 Y2
        // targetOrigin  - X3 Y3
        // targetEnd     - X4 Y4

        if (parentEnd.x - parentOrigin.x == 0 || parentEnd.y - parentOrigin.y == 0 || targetEnd.x - targetOrigin.x == 0 || targetEnd.y - targetOrigin.y == 0) {
            Log.d("Algebra", "Warning! One Line is completely flat in one axis!");
        }

        float division = ((targetEnd.y - targetOrigin.y) * (parentEnd.x - parentOrigin.x)) - ((targetEnd.x - targetOrigin.x) * (parentEnd.y - parentOrigin.y));

        float ua = ((targetEnd.x - targetOrigin.x) * (parentOrigin.y - targetOrigin.y)) - ((targetEnd.y - targetOrigin.y) * (parentOrigin.x - targetOrigin.x)) / division;
        float ub = ((parentEnd.x - parentOrigin.x) * (parentOrigin.y - targetOrigin.y)) - ((parentEnd.y - parentOrigin.y) * (parentOrigin.x - targetOrigin.x)) / division;
    }
}
