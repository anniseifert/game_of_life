package com.example.asyb.convay_gol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GameModeActivity extends AppCompatActivity {

    Button storyModeButton;
    Button normalModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        storyModeButton = (Button) findViewById(R.id.storyModeButton);
        normalModeButton = (Button) findViewById(R.id.normalModeButton);
    }
}
