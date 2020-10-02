package com.niceron.mcrystal.logic;

import com.niceron.mcrystal.core.Config;
import com.niceron.mcrystal.math.Position;
import com.niceron.mcrystal.math.PositionInt;

public class FieldUtils {
	public static final Position SPACING = new Position(10);
	public static final Position OFFSET = new Position(5);
	
	public static Position fieldToReal(PositionInt field) {
		return fieldToReal(field.x, field.y);
	}
	
	public static Position fieldToReal(int x, int y) {
		return Position.of(OFFSET.x + x * (Config.TILE_WIDTH + SPACING.x),
				OFFSET.y + y * (Config.TILE_HEIGHT + SPACING.y));
	}
	
}
