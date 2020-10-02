package com.niceron.mcrystal.math;

public class Position {
	public float x;
	public float y;
	
	public Position(float value) {
		this.x = value;
		this.y = value;
	}
	
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static Position of(float x, float y) {
		return new Position(x, y);
	}
	
	public void setFrom(Position another) {
		this.x = another.x;
		this.y = another.y;
	}
	
	public void add(Position position) {
		this.x += position.x;
		this.y += position.y;
	}
	
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	public boolean smoothMove(Position to, float speed) {
		float dx = to.x - x;
		float dy = to.y - y;
		
		if (speed >= Math.abs(dx)) {
			x = to.x;
			dx = 0;
		} else {
			x += speed * Math.signum(dx);
		}
		
		if (speed >= Math.abs(dy)) {
			y = to.y;
			dy = 0;
		} else {
			y += speed * Math.signum(dy);
		}
		
		return dx != 0 || dy != 0;
	}
	
	@Override
	public String toString() {
		return "Position{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
