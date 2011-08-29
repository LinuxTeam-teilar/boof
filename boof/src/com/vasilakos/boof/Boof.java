package com.vasilakos.boof;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class Boof extends Activity {

	public MyCount counter;
	public static MediaPlayer song;
	public static Animation anim;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = this.getIntent().getExtras();
		Integer noOfPlayers = b.getInt("players");
		Integer selectedSong = b.getInt("song");
		Log.d("koko", "bundle read selected song : " + selectedSong);

		Display display = getWindowManager().getDefaultDisplay();

		Panel p = new Panel(this, noOfPlayers, display.getWidth(),
				display.getHeight());
		setContentView(p);

		LayoutInflater inflater = getLayoutInflater();
		View tmpView;
		tmpView = inflater.inflate(R.layout.boof, null);
		addContentView(tmpView, new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT));

		String songId = "";
		String songName = "";
		String songChanges = "";
		String songLyrics = "";
		XmlResourceParser parser = getResources().getXml(R.xml.songs);

		try {
			int eventType = parser.getEventType();
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				if (eventType == XmlResourceParser.START_TAG) {
					if (parser.getName().equals("song")) {
						songId = parser.getAttributeValue(null, "id");
						if (songId.equals(selectedSong.toString())) {
							songName = parser.getAttributeValue(null, "name");
							songChanges = parser.getAttributeValue(null,
									"changes");
							songLyrics = parser.getAttributeValue(null,
									"lyrics");
							Log.d("koko", "name : " + songName
									+ " , changes : " + songChanges
									+ ", lyrics : " + songLyrics);
						}
					}
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		song = getSongFromId(selectedSong);

		LyricsCircleView lyrics = new LyricsCircleView(this,
		display.getWidth(), display.getHeight(), songLyrics);
		addContentView(lyrics, new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT));

		anim = new RotateAnimation(-180, 180, display.getWidth() / 2,
				display.getHeight() / 2);
		anim.setDuration(song.getDuration());
		anim.setFillAfter(true);
//		anim.setRepeatCount(2);
		// anim.setInterpolator(new AccelerateDecelerateInterpolator());

		lyrics.startAnimation(anim);
		
		counter = new MyCount(song.getDuration(), 100, p, noOfPlayers, songChanges, 
				getBaseContext());
		counter.start();
	}

	@Override
	public void onStart() {
		super.onStart();
		song.start();
	}

	@Override
	public void onStop() {
		super.onStop();
		counter.cancel();

		try {
			if (song.isPlaying()) {
				song.stop();
			}
		} catch (Exception e) {
			return;
		}
	}

	public MediaPlayer getSongFromId(int id) {
		MediaPlayer s = null;
		switch (id) {
		case 2:
			s = MediaPlayer.create(getApplicationContext(),
					R.raw.to_papoutsi_sou_vromaei);
			break;
		default:
			s = MediaPlayer.create(getApplicationContext(),
					R.raw.childrenplaying);
		}
		return s;
	}
}
