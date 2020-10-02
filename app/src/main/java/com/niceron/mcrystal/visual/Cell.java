package com.niceron.mcrystal.visual;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import com.niceron.mcrystal.animation.Animation;
import com.niceron.mcrystal.logic.CellType;
import com.niceron.mcrystal.math.Position;
import com.niceron.mcrystal.math.PositionInt;

public class Cell implements Drawable {
	private static final Paint DEFAULT_PAINT = new Paint();
	
	private final CellType type;
	private final Position position;
	private final PositionInt fieldPosition;
	private final Paint paint;
	public Animation currentAnimation = null;
	
	public Cell(CellType type, Position position, PositionInt fieldPosition) {
		this.position = position;
		this.fieldPosition = fieldPosition;
		this.type = type;
		this.paint = new Paint(DEFAULT_PAINT);
	}
	
	public void setAlpha(int alpha) {
		this.paint.setAlpha(alpha);
	}
	
	public CellType getType() {
		return this.type;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public PositionInt getFieldPosition() {
		return fieldPosition;
	}
	
	public void moveBy(Position offset) {
		position.add(offset);
	}
	
	public void moveBy(float x, float y) {
		position.add(x, y);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(type.getVisual(), position.x, position.y, paint);
	}
	
	@NonNull
	@Override
	public String toString() {
		return type.name();
	}
}
