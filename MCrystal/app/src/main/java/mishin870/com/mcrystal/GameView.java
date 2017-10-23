package mishin870.com.mcrystal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import mishin870.com.mcrystal.anim.AnimationManager;
import mishin870.com.mcrystal.anim.HideAnimation;
import mishin870.com.mcrystal.field.Cell;
import mishin870.com.mcrystal.field.Cursor;
import mishin870.com.mcrystal.field.Field;
import mishin870.com.mcrystal.field.MCResources;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class GameView extends View {
    private Paint blackPaint;
    private Field gameField;
    //private Cursor cursor;
    private AnimationManager animationManager;
    private Thread animationManagerThread;
    private int score;

    public GameView(Context context) {
        super(context);

        blackPaint = new Paint();
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setTextSize(50);

        MCResources.init(this.getResources());
        gameField = new Field(5, 5);
        gameField.randomize();
        //cursor = new Cursor();

        animationManager = new AnimationManager(this);
        animationManagerThread = new Thread(animationManager);
        animationManagerThread.start();

        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onClick(event.getAction(), event.getX(), event.getY());
            }
        });
    }

    /**
     * главный хандлер клика
     * @param action тип клика
     * @param cx
     * @param cy
     * @return true
     */
    private boolean onClick(int action, float cx, float cy) {
        if (action == MotionEvent.ACTION_UP) {
            if (!animationManager.isEmpty()) return true;
            int x = (int) ((cx - Field.X_OFFSET) / (MCResources.TILE_WIDTH + Field.X_SPACING));
            int y = (int) ((cy - Field.Y_OFFSET) / (MCResources.TILE_HEIGHT + Field.Y_SPACING));

            Cell cell = gameField.getCell(x, y);
            if (cell != null) {
                gameField.clearCollected();
                ArrayList<Cell> cells = gameField.collect(cell, x, y);
                if (cells.size() >= 2) {
                    int scoreAdd = 0;
                    int additiveScoreAdd = 0;
                    for (Cell current : cells) {
                        animationManager.addAnimation(new HideAnimation(current));
                        scoreAdd += GameConfig.SCORE_BY_CELL;
                        additiveScoreAdd += GameConfig.ADDITIVE_SCORE;
                        scoreAdd += additiveScoreAdd;
                    }
                    this.score += scoreAdd;
                }
            }
            /*int oldX = cursor.x;
            int oldY = cursor.y;
            if (cursor.toggle(x, y)) {
                gameField.swap(x, y, oldX, oldY);
            }*/
            this.invalidate();
        }
        return true;
    }

    /**
     * main draw function
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        gameField.draw(canvas);
        canvas.drawText(String.valueOf(score), 10, canvas.getHeight() - 10 - 30, blackPaint);
        /*if (cursor.captured) {
            int x = cursor.x * (MCResources.TILE_WIDTH + Field.X_SPACING) + Field.X_OFFSET;
            int y = cursor.y * (MCResources.TILE_HEIGHT + Field.Y_SPACING) + Field.Y_OFFSET;
            canvas.drawRect(x, y, x + MCResources.TILE_WIDTH, y + MCResources.TILE_HEIGHT, blackPaint);
        }*/
    }

}