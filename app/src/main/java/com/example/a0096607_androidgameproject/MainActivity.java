
package com.example.a0096607_androidgameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


// If App is not found
// do in terminal: \rm -r .android/
public class MainActivity extends AppCompatActivity {
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("MainActivity", "onCreate initialised");
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("MainActivity", "Resumed");
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("MainActivity", "Paused");
        gameView.pause();
    }
}