package com.me.Models;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Ground {
	private Body box;
	BodyDef bodyDef = new BodyDef();
	FixtureDef fixtureDef = new FixtureDef();
	
	public Ground(World world){
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(1f, 0);
		
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
}
