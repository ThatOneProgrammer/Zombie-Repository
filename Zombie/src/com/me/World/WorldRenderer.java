package com.me.World;


import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.me.Models.Boxes;
import com.me.Models.Circle;
import com.me.Models.Ground;
import com.me.Zombie.MainLoop;

public class WorldRenderer {

	Box2DDebugRenderer renderer;
	Ground ground;
	Boxes boxx;
	Circle circle;
	World World;
	Sprite ball;
	SpriteBatch batch;
	OrthographicCamera cam;
	float width, height;
	public RayHandler handler;
	MainLoop game;
	InputHandler Input;
	private final float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
	
	public WorldRenderer(MainLoop game) {
		this.game = game;
		World = new World(new Vector2(0,0), true);
		renderer = new Box2DDebugRenderer();
		circle = new Circle(World);
		boxx = new Boxes(World);
		ground = new Ground(World);
		width = Gdx.graphics.getWidth() / 45;
		height = Gdx.graphics.getHeight() / 45;

		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		handler = new RayHandler(World);
		handler.setCombinedMatrix(cam.combined);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		//Separate Method to start lighting
		startLighting();
	}
	
	private void startLighting() {
		handler.setCombinedMatrix(cam.combined);
		new PointLight(handler, 350, Color.CYAN,150,(width / 1), (height / 1));
		new PointLight(handler, 350, Color.RED,250,(width / 3), (height / 3));
		new PointLight(handler, 350, Color.GREEN,150,(width / 2), (height / 2));
	}
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		World.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
		cam.update();
		boxx.Force();
		renderer.render(World, cam.combined);
	}


	public OrthographicCamera getCamera() {
		return cam;
	}

	public void dispose() {
		batch.dispose();
		handler.dispose();
		renderer.dispose();
		//boxSprite.getTexture().dispose();
	}

}
