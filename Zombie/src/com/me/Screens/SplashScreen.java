package com.me.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.World.ZombieAudio;
import com.me.Zombie.MainLoop;
import com.me.ZombieTween.SpriteTween;

public class SplashScreen implements Screen {

	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	MainLoop game;
	TweenManager manager;

	public SplashScreen(MainLoop game) {
		this.game = game;
	}


	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.update(delta);
		batch.begin();
		splashSprite.draw(batch);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		splashSprite.setSize(width, height);
	}

	@Override
	public void show() {
		ZombieAudio.play("menu");
		splashTexture = new Texture("zombie.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);
		splashSprite.setX(Gdx.graphics.getWidth() / 2
				- (splashSprite.getWidth() / 2));
		splashSprite.setY(Gdx.graphics.getHeight() / 2
				- (splashSprite.getHeight() / 2));
		batch = new SpriteBatch();
		Tween.registerAccessor(Sprite.class, new SpriteTween());

		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();

			}
		};

		Tween.to(splashSprite, SpriteTween.ALPHA, 2.5f).target(1)
				.ease(TweenEquations.easeInQuad).repeatYoyo(1, 2.5f).setCallback(cb)
				.setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}

	private void tweenCompleted() {
		Gdx.app.log(MainLoop.LOG, "Tween Complete");
		game.setScreen(new MainMenu(game));
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		splashSprite.getTexture().dispose();
	}

}
