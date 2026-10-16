package com.example.helloandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CustomGameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new CustomGameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (gameView != null) {
            gameView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (gameView != null) {
            gameView.pause();
        }
    }
}