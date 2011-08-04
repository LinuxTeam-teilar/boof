package com.vasilakos.boof;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Boof extends Activity {
	
	TextView noOfPlayersEt;

	MyCount counter;
	public static Integer size;

	@Override
	public void onStop() {
		super.onStop();

//		counter.cancel();
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
		noOfPlayersEt = (TextView) findViewById(R.id.numberOfPlayersTv);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ubscript.ttf");  
		txt.setTypeface(font); 
		noOfPlayersEt.setTypeface(font);

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
	
	public void plusButtonClicked(View v){
		Integer a = Integer.parseInt(noOfPlayersEt.getText().toString());
		if (a < 9){
			a++;
			noOfPlayersEt.setText(a.toString());
		}
	}
	
	public void minusButtonClicked(View v){
		Integer a = Integer.parseInt(noOfPlayersEt.getText().toString());
		if (a > 2){
			a--;
			noOfPlayersEt.setText(a.toString());
		}
	}

}