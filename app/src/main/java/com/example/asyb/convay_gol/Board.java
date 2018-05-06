package com.example.asyb.convay_gol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by anney on 06.05.2018.
 */

public class Board {

    public static final Random Rand = new Random();
    ///static = is initialized at the beginning with the class
    ///final = cannot be rewritten
    public int width, height;
    private Cell[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new Cell[width][height];
        init();
    }

    private void init() {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j< height; j++) {
                board[i][j] = new Cell(i, j, false);
            }
        }
    }

    public Cell getCell(int i, int j) {
        return board[i][j];
    }

    public int countNeighboursOf(int i, int j) {
        int neighbours = 0;

        for(int k = i-1; k <= i+1; k++) {
            for (int l = j-1; l <= j+1; l++) {
                //TODO ignore the borders
                if((k != 1 || l !=j) && k >= 0 && k < width && 1 >= 0 && 1 < height) {
                    Cell cell = board[k][l];

                    if (cell.alive) {
                        neighbours++;
                    }
                }
            }
        }

        return neighbours;
    }

    public void nextGeneration() {
        List<Cell> liveCells = new ArrayList<Cell>();
        List<Cell> deadCells = new ArrayList<Cell>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                Cell cell = board[i][j];

                int neighbours = countNeighboursOf(cell.x, cell.y);

                /*
                1. Any live cell with fewer than two live neighbors dies,
                 as if caused by underpopulation.

                2. Any live cell with two or three live neighbors
                 lives on to the next generation.

                3. Any live cell with more than three live neighbors dies,
                 as if by overpopulation.

                4. Any dead cell with exactly three live neighbors
                 becomes a live cell, as if by reproduction.

                 (rules from tutorial == rules from competition)

                 */

                //rule 1 & 3
                if(cell.alive &&
                        (neighbours < 2 || neighbours > 3)) {
                    deadCells.add(cell);
                }

                //rule 2 & rule 4
                if ((cell.alive &&
                        (neighbours  == 3 || neighbours == 2)) ||
                        (!cell.alive && neighbours == 3)) {
                    liveCells.add(cell);
                }
            }
        }

        //update future live an dead cells
        for(Cell cell : liveCells) {
            ///foreach: iterate the List liveCells with Cell-Objects and name the recent object 'cell'
            cell.live();
        }

        for (Cell cell : deadCells) {
            cell.die();
        }
    }
}
