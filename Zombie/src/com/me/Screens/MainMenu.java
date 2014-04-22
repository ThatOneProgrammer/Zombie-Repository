package com.me.Screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.World.ZombieAudio;
import com.me.Zombie.MainLoop;
import com.me.ZombieTween.ActorAccessor;

public class MainMenu implements Screen {

	private MainLoop game;
	private Table table;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private TweenManager tweenManager;

	public MainMenu(MainLoop game) {
		this.game = game;
	}

	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);

		tweenManager.update(delta);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		table.invalidateHierarchy();
		table.setSize(width, height);
	}

	@Override
	public void show() {
		//ZombieAudio.play("menu");
		// declares actors
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("buttonblue.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);
		skin.addRegions(atlas);
		table = new Table(skin);

		// Button Position
		TextButton button = new TextButton(null, skin);
		button.pad(20);
		button.setWidth(200);
		button.setHeight(200);
		button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);

		// Button Input
			Gdx.input.setInputProcessor(stage);
			button.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
				 ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
				}
			});
		// heading
		Label heading = new Label(MainLoop.TITLE, skin); 
		heading.setScale(3);

		// adds everything to the screen
		table.add(heading);
		table.getCell(heading).spaceBottom(100);
		table.row();
		table.add(button);
		table.debug();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);

		// Tween Animation
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		// Timeline animation for colored Heading
		Timeline.createSequence().beginSequence()
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 0, 1))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 0))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 0))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 0))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 0, 1))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 1))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 1))
				.end().repeat(Tween.INFINITY, 0).start(tweenManager);

		// Timeline Animation. Heading First to fade in
		Timeline.createSequence().beginSequence()
				.push(Tween.set(button, ActorAccessor.ALPHA).target(0))
				.push(Tween.from(heading, ActorAccessor.ALPHA, .5f).target(0))
				.push(Tween.to(button, ActorAccessor.ALPHA, .5f).target(1))
				.end().start(tweenManager);
		
		// table fade in
		Tween.from(table, ActorAccessor.ALPHA, .5f).target(0);
		Tween.from(table, ActorAccessor.Y, .5f)
				.target(Gdx.graphics.getHeight() / 8).start(tweenManager);
	}

	@Override
	public void hide() {
		skin.dispose();
		atlas.dispose();
		stage.dispose();
		ZombieAudio.stop("menu");
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		
	}

}
