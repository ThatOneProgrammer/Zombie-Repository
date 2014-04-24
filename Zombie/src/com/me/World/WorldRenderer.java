package com.me.World;



import java.util.Random;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
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
	Camera camera;
	float width, height;
	public RayHandler handler;
	MainLoop game;
	InputHandler Input;
	private final float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
	Ground[] groundArray = new Ground[100];
	Random random;
	public static final int TEN = 10;
	
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
		handler = new RayHandler(World);
		handler.setCombinedMatrix(cam.combined);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		RandomizePlatform();
		
	}
		
	private void RandomizePlatform() {
		random = new Random();
		for(int x = 0, y = 0, z = 0, offset = 0; x < 100; x++, y++){
			int xPosi;
			if(y == 4){
				z = TEN * random.nextInt(5) + 20;
				if(z == 0)
					z = TEN;
				
				xPosi = x * TEN + z;
				
				if(xPosi < offset){
					z = 0;
				}else{
					offset = z;
				}
				y = 0;
			}else{
				z = 0;
				xPosi = x*TEN;
			}
			
			groundArray[x] = new Ground(World);
		}
	}
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		World.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
		cam.update();
		renderer.render(World, cam.combined);
		boxx.update(camera, delta);
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
