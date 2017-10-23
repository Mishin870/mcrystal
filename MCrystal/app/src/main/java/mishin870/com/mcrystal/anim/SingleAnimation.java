package mishin870.com.mcrystal.anim;

import mishin870.com.mcrystal.field.Cell;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class SingleAnimation implements IAnimation {
    public Cell cell;

    /**
     * база для анимаций одиночных ячеек
     * @param cell
     */
    public SingleAnimation(Cell cell) {
        this.cell = cell;
    }

    @Override
    public boolean play() {
        return true;
    }

}
