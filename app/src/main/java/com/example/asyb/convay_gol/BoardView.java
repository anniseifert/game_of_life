package com.example.asyb.convay_gol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by anney on 06.05.2018.
 */

public class BoardView extends SurfaceView implements Runnable {

    //Default size of a cell
    //TODO create custom size
    public static final int DEFAULT_SIZE = 50;

    //TODO create color sets
    //Default color of an alive color
    public static final int DEFAULT_ALIVE_COLOR = Color.WHITE;
    //Default color of a dead color
    public static final int DEFAULT_DEAD_COLOR = Color.BLACK;
    //default color of text
    public static final int DEFAULT_TEXT_COLOR = Color.WHITE;

    //Thread which will be responsible to manage the evolution of the board
    private Thread thread;
    //Boolean indicating if the Bord is evolving or not
    private boolean isRunning = false;
    private int columnWidth = 1;
    private int rowHeight = 1;
    private int numberofColumns = 1;
    private int numberofRows = 1;
    private Board board;
    private int generationCount = 0;

    //a Rectangle instance and a Paint instance used to draw the cells
    private Rect rect = new Rect();
    private Paint paint = new Paint();

    public BoardView(Context context) {
        super(context);
        initBoard();
    }

    public BoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initBoard();
    }


    @Override
    public void run() {
        //while the board is evolving
        while (isRunning) {
            if (getHolder().getSurface().isValid()) {
                //Pause of 300ms to better visualize the evolution TODO make time variable for fast forward
                try {
                  Thread.sleep(300);
                } catch (InterruptedException e) {

                }

                Canvas canvas = getHolder().lockCanvas();
                board.nextGeneration();
                generationCount++;
                drawCells(canvas);
                drawGenerationCount(canvas);
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    public void resume() {
        //board is evolving
        isRunning = true;
        thread = new Thread(this);
        //we start the Thread for the boards evolutions
        thread.start();
    }

    public void pause() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            //TODO
        }
    }

    private void initBoard() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        //we calculate the number of columns and rows for our board
        numberofColumns = point.x / DEFAULT_SIZE;
        numberofRows = point.y / DEFAULT_SIZE;

        // we calculate the column width and row height
        columnWidth = point.x / numberofColumns;
        rowHeight = point.y / numberofRows;
        board = new Board(numberofColumns, numberofRows);
    }

    //Method to draw each cell of the board on the canvas
    private void drawCells(Canvas canvas) {
        for(int i = 0; i < numberofColumns; i++) {
            for (int j = 0; j < numberofRows; j++) {
                Cell cell = board.getCell(i, j);
                rect.set((cell.x * columnWidth) - 1, (cell.y * rowHeight) -1,
                        (cell.x * columnWidth + columnWidth) -1,
                        (cell.y * rowHeight + rowHeight) -1);

                //we change the color according the alive status of the cell
                paint.setColor(cell.alive ? DEFAULT_ALIVE_COLOR : DEFAULT_DEAD_COLOR);
                canvas.drawRect(rect, paint);
            }
        }
    }

    // method to draw the number of the current generation
    private void drawGenerationCount(Canvas canvas) {
        paint.setColor(DEFAULT_TEXT_COLOR);
        //TODO only do once
        paint.setTextSize(rowHeight * 1.5f);
        canvas.drawText("Generation" + String.valueOf(generationCount), columnWidth, paint.getTextSize() + columnWidth, paint);
    }

    //We let the user to interact with the cells
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //get touch coordinates an convert them to board coordinates
            int i = (int) (event.getX() / columnWidth);
            int j = (int) (event.getY() / rowHeight);

            //get the touched cell
            Cell cell = board.getCell(i,j);
            //change touched Cell
            cell.invert();

            invalidate();
        }
        return super.onTouchEvent(event);
    }

    /* TODO maybe use this method https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-2-p-create-a-surfaceview/11-2-p-create-a-surfaceview.html
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
    */
}
