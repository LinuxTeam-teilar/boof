package com.vasilakos.boof;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Boof extends Activity {

	public MyCount counter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = this.getIntent().getExtras();
		Integer players = b.getInt("players");
		Integer song = b.getInt("song");
		Log.d("debug", "players : " + players.toString());
		Log.d("debug", "song    : " + song.toString());
		
		 Panel p = new Panel(this, players);

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
