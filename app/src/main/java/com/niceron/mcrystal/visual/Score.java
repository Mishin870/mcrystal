package com.niceron.mcrystal.visual;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.niceron.mcrystal.math.Position;

public class Score implements Drawable {
	private static final Paint BLACK_PAINT = new Paint();
	static {
		BLACK_PAINT.setStyle(Paint.Style.STROKE);
		BLACK_PAINT.setTextSize(50);
	}
	private static final Position OFFSET = new Position(10);
	
	private int score;
	
	public void set(int score) {
		this.score = score;
	}
	
	public void add(int toAdd) {
		this.score += toAdd;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(String.valueOf(score),
				OFFSET.x,
				canvas.getHeight() - OFFSET.y - 30,
				BLACK_PAINT);
	}
}
