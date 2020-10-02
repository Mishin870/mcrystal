package com.niceron.mcrystal.core;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Game(this));
	}
	
}
