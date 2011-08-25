package com.vasilakos.boof;

import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class MyCount extends CountDownTimer {
	public Panel panel;
	Context context;
	public Integer current, next, previous;
	public String prevPlayer;
	public Integer c, players;
	public Double[] song = { 10.0, 1.3, 2.5, 4.0, 4.8, 5.0, 5.1, 6.0, 7.0, 7.3,
			8.0, 8.2, 8.5, 8.8 };

	public MyCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public MyCount(int millisInFuture, int countDownInterval, Panel p,
			Integer noOfPlayers, Context cont) {
		super(millisInFuture, countDownInterval);
		panel = p;
		players = noOfPlayers;
		context = cont;
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		if (prefs.getBoolean("randomPlayer", true))
			current = new Random().nextInt(noOfPlayers);
		else
			current = 0;
		current++;
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
		for (i = 1; i <= players; i++) {
			if (i != previous) {
				Panel.setSelectedPaintColor(i);
			} else {
				Panel.setDefaultPlayerColor(i);
			}
		}
		Toast.makeText(context, "Player " + previous + " won!", Toast.LENGTH_LONG).show();
		c = 1;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		double secs = (double) millisUntilFinished / 1000;
		secs = Math.floor(secs * 10 + .5) / 10;

		if (c < song.length)
			if (secs <= song[0] - song[c]) {
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
	}
}
