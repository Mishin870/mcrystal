package mishin870.com.mcrystal.anim;

/**
 * Created by Mishin870 on 23.10.2017.
 * интерфейс для всех анимаций
 */
public interface IAnimation {
    /**
     * отыгровка анимации
     * @return false если анимация закончилась
     */
    public boolean play();
}
