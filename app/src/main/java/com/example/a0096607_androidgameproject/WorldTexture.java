package com.example.a0096607_androidgameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;


public class WorldTexture {
    private Bitmap bitmap;
    Rect size;

    // We need to initialise the texture before calling the draw function.
    public WorldTexture(int width, int height, int textureResource, Context view) {
        size = new Rect(0,0,width,height);
        bitmap = BitmapFactory.decodeResource(view.getResources(), textureResource);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    public void Draw(Canvas view, Rect position) {
        view.drawBitmap(bitmap, size, position, null);
    }
}