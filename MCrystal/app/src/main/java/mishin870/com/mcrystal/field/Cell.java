package mishin870.com.mcrystal.field;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Mishin870 on 23.10.2017.
 * ячейка поля
 */
public class Cell {
    private static final Paint cellPaint = new Paint();
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int YELLOW = 3;
    public static final int NUM_OF_COLORS = 4;
    private int type;
    private Bitmap bmp;

    public Cell(int type) {
        this.setType(type);
    }

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
                bmp = MCResources.red;
                break;
        }
    }

    public void draw(Canvas c, int x, int y) {
        c.drawBitmap(bmp, x, y, cellPaint);
    }

}
