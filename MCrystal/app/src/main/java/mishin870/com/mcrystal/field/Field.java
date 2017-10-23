package mishin870.com.mcrystal.field;

import android.graphics.Canvas;
import java.util.Random;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class Field {
    public static final int X_SPACING = 5;
    public static final int Y_SPACING = 5;
    public static final int X_OFFSET = 10;
    public static final int Y_OFFSET = 10;
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
     * получить ячейку по координатам на поле
     * @param x
     * @param y
     * @return
     */
    public Cell getCell(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height ? field[x][y] : null;
    }

    /**
     * поменять местами две ячейки
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void swap(int x1, int y1, int x2, int y2) {
        Cell c1 = getCell(x1, y1);
        Cell c2 = getCell(x2, y2);
        int type1 = c1.getType();
        int type2 = c2.getType();
        c1.setType(type1);
        c2.setType(type2);
    }

    /**
     * рисует поле на канвасе
     * @param canvas
     */
    public void draw(Canvas canvas) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y].draw(canvas,
                        X_OFFSET + x * (MCResources.TILE_WIDTH + X_SPACING),
                        Y_OFFSET + y * (MCResources.TILE_HEIGHT + Y_SPACING)
                );
            }
        }
    }

}