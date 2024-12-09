package com.example.a0096607_androidgameproject.Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Log;
import android.util.LruCache;

import com.example.a0096607_androidgameproject.R;

import java.util.HashMap;
import java.util.Map;


// Preloads textures at the start of runtime, enabling us to simply copy the bitmaps rather than decode them with every new Entity/Object.
public class TextureCache {

    LruCache<String, Bitmap> textureCache;

    // Stores textures and their related R.Drawable resources. We need this to check for texture IDs dynamically.
    enum Textures {
        ERROR(R.drawable.error),
        CROSSBOW(R.drawable.coffee),
        ENEMY(R.drawable.kerfus),
        BULLET(R.drawable.pea);

        // Assign texture drawable IDs to the TEXTURE_ID so we can grab them as actual reference later on.
        private static final Map<Textures, Integer> TEXTURE_ID = new HashMap<>();
        static {
            for (Textures tex : values()) {
                TEXTURE_ID.put(tex, tex.drawable);
            }
        }

        public final int drawable;
        Textures(int drawable) {
            this.drawable = drawable;
        }


        public static Integer textureResource(Textures id) {
            return TEXTURE_ID.get(id);
        }
    }

    public TextureCache(Context view) {

        // Create the cache size with a RAM allocation of Two Megabytes.
        int cacheSize = 2 * 1024 * 1024;
        textureCache = new LruCache<String, Bitmap>(cacheSize);

        // Iterate through the Textures Enum and initialise the resources found there.
        for (Textures id : Textures.values()) {
            Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), Textures.textureResource(id));
            //bitmap = Bitmap.createScaledBitmap(bitmap, 100,100, false);
            textureCache.put(id.name(), bitmap);
        }
    }

    public Bitmap getCacheResource(String id, int width, int height) {
        // Error Texture if we find no stored cache textures.
        if (textureCache.get(id) == null) {
            return Bitmap.createScaledBitmap(textureCache.get("ERROR"), width, height,false);
        }

        return Bitmap.createScaledBitmap(textureCache.get(id), width, height,false);
    }

    public void printCacheKey() {
        for (String key : textureCache.snapshot().keySet()) {
            Log.d("Texture Cache Key", key);
        }
    }
}
