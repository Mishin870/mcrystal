package com.niceron.mcrystal.math;

public class PositionInt {
	public int x;
	public int y;
	
	public PositionInt(int value) {
		this.x = value;
		this.y = value;
	}
	
	public PositionInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static PositionInt of(int x, int y) {
		return new PositionInt(x, y);
	}
	
	public void setFrom(PositionInt another) {
		this.x = another.x;
		this.y = another.y;
	}
	
	public void add(PositionInt position) {
		this.x += position.x;
		this.y += position.y;
	}
	
	public void add(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	@Override
	public String toString() {
		return "PositionInt{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
