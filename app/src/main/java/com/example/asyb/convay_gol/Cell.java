package com.example.asyb.convay_gol;

/**
 * Created by anney on 06.05.2018.
 */

public class Cell {

    public int x,y;
    public boolean alive;

    public Cell(int x,int y, boolean alive) {
        this.x = x;
        this.y = y;

        this.alive=alive;
    }

    public void die() {
        alive= false;
    }

    public void live() {
        alive=true;
    }

    public void invert() {
        alive = !alive;
    }
}
