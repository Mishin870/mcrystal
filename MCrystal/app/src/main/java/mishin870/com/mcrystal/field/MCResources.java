package mishin870.com.mcrystal.field;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import mishin870.com.mcrystal.R;

/**
 * Created by Mishin870 on 23.10.2017.
 * все ресурсы проекта в виде bitmap
 */
public class MCResources {
    public static Bitmap red, green, blue, yellow;
    public static int TILE_WIDTH, TILE_HEIGHT;

    /**
     * создание битмапок
     */
    public static void init(Resources res) {
        red = BitmapFactory.decodeResource(res, R.drawable.red);
        green = BitmapFactory.decodeResource(res, R.drawable.green);
        blue = BitmapFactory.decodeResource(res, R.drawable.blue);
        yellow = BitmapFactory.decodeResource(res, R.drawable.yellow);
        TILE_WIDTH = red.getWidth();
        TILE_HEIGHT = blue.getHeight();
    }
}
