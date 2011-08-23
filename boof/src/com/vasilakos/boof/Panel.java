package com.vasilakos.boof;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.preference.PreferenceManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

	public static Float space = (float) 50;
	public static Paint p1;
	public static Paint p2;
	public static Paint p3;
	public static Paint p4;
	public static Paint p5;
	public static Paint p6;
	public static Paint p7;
	public static Paint p8;
	public static Paint p9;
	public static RectF rect;
	public static Integer players;

	public static Context cnt;

	private ViewThread mThread;

	public Panel(Context context, Integer noOfPlayers) {
		super(context);

		cnt = context;

		players = noOfPlayers;

		createPaints();
		setDefaultPaintColors();
		Integer size = Main.getScreenSize();
		rect = new RectF(space / 2, space / 2 + 10, size - space / 2, size
				- space / 2);

		getHolder().addCallback(this);
		mThread = new ViewThread(this);
	}

	public void doDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		Float len = (float) 360 / players;
		// Log.d(this.toString(), "len : " + len.toString());
		Float start = (float) 0;
		int i = 0;
		for (i = 1; i <= players; i++) {
			// Log.d(this.toString(), "i: " + i);
			switch (i) {
			case 1:
				canvas.drawArc(rect, start, len, true, p1);
				break;
			case 2:
				canvas.drawArc(rect, start, len, true, p2);
				break;
			case 3:
				canvas.drawArc(rect, start, len, true, p3);
				break;
			case 4:
				canvas.drawArc(rect, start, len, true, p4);
				break;
			case 5:
				canvas.drawArc(rect, start, len, true, p5);
				break;
			case 6:
				canvas.drawArc(rect, start, len, true, p6);
				break;
			case 7:
				canvas.drawArc(rect, start, len, true, p7);
				break;
			case 8:
				canvas.drawArc(rect, start, len, true, p8);
				break;
			case 9:
				canvas.drawArc(rect, start, len, true, p9);
				break;
			}
			start += len;
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if (!mThread.isAlive()) {
			mThread = new ViewThread(this);
			mThread.setRunning(true);
			mThread.start();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mThread.isAlive()) {
			mThread.setRunning(false);
		}
	}

	public void createPaints() {
		p1 = new Paint();
		p2 = new Paint();
		p3 = new Paint();
		p4 = new Paint();
		p5 = new Paint();
		p6 = new Paint();
		p7 = new Paint();
		p8 = new Paint();
		p9 = new Paint();
	}

	public static int getDefaultPlayerColor(String player){
		Integer color = 0;
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(cnt);
		
		if (player == "p1"){
			color = prefs.getInt("p1Color", Color.parseColor("#ffff0000"));
		}else if (player == "p2"){
			color = prefs.getInt("p2Color", Color.parseColor("#ffffff00"));
		}else if (player == "p3"){
			color = prefs.getInt("p3Color", Color.parseColor("#ff0000ff"));
		}else if (player == "p4"){
			color = prefs.getInt("p4Color", Color.parseColor("#ff008000"));
		}else if (player == "p5"){
			color = prefs.getInt("p5Color", Color.parseColor("#ffff00ff"));
		}else if (player == "p6"){
			color = prefs.getInt("p6Color", Color.parseColor("#ff00ff00"));
		}else if (player == "p7"){
			color = prefs.getInt("p7Color", Color.parseColor("#ff00ffff"));
		}else if (player == "p8"){
			color = prefs.getInt("p8Color", Color.parseColor("#ff800000"));
		}else if (player == "p9"){
			color = prefs.getInt("p9Color", Color.parseColor("#ff800080"));
		}
		
		return color;
		
	}

	public static void setDefaultPaintColors() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(cnt);

		p1.setColor(prefs.getInt("p1Color", Color.parseColor("#ffff0000")));
		p2.setColor(prefs.getInt("p2Color", Color.parseColor("#ffffff00")));
		p3.setColor(prefs.getInt("p3Color", Color.parseColor("#ff0000ff")));
		p4.setColor(prefs.getInt("p4Color", Color.parseColor("#ff008000")));
		p5.setColor(prefs.getInt("p5Color", Color.parseColor("#ffff00ff")));
		p6.setColor(prefs.getInt("p6Color", Color.parseColor("#ff00ff00")));
		p7.setColor(prefs.getInt("p7Color", Color.parseColor("#ff00ffff")));
		p8.setColor(prefs.getInt("p8Color", Color.parseColor("#ff800000")));
		p9.setColor(prefs.getInt("p9Color", Color.parseColor("#ff800080")));
	}

	public static void setSelectedPaintColor(Paint p) {
		p.setColor(Color.BLACK);
	}

	public static void setUnselectedPaintColor(Paint p, String pst) {
		p.setColor(Panel.getDefaultPlayerColor(pst));
	}
}
