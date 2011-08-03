package com.vasilakos.boof;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

	public static Float space = (float) 60;
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
	public Integer players = 7;

	private ViewThread mThread;

	public Panel(Context context) {
		super(context);

		createPaints();
		setPaintColors();
		Integer size = Boof.getScreenSize();
		rect = new RectF(space / 2, space / 2 + 10, size - space / 2, size
				- space / 2);
		 
		getHolder().addCallback(this);
		mThread = new ViewThread(this);
	}
	
	public void doDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		Float len = (float) 360 / players;
//		Log.d(this.toString(), "len : " + len.toString());
		Float start = (float) 0;
		int i=0;
		for ( i = 1; i <= players; i++) {
//			Log.d(this.toString(), "i: " + i);
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
	
	public void createPaints(){
		p1 = new Paint();
		p2 = new Paint();
		p3 = new Paint();
		p4 = new Paint();
		p5 = new Paint();
		p6 = new Paint();
		p7 = new Paint();
	}
	
	public static void setPaintColors(){
		p1.setColor(Color.BLUE);
		p2.setColor(Color.GREEN);
		p3.setColor(Color.RED);
		p4.setColor(Color.YELLOW);
		p5.setColor(Color.LTGRAY);
		p6.setColor(Color.CYAN);
		p7.setColor(Color.GRAY);
	}
	
	public static void setSelectedPaintColor(Paint p){
		p.setColor(Color.BLACK);
	}
	
	public static void setUnselectedPaintColor(Paint p){
		int col = 0;
		if      (p == p1) col = Color.BLUE;
		else if (p == p2) col = Color.GREEN;
		else if (p == p3) col = Color.RED;
		else if (p == p4) col = Color.YELLOW;
		else if (p == p5) col = Color.LTGRAY;
		else if (p == p6) col = Color.CYAN;
		else if (p == p7) col = Color.GRAY;
//		p.setColor(col);
	}
}
