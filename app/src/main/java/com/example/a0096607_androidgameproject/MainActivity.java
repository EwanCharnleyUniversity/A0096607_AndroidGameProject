
package com.example.a0096607_androidgameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


// If App is not found
// do in terminal: \rm -r .android/
public class MainActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);
        //Log.d("MainActivity", "Initialised");
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
        //Log.d("MainActivity", "Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();

        gameView.pause();
        //Log.d("MainActivity", "Paused");
    }
}