package com.vasilakos.boof;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.widget.TextView;

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
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);


		setSize();

//		Panel p = new Panel(this);

//		setContentView(p);
		setContentView(R.layout.main);
		
		TextView txt = (TextView) findViewById(R.id.instructionsTextView);  
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/mghobofun.ttf");  
		txt.setTypeface(font); 

//		counter = new MyCount(10000, 100, p);
//		counter.start();
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