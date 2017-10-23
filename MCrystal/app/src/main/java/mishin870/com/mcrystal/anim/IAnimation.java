package mishin870.com.mcrystal.anim;

import mishin870.com.mcrystal.GameView;

/**
 * Created by Mishin870 on 23.10.2017.
 * интерфейс для всех анимаций
 */
public interface IAnimation {
    /**
     * отыгровка анимации
     * @param gameView ссылка на главный игровой класс
     * @return false если анимация закончилась
     */
    public boolean play(GameView gameView);
}
