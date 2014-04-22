package com.me.Models;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Circle {
	
	float width, height;
	Body body;
	BodyDef bodyDef = new BodyDef();
	FixtureDef fixtureDef = new FixtureDef();
	World world;
	
	public Circle(World world){
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(10.25f, 10);
		
		//ball shape
		CircleShape shape = new CircleShape();
		shape.setRadius(.5f);
		
		//Fixture instantiate 
			fixtureDef.shape = shape;
			fixtureDef.density = 2.5f;
			fixtureDef.friction = .5f;
			fixtureDef.restitution = .8f;
			
			body = world.createBody(bodyDef);
			body.createFixture(fixtureDef);
	}
}
