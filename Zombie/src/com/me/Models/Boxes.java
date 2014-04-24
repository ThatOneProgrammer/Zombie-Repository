package com.me.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.me.World.InputHandler;

public class Boxes {
	
	private Body box;
	World world;
	private Vector2 movement = new Vector2(0, 0);
	private float speed = 500;
	
	public Boxes(World world){
		Input();
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();	
			//BOX
			//bodyDef
			bodyDef.type = BodyType.DynamicBody;
			bodyDef.position.set(2.25f, 10);
			
			//Box Shape
			PolygonShape boxShape = new PolygonShape();
			boxShape.setAsBox(.5f, 1);
			
			//Fixture Def
			fixtureDef.shape = boxShape;
			fixtureDef.friction =.75f;
			fixtureDef.restitution = .1f;
			fixtureDef.density = 5;
			box = world.createBody(bodyDef);
			box.createFixture(fixtureDef);
	}
	private void Input() {
		Gdx.input.setInputProcessor(new InputHandler(){
			public boolean keyDown(int keycode){
				switch(keycode){
				case Keys.W:
					movement.y = speed;
					break;
				case Keys.A:
					movement.x =- speed;
					break;
				case Keys.S:
					movement.y =- speed;
					break;
				case Keys.D:
					movement.x = speed;
					break;
				}
				return true;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				switch(keycode){
				case Keys.W:
				case Keys.S:
					movement.y = 0;
					break;
				case Keys.D:
				case Keys.A:
					movement.x = 0;
					break;
				}
				return true;
			}
		});
	}
	public void update(Camera camera, float delta){
		box.applyForceToCenter(movement, true);
		camera.position.set(box.getPosition().x, box.getPosition().y, 0);
	}
}
