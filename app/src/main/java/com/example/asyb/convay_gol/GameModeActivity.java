package com.example.asyb.convay_gol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameModeActivity extends AppCompatActivity {

    Button storyModeButton;
    Button normalModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        setupButtons();
    }

    private void setupButtons() {
        normalModeButton = (Button) findViewById(R.id.normalModeButton);
        storyModeButton = (Button) findViewById(R.id.storyModeButton);

        normalModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameModeActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        storyModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameModeActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
    }
}
