/**
 * Show Board, Settings-Button + Menu (Swipe-in)
 */

package com.example.asyb.convay_gol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    private BoardView boardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        boardView = (BoardView) findViewById(R.id.board_view);

    }

    @Override
    protected void onResume() {
        super.onResume();
        boardView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        boardView.start();
    }
}
