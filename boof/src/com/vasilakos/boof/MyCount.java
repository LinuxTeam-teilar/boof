package com.vasilakos.boof;

import java.util.ArrayList;
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
	public Integer numberOfColorChanges, players, direction;
	public ArrayList<Double> song;
	public Integer duration;
//	= { 10.0, 1.3, 2.5, 4.0, 4.8, 5.0, 5.1, 6.0, 7.0, 7.3, 8.0, 8.2, 8.5, 8.8 };

	public MyCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public MyCount(int millisInFuture, int countDownInterval, Panel p,
			Integer noOfPlayers, ArrayList<Double> chang, Context cont) {
		super(millisInFuture, countDownInterval);
		panel = p;
		players = noOfPlayers;
		context = cont;
		duration = millisInFuture/1000;
		song = chang;
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		direction = Integer.parseInt(prefs.getString("cycleDirection", "1"));
		
		if (prefs.getBoolean("randomPlayer", true))
			current = new Random().nextInt(noOfPlayers);
		else
			current = 0;
		current++;
		next = current + 1*direction;

		if (next > players)
			next = 1;
		if (next < 1)
			next = players;
		previous = 0;
		numberOfColorChanges = 0;
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
		numberOfColorChanges = 0;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		double secs = (double) millisUntilFinished / 1000;
		secs = Math.floor(secs * 10 + .5) / 10;

		if (numberOfColorChanges < song.size())
			if (secs <= duration - song.get(numberOfColorChanges)) {
				timeToChangeColor();
				numberOfColorChanges++;
			}
	}

	public void timeToChangeColor() {
		Panel.setSelectedPaintColor(current);
		Panel.setDefaultPlayerColor(previous);

		previous = current;
		current = next;
		next = current + 1*direction;
		if (next > players)
			next = 1;
		if (next < 1)
			next = players;
	}
}
