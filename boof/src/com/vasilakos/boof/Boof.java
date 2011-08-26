package com.vasilakos.boof;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

public class Boof extends Activity {

	public MyCount counter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = this.getIntent().getExtras();
		Integer players = b.getInt("players");
		Integer song = b.getInt("song");
		Display display = getWindowManager().getDefaultDisplay();
		
		Panel p = new Panel(this, players, display.getWidth(), display.getHeight());

		setContentView(p);

		counter = new MyCount(10000, 100, p, players, getBaseContext());
		counter.start();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		 counter.cancel();

	}
}
