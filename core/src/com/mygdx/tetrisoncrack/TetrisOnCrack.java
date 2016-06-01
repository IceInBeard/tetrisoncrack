package com.mygdx.tetrisoncrack;

import com.badlogic.gdx.Game;


import java.util.Locale;

public class TetrisOnCrack extends Game {
	Locale locale;
	
	@Override
	public void create () {
		Ass.load();
		this.setScreen(new MenuScreen(this));
		/*
		Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

			@Override
			public void onUp() {
				Gdx.app.log("MyTag", "Up");
			}

			@Override
			public void onRight() {
				Gdx.app.log("MyTag", "Right");


			}

			@Override
			public void onLeft() {
				Gdx.app.log("MyTag", "Left");


			}

			@Override
			public void onDown() {
				Gdx.app.log("MyTag", "Down");
			}
		}));
	*/
	}

	@Override
	public void dispose() {
		// .dispose() on all the stuff;
		// textures, sound, batch etc

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
