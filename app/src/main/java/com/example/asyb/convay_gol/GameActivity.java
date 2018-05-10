/**
 * Show Board, Settings-Button + Menu (Swipe-in)
 */

package com.example.asyb.convay_gol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private BoardView boardView;
    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        boardView = (BoardView) findViewById(R.id.board_view);

        //Remove Text Field (testing issues)
        testText = (TextView) findViewById(R.id.txtView);
        testText.setText("Test");

    }

    @Override
    protected void onResume() {
        super.onResume();
        boardView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        boardView.stop();
    }
}
