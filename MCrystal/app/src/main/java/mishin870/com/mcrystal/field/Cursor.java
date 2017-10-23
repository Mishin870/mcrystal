package mishin870.com.mcrystal.field;

/**
 * Created by Mishin870 on 23.10.2017.
 */

public class Cursor {
    public int x, y;
    public boolean captured;

    /**
     * виртуальный игровой курсор
     */
    public Cursor() {
        x = -1;
        y = -1;
        captured = false;
    }

    /**
     * захват ячейки курсором
     * @param x
     * @param y
     */
    public void capture(int x, int y) {
        this.x = x;
        this.y = y;
        this.captured = true;
    }

    /**
     * высвобождение ячейки
     */
    public void release() {
        this.captured = false;
    }

    /**
     * смена состояния на противоположное
     * @param x
     * @param y
     * @return смена ли это состояния
     */
    public boolean toggle(int x, int y) {
        if (this.captured) {
            this.release();
            return true;
        } else {
            this.capture(x, y);
            return false;
        }
    }

}