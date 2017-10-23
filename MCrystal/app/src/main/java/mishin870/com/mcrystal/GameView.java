package mishin870.com.mcrystal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import mishin870.com.mcrystal.field.MCResources;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class GameView extends View {
    private Bitmap red, green, blue, yellow;

    public GameView(Context context) {
        super(context);

        MCResources.init(this.getResources());

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
            //click
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
    }

}