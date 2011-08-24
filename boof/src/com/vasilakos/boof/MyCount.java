package com.vasilakos.boof;

import java.util.Random;

import android.os.CountDownTimer;
import android.util.Log;

public class MyCount extends CountDownTimer {
	public Panel panel;
	public Integer current, next, previous;
	public String prevPlayer;
	public Integer c, players;
	public Double[] song = { 10.0, 1.3, 2.5, 4.0, 4.8, 5.0, 5.1, 6.0, 7.0, 7.3,
			8.0, 8.2, 8.5, 8.8 };

	public MyCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public MyCount(int millisInFuture, int countDownInterval, Panel p,
			Integer noOfPlayers) {
		super(millisInFuture, countDownInterval);
		panel = p;
		players = noOfPlayers;
		current = new Random().nextInt(noOfPlayers);
		current++;
		Log.d("koko", "current : " + current.toString());
		next = current + 1;
		if (next > players)
			next = 1;
		previous = 0;
		c = 1;
	}

	@Override
	public void onFinish() {
		Log.d(this.toString(), "done!");
		Integer i;
		for(i=1;i<=players;i++){
			if( i != previous ){
				Panel.setSelectedPaintColor(i);
				Log.d("koko", "i : " + i + "p : " + previous + " c : " + current + " n : " + next);
			}else{
				Panel.setDefaultPlayerColor(i);
			}
		}
//		Panel.setAllDefaultPaintColors();
		c = 1;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		double secs = (double) millisUntilFinished / 1000;
		secs = Math.floor(secs * 10 + .5) / 10;

		// Log.d("koko", "OUT OF IF :" + secs + ">= " + (song[0] - song[c])
		// + " , c : " + c);

		if (c < song.length)
			if (secs <= song[0] - song[c]) {
//				Log.d("koko", secs + "<= " + (song[0] - song[c]) + " , c : "
//						+ c);
				timeToChangeColor();
				c++;
			}
	}

	public void timeToChangeColor() {
		Panel.setSelectedPaintColor(current);
		Panel.setDefaultPlayerColor(previous);

		previous = current;
		current = next;
		next++;
		if (next > players)
			next = 1;
		Log.d("koko", "p : " + previous + " c : " + current + " n : " + next);
	}
}
