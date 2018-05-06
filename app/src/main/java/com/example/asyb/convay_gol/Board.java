package com.example.asyb.convay_gol;

import java.util.Random;

/**
 * Created by anney on 06.05.2018.
 */

public class Board {

    public static final Random Rand = new Random();
    //static = is initialized at the beginning with the class
    //final = cannot be rewritten
    public int width, height;
    private Cell[][] board;

    public Board(int width, int height) {
        this.width = width;

    }
}
