package com.me.World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.me.Screens.MainMenu;
import com.me.Zombie.MainLoop;

public class InputHandler implements InputProcessor {

	Vector3 touch = new Vector3();
	Vector2 vec2Touch = new Vector2();
	MainLoop game;
	public InputHandler() {
		this.game = game;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(
					game));
		}
		switch (keycode) {
		case Keys.W:
			break;
		}
		switch (keycode) {
		case Keys.S:
			break;
		}
		switch (keycode) {
		case Keys.A:
			break;
		}
		switch (keycode) {
		case Keys.D:
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			
			break;
		}
		switch (keycode) {
		case Keys.S:
			
			break;
		}
		switch (keycode) {
		case Keys.A:
			
			break;
		}
		switch (keycode) {
		case Keys.D:
		
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touch.set(screenX, screenY, 0);
		vec2Touch.set(touch.x, touch.y);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {

		return false;
	}

}
