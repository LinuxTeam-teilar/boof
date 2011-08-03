package com.vasilakos.boof;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

public class Boof extends Activity {

	MyCount counter;
	public static Integer size;

	@Override
	public void onStop() {
		super.onStop();

		counter.cancel();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setSize();

		Panel p = new Panel(this);

		setContentView(p);

		counter = new MyCount(8000, 100, p);
		counter.start();
	}
	
	public void setSize(){
		Display display = getWindowManager().getDefaultDisplay();
		Integer width = display.getWidth();
		Integer height = display.getHeight();
		if (width < height)
			size = width;
		else
			size = height;
	}
	
	public static Integer getScreenSize(){
		return size;
	}

}