package com.vasilakos.boof;

import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;

public class MyCount extends CountDownTimer {
	public Panel panel;
	public Paint nextPaint, prevPaint;
	public String prevPlayer;
	public Integer c, players;
	public Double[] song = { 10.0, 1.3, 2.5, 4.0, 4.8, 5.0, 5.1, 6.0, 7.0, 7.3,
			8.0, 8.2, 8.5, 8.8 };

	public MyCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public MyCount(int millisInFuture, int countDownInterval, Panel p, Integer noOfPlayers) {
		super(millisInFuture, countDownInterval);
		panel = p;
		nextPaint = Panel.p1;
		prevPaint = Panel.p9;
		c = 1;
		players = noOfPlayers;
	}

	@Override
	public void onFinish() {
		Log.d(this.toString(), "done!");
		Panel.setDefaultPaintColors();
		c= 1;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		double secs = (double) millisUntilFinished / 1000;
		secs = Math.floor(secs * 10 + .5) / 10;

//		Log.d("koko", "OUT OF IF :" + secs + ">= " + (song[0] - song[c])
//				+ " , c : " + c);
		
		if (c < song.length)
			if (secs <= song[0] - song[c]) {
				Log.d("koko", secs + "<= " + (song[0] - song[c]) + " , c : " + c);
				timeToChangeColor();
				c++;
			}
	}

	public void timeToChangeColor() {
		 Log.d("timeToChangeColor", "next : " + nextPaint.toString());

		 Panel.setSelectedPaintColor(nextPaint);
		if (nextPaint == Panel.p1) {
			nextPaint = Panel.p2;
			prevPaint = Panel.p9;
			prevPlayer = "p9";
		} else if (nextPaint == Panel.p2) {
			nextPaint = Panel.p3;
			prevPaint = Panel.p1;
			prevPlayer = "p1";
		} else if (nextPaint == Panel.p3) {
			nextPaint = Panel.p4;
			prevPaint = Panel.p2;
			prevPlayer = "p2";
		} else if (nextPaint == Panel.p4) {
			nextPaint = Panel.p5;
			prevPaint = Panel.p3;
			prevPlayer = "p3";
		} else if (nextPaint == Panel.p5) {
			nextPaint = Panel.p6;
			prevPaint = Panel.p4;
			prevPlayer = "p4";
		} else if (nextPaint == Panel.p6) {
			nextPaint = Panel.p7;
			prevPaint = Panel.p5;
			prevPlayer = "p5";
		} else if (nextPaint == Panel.p7) {
			nextPaint = Panel.p8;
			prevPaint = Panel.p6;
			prevPlayer = "p6";
		} else if (nextPaint == Panel.p8) {
			nextPaint = Panel.p9;
			prevPaint = Panel.p7;
			prevPlayer = "p7";
		} else {
			nextPaint = Panel.p1;
			prevPaint = Panel.p8;
			prevPlayer = "p8";
		}

		 Panel.setUnselectedPaintColor(prevPaint, prevPlayer);
	}
}
