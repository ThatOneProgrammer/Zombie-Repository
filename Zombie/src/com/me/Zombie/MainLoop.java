package com.me.Zombie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.me.Screens.MainMenu;
import com.me.Screens.PlayScreen;
import com.me.Screens.SplashScreen;
import com.me.World.ZombieAudio;

public class MainLoop extends Game {
	
	public static final String LOG = "Zombie";
	public static final String TITLE = "Zombie Alpha v 0.0.0.1";
	public static final boolean DEBUG = true;
	
	@Override
	public void create() {		
		ZombieAudio.load("sounds/menu_music.mp3", "menu");
		ZombieAudio.load("sounds/play_music.mp3", "play");
		//setScreen(new SplashScreen(this));
		setScreen(new MainMenu(this));
		//setScreen(new PlayScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void render(float delta) {	
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
