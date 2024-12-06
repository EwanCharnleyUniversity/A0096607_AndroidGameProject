package com.example.a0096607_androidgameproject.Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class WorldTexture {
    private Bitmap bitmap;
    private final Rect size;

    // We need to initialise the texture before calling the draw function.
    public WorldTexture(TextureCache textures, int width, int height, int textureResource, Context view) {
        size = new Rect(0,0,width,height);

        bitmap = textures.getCacheResource("CROSSBOW", width, height).copy(Bitmap.Config.ARGB_4444, true);
    }

    public void Draw(Canvas view, Rect position) {
        view.drawBitmap(bitmap, size, position, null);
    }
}