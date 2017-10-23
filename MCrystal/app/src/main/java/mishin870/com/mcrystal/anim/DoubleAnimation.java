package mishin870.com.mcrystal.anim;

import mishin870.com.mcrystal.field.Cell;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class DoubleAnimation implements IAnimation {
    public Cell cell1, cell2;

    /**
     * база для всех анимаций двух ячеек
     * @param cell1
     * @param cell2
     */
    public DoubleAnimation(Cell cell1, Cell cell2) {
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    @Override
    public boolean play() {
        return true;
    }

}
