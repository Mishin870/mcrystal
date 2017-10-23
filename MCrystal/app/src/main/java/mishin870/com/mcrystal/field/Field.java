package mishin870.com.mcrystal.field;

import android.graphics.Canvas;
import java.util.Random;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class Field {
    private static final int X_SPACING = 10;
    private static final int Y_SPACING = 10;
    private static final int X_OFFSET = 10;
    private static final int Y_OFFSET = 10;
    private Cell[][] field;
    private int width, height;

    /**
     * игровое поле
     * @param width
     * @param height
     */
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[width][height];
        initField();
    }

    /**
     * инициализирует поле
     */
    private void initField() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y] = new Cell(Cell.RED);
            }
        }
    }

    /**
     * заполняет поле случайными ячейками
     */
    public void randomize() {
        Random r = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y].setType(r.nextInt(Cell.NUM_OF_COLORS));
            }
        }
    }

    /**
     * рисует поле на канвасе
     * @param canvas
     */
    public void draw(Canvas canvas) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y].draw(canvas,
                        X_OFFSET + x * (Cell.WIDTH + X_SPACING),
                        Y_OFFSET + y * (Cell.HEIGHT + Y_SPACING)
                );
            }
        }
    }

}