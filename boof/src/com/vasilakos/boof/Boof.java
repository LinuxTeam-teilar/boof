package com.vasilakos.boof;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
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

		LyricsCircleView lyrics = new LyricsCircleView(this,
				display.getWidth(), display.getHeight());
		addContentView(lyrics, new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT));

		song = MediaPlayer.create(getApplicationContext(),
				R.raw.childrenplaying);
		
		anim = new RotateAnimation(180, -180, display.getWidth()/2, display.getHeight()/2);
		anim.setDuration(song.getDuration()/3);
		anim.setFillAfter(true);
		anim.setRepeatCount(2);
//		anim.setInterpolator(new AccelerateDecelerateInterpolator());

		lyrics.startAnimation(anim);

		counter = new MyCount(song.getDuration(), 100, p, noOfPlayers, getBaseContext());
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
}
