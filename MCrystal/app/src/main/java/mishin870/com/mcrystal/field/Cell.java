package mishin870.com.mcrystal.field;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class Cell {
    public static final int NONE = -1;
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int YELLOW = 3;
    public static final int NUM_OF_COLORS = 4;
    private int type;
    private Bitmap bmp;
    public boolean collected = false, isLockedForFallInto = false;
    public Paint paint = new Paint();
    public int offX = 0, offY = 0;
    public int x, y;

    /**
     * ячейка поля
     * @param type тип ячейки
     */
    public Cell(int type, int x, int y) {
        this.setType(type);
        this.x = x;
        this.y = y;
    }

    /**
     * установка нового типа и переустановка картинки (цвета)
     * @param type
     */
    public void setType(int type) {
        this.type = type;
        switch (this.type) {
            case RED:
                bmp = MCResources.red;
                break;

            case GREEN:
                bmp = MCResources.green;
                break;

            case BLUE:
                bmp = MCResources.blue;
                break;

            case YELLOW:
                bmp = MCResources.yellow;
                break;

            default:
                bmp = MCResources.none;
                break;
        }
    }

    /**
     * отрисовка ячейки на поле
     * @param canvas
     * @param x
     * @param y
     */
    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(bmp, x, y, paint);
    }

    public int getType() {
        return this.type;
    }
}
