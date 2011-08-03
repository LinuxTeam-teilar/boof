package com.vasilakos.boof;


import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;

public class MyCount extends CountDownTimer {
	Panel panel;
	Paint nextPaint, prevPaint;
	double[] song = { 1.3, 3.5, 4, 4.8, 5, 5.1, 6, 7, 7.3, 8, 8.2, 8.5, 8.8 };

	public MyCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public MyCount(int millisInFuture, int countDownInterval, Panel p) {
		super(millisInFuture, countDownInterval);
		panel = p;
		nextPaint = Panel.p1;
		prevPaint = Panel.p9;
	}

	@Override
	public void onFinish() {
		Log.d(this.toString(), "done!");
		Panel.setPaintColors();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		double secs = (double) millisUntilFinished / 1000;
		secs = Math.floor(secs * 10 + .5) / 10;

		if (secs <= song[0]) {
			timeToChangeColor();
		} else if (secs <= song[1]) {
			timeToChangeColor();
		} else if (secs <= song[2]) {
			timeToChangeColor();
		} else if (secs <= song[3]) {
			timeToChangeColor();
		} else if (secs <= song[4]) {
			timeToChangeColor();
		} else if (secs <= song[5]) {
			timeToChangeColor();
		} else if (secs <= song[6]) {
			timeToChangeColor();
		} else if (secs <= song[7]) {
			timeToChangeColor();
		} else if (secs <= song[8]) {
			timeToChangeColor();
		} else if (secs <= song[9]) {
			timeToChangeColor();
		} else if (secs <= song[10]) {
			timeToChangeColor();
		} else if (secs <= song[11]) {
			timeToChangeColor();
		}
	}

	public void timeToChangeColor() {
		// Log.d("timeToChangeColor", "next : " + nextPaint.toString());

		// Panel.setSelectedPaintColor(nextPaint);
		if (nextPaint == Panel.p1) {
			nextPaint = Panel.p2;
			prevPaint = Panel.p9;
		} else if (nextPaint == Panel.p2) {
			nextPaint = Panel.p3;
			prevPaint = Panel.p1;
		} else if (nextPaint == Panel.p3) {
			nextPaint = Panel.p4;
			prevPaint = Panel.p2;
		} else if (nextPaint == Panel.p4) {
			nextPaint = Panel.p5;
			prevPaint = Panel.p3;
		} else if (nextPaint == Panel.p5) {
			nextPaint = Panel.p6;
			prevPaint = Panel.p4;
		} else if (nextPaint == Panel.p6) {
			nextPaint = Panel.p7;
			prevPaint = Panel.p5;
		} else if (nextPaint == Panel.p7) {
			nextPaint = Panel.p8;
			prevPaint = Panel.p6;
		} else if (nextPaint == Panel.p8) {
			nextPaint = Panel.p9;
			prevPaint = Panel.p7;
		} else {
			nextPaint = Panel.p1;
			prevPaint = Panel.p8;
		}

		// Panel.setUnselectedPaintColor(prevPaint);
	}
}
