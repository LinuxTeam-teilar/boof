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
		setAllDefaultPaintColors();
		Integer size = Main.getScreenSize();
		rect = new RectF(space / 2, space / 2 + 10, size - space / 2, size
				- space / 2);

		getHolder().addCallback(this);
		mThread = new ViewThread(this);
	}

	public void doDraw(Canvas canvas) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(cnt);
		canvas.drawColor(prefs.getInt("bgColor", Color.BLACK));

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

	public static int getDefaultPlayerColor1(Integer player) {
		Integer color = 0;
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(cnt);

		if (player == 1) {
			color = prefs.getInt("p1Color", Color.parseColor("#ffff0000"));
		} else if (player == 2) {
			color = prefs.getInt("p2Color", Color.parseColor("#ffffff00"));
		} else if (player == 3) {
			color = prefs.getInt("p3Color", Color.parseColor("#ff0000ff"));
		} else if (player == 4) {
			color = prefs.getInt("p4Color", Color.parseColor("#ff008000"));
		} else if (player == 5) {
			color = prefs.getInt("p5Color", Color.parseColor("#ffff00ff"));
		} else if (player == 6) {
			color = prefs.getInt("p6Color", Color.parseColor("#ff00ff00"));
		} else if (player == 7) {
			color = prefs.getInt("p7Color", Color.parseColor("#ff00ffff"));
		} else if (player == 8) {
			color = prefs.getInt("p8Color", Color.parseColor("#ff800000"));
		} else if (player == 9) {
			color = prefs.getInt("p9Color", Color.parseColor("#ff800080"));
		}

		return color;

	}

	public static int getDefaultPlayerColor(Integer player) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(cnt);

		switch (player) {
		case 1:
			return prefs.getInt("p1Color", Color.parseColor("#ffff0000"));
		case 2:
			return prefs.getInt("p2Color", Color.parseColor("#ffffff00"));
		case 3:
			return prefs.getInt("p3Color", Color.parseColor("#ff0000ff"));
		case 4:
			return prefs.getInt("p4Color", Color.parseColor("#ff008000"));
		case 5:
			return prefs.getInt("p5Color", Color.parseColor("#ffff00ff"));
		case 6:
			return prefs.getInt("p6Color", Color.parseColor("#ff00ff00"));
		case 7:
			return prefs.getInt("p7Color", Color.parseColor("#ff00ffff"));
		case 8:
			return prefs.getInt("p8Color", Color.parseColor("#ff800000"));
		case 9:
			return prefs.getInt("p9Color", Color.parseColor("#ff800080"));
		default:
			return Color.TRANSPARENT;
		}
	}
	
	public static void setDefaultPlayerColor(Integer player){
		switch (player) {
		case 1:
			p1.setColor(getDefaultPlayerColor(player));
			break;
		case 2:
			p2.setColor(getDefaultPlayerColor(player));
			break;
		case 3:
			p3.setColor(getDefaultPlayerColor(player));
			break;
		case 4:
			p4.setColor(getDefaultPlayerColor(player));
			break;
		case 5:
			p5.setColor(getDefaultPlayerColor(player));
			break;
		case 6:
			p6.setColor(getDefaultPlayerColor(player));
			break;
		case 7:
			p7.setColor(getDefaultPlayerColor(player));
			break;
		case 8:
			p8.setColor(getDefaultPlayerColor(player));
			break;
		case 9:
			p9.setColor(getDefaultPlayerColor(player));
			break;
		}
	}

	public static void setAllDefaultPaintColors() {
		p1.setColor(getDefaultPlayerColor(1));
		p2.setColor(getDefaultPlayerColor(2));
		p3.setColor(getDefaultPlayerColor(3));
		p4.setColor(getDefaultPlayerColor(4));
		p5.setColor(getDefaultPlayerColor(5));
		p6.setColor(getDefaultPlayerColor(6));
		p7.setColor(getDefaultPlayerColor(7));
		p8.setColor(getDefaultPlayerColor(8));
		p9.setColor(getDefaultPlayerColor(9));
	}

	public static void setSelectedPaintColor(Integer player) {
		switch (player) {
		case 1:
			p1.setColor(Color.TRANSPARENT);
			break;
		case 2:
			p2.setColor(Color.TRANSPARENT);
			break;
		case 3:
			p3.setColor(Color.TRANSPARENT);
			break;
		case 4:
			p4.setColor(Color.TRANSPARENT);
			break;
		case 5:
			p5.setColor(Color.TRANSPARENT);
			break;
		case 6:
			p6.setColor(Color.TRANSPARENT);
			break;
		case 7:
			p7.setColor(Color.TRANSPARENT);
			break;
		case 8:
			p8.setColor(Color.TRANSPARENT);
			break;
		case 9:
			p9.setColor(Color.TRANSPARENT);
			break;
		}
	}
}
