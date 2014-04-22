package com.me.World;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class ZombieAudio {

	public static HashMap<String, Music> music;
	
	static{
		music = new HashMap<String, Music>();
	}
	
	public static void load(String path, String name){
		Music musics = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.put(name, musics);
	}
	
	public static void play(String name){
		music.get(name).play();
	}
	public static void loop(String name){
		music.get(name).setLooping(true);
	}
	public static void stop(String name){
		music.get(name).stop();
	}
	public static void stopAll(){
		for(Music m : music.values()){
			m.stop();
		}
	}
}
