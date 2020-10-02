package com.niceron.mcrystal.logic;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.niceron.mcrystal.R;

import java.util.Random;

public enum CellType {
	RED, GREEN, BLUE, YELLOW, VIOLET;
	
	private static final CellType[] TYPES = values();
	private Bitmap visual;
	
	public Bitmap getVisual() {
		return visual;
	}
	
	public static CellType random(Random random) {
		return TYPES[random.nextInt(TYPES.length)];
	}
	
	private static void setVisual(CellType type, Resources resources, int resourceId) {
		type.visual = BitmapFactory.decodeResource(resources, resourceId);
	}
	
	public static void init(Resources resources) {
		setVisual(RED, resources, R.drawable.red);
		setVisual(GREEN, resources, R.drawable.green);
		setVisual(BLUE, resources, R.drawable.blue);
		setVisual(YELLOW, resources, R.drawable.yellow);
		setVisual(VIOLET, resources, R.drawable.violet);
	}
}
