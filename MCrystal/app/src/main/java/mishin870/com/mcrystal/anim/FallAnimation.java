package mishin870.com.mcrystal.anim;

import java.util.ArrayList;

import mishin870.com.mcrystal.GameView;
import mishin870.com.mcrystal.field.Cell;
import mishin870.com.mcrystal.field.Field;
import mishin870.com.mcrystal.field.MCResources;

/**
 * Created by Mishin870 on 24.10.2017.
 */

public class FallAnimation extends SingleAnimation {
    private static final int ANIM_SPEED = 5;
    private int sourceX, sourceY, len;

    /**
     * анимация падения ячейки на 1 вниз
     * @param cell
     * @param sourceX
     * @param sourceY
     */
    public FallAnimation(Cell cell, Field gameField, int sourceX, int sourceY) {
        super(cell);
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        gameField.getCell(this.sourceX, this.sourceY + 1).isLockedForFallInto = true;
    }

    /**
     * блокирую ли я ячейку по данным координатам?
     * @param x
     * @param y
     * @return
     */
    public boolean isLocked(int x, int y) {
        return (this.sourceX == x) && (this.sourceY + 1 == y);
    }

    /**
     * заблокирована ли кем-то из массива ячейка по данным координатам?
     * @param animations
     * @param x
     * @param y
     * @return
     */
    private boolean isLocked(ArrayList<IAnimation> animations, int x, int y) {
        for (IAnimation anim : animations) {
            if (anim instanceof FallAnimation) {
                if (((FallAnimation) anim).isLocked(x, y)) return true;
            }
        }
        return false;
    }

    /**
     * проверяет верхнюю ячейку на пустоту
     */
    private void checkForUpCell() {

    }

    @Override
    public boolean play(GameView gameView) {
        this.cell.offY += ANIM_SPEED;
        if (this.cell.offY >= MCResources.TILE_HEIGHT) {
            Cell destination = gameView.getGameField().getCell(this.sourceX, this.sourceY + 1);
            destination.setType(this.cell.getType());
            destination.isLockedForFallInto = false;

            AnimationManager animationManager = gameView.getAnimationManager();
            if (!isLocked(animationManager.getToAdds(), this.sourceX, this.sourceY) && !isLocked(animationManager.getAnimations(), this.sourceX, this.sourceY)) {
                gameView.getGameField().setCell(this.sourceX, this.sourceY, Cell.NONE);
                checkForUpCell();
            }

            return false;
        } else {
            return true;
        }
    }

}
