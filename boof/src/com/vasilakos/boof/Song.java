package com.vasilakos.boof;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

public class Song extends Activity {
	public String songName;
	public Integer songId;
	public String songLyrics;
	public Integer songAngle;
	public ArrayList<Double> songChanges;
	public int songSong;
	public int songPicture;

	public Context context;

	public Song() {
		songName = "";
		songId = 0;
		songLyrics = "";
		songAngle = 0;
		songChanges = new ArrayList<Double>();
		songSong = 0;
		songPicture = 0;
	}

	public void setSongName(String name) {
		songName = name;
	}

	public void setSongId(Integer id) {
		songId = id;
	}

	public void setSongLyrics(String lyrics) {
		songLyrics = lyrics;
	}

	public void setSongAngle(Integer angle) {
		songAngle = angle;
	}

	public void setSongChanges(String text) {
		String changes[] = text.split(", ");

		for (int i = 0; i < changes.length; i++) {
			songChanges.add(Double.parseDouble(changes[i]));
		}
	}

	public void setSongSong(Integer id) {
		switch (id) {
		case 2:
			songSong = R.raw.to_papoutsi_sou_vromaei;
			break;
		default:
			songSong = 0;
			break;
		}
	}

	public void setSongPicture(Integer id) {
		switch (id) {
		case 0:
			songPicture = R.drawable.song0;
			break;
		case 1:
			songPicture = R.drawable.song1;
			break;
		case 2:
			songPicture = R.drawable.song2;
			break;
		case 3:
			songPicture = R.drawable.song3;
			break;
		case 4:
			songPicture = R.drawable.song4;
			break;
		case 5:
			songPicture = R.drawable.song5;
			break;
		case 6:
			songPicture = R.drawable.song6;
			break;
		case 7:
			songPicture = R.drawable.song7;
			break;
		case 8:
			songPicture = R.drawable.song8;
			break;
		default:
			songPicture = R.drawable.song0;
			break;
		}
	}

	public String getSongName() {
		return this.songName;
	}

	public Integer getSongId() {
		return this.songId;
	}

	public String getSongLyrics() {
		return this.songLyrics;
	}

	public Integer getSongAngle() {
		return this.songAngle;
	}

	public ArrayList<Double> getSongChanges() {
		return this.songChanges;
	}

	public int getSongSong() {
		return this.songSong;
	}

	public int getSongPicture() {
		return this.songPicture;
	}
}