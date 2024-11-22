package com.example.a0096607_androidgameproject.Weapons;

import android.content.Context;

import java.util.ArrayList;
import com.example.a0096607_androidgameproject.Entities.Bullet;


// We need Bullet Manager to handle all bullets on scene, increasing performance by ensuring there aren't too many
// bullets active at once. Such as reusing the oldest bullets rather than generating new ones.
public class BulletManager {
    ArrayList<Bullet> bullets;

    public BulletManager() {}

    public void SpawnBullet(Context context, float positionX) {
        if (bullets.size() > 255) {
            // If it exceeds the max, we take the first bullet of the list and reuse it.
        } else {
            bullets.add(new Bullet(context));
        }
    }


    public void SimulateBullets() {
        for(Bullet target : bullets) {
            target.Simulate();
        }
    }
}
