package mishin870.com.mcrystal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import mishin870.com.mcrystal.field.Field;
import mishin870.com.mcrystal.field.MCResources;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class GameView extends View {
    private Field gameField;

    public GameView(Context context) {
        super(context);

        MCResources.init(this.getResources());
        gameField = new Field(5, 5);

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
     * @param x
     * @param y
     * @return true
     */
    private boolean onClick(int action, float x, float y) {
        if (action == MotionEvent.ACTION_DOWN) {
            gameField.randomize();
        }
        this.invalidate();
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
    }

}