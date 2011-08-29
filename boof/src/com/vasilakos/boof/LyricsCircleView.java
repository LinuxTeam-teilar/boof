package com.vasilakos.boof;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.view.View;

public class LyricsCircleView extends View {
	public Integer w, h;
	public Paint paint;
	public Path circle;
	public String text;

	public LyricsCircleView(Context context, int width, int height, String txt) {
		super(context);
		text = txt;
		w = width / 2;
		h = height / 2;
		paint = new Paint();
		circle = new Path();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		circle.addCircle(w, h, w, Direction.CW);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLUE);
		paint.setTextSize(16);
		paint.setAntiAlias(true);
		canvas.drawTextOnPath(text, circle, 0, 20, paint);
		super.onDraw(canvas);
	}

}
