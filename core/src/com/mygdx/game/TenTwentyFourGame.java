package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TenTwentyFourGame extends Game {

	BoardScreen boardScreen;
	
	@Override
	public void create () {
		// Set the default screen
		boardScreen = new BoardScreen(this);
		setScreen(boardScreen);
	}

}
