package com.me.Screens;


import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.me.World.WorldRenderer;
import com.me.World.ZombieAudio;
import com.me.Zombie.MainLoop;

public class PlayScreen implements Screen {

	float width, height;
	
	
	MainLoop game;
	World World;
	WorldRenderer render;
	RayHandler handler;
	Box2DDebugRenderer renderer;
	OrthographicCamera camera;
	SpriteBatch batch;

	
	
	public PlayScreen(MainLoop game){
		ZombieAudio.play("play");
		this.game = game;
		render = new WorldRenderer(game);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		render.render(delta);
	}
	

	public void create() {
		width = Gdx.graphics.getWidth() /5;
		height = Gdx.graphics.getHeight() /5;
	}
	
	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
	render.dispose();
	ZombieAudio.stop("play");
	}

	
}