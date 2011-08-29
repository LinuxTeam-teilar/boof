package com.vasilakos.boof;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.Log;
import android.view.View;

public class LyricsCircleView extends View {
	public Integer w, h;
	public Paint paint;
	public Path circle;

	public LyricsCircleView(Context context, int width, int height) {
		super(context);
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
		canvas.drawTextOnPath(
				"Here are the lyrics of the selected song that will go round the cycle!",
				circle, 0, 20, paint);
		super.onDraw(canvas);
	}

}
