package com.vasilakos.boof;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Boof extends Activity {

	TextView noOfPlayersEt;
	MediaPlayer bgMusic;
	boolean soundEffects;
	boolean music;
	Integer bgColor;

	Integer[] songs = { R.drawable.song0, R.drawable.song1, R.drawable.song2,
			R.drawable.song3, R.drawable.song4 };

	// MyCount counter;
	public static Integer size;

	@Override
	public void onStop() {
		super.onStop();
		if (music)
			bgMusic.release();
		// counter.cancel();

	}

	public void onStart() {
		super.onStart();

		getPrefs();

		TextView instrTv = (TextView) findViewById(R.id.instructionsTextView);
		noOfPlayersEt = (TextView) findViewById(R.id.numberOfPlayersTv);
		Typeface font = Typeface.createFromAsset(getAssets(),
				"fonts/ubscript.ttf");
		instrTv.setTypeface(font);
		noOfPlayersEt.setTypeface(font);
		setRandomTextColor();
		
		RelativeLayout mainBg = (RelativeLayout) findViewById(R.id.mainbg);
		mainBg.setBackgroundColor(bgColor);

		Gallery ga = (Gallery) findViewById(R.id.song_select_gallery);
		ga.setAdapter(new ImageAdapter(this));

		if (music) {
			bgMusic = MediaPlayer.create(this, R.raw.childrenplaying);
			bgMusic.setLooping(true);
			bgMusic.start();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);

		setSize();

		// Panel p = new Panel(this);

		// setContentView(p);

		// counter = new MyCount(10000, 100, p);
		// counter.start();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, R.string.instructions).setIcon(
				R.drawable.ic_menu_help);
		menu.add(0, 3, 0, R.string.preferences).setIcon(
				R.drawable.ic_menu_preferences);
		menu.add(0, 2, 0, R.string.info).setIcon(
				R.drawable.ic_menu_info_details);
		menu.add(0, 4, 0, R.string.donate).setIcon(R.drawable.ic_menu_star);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			instructionsButtonClicked();
			return true;
		case 2:
			infoButtonClicked();
			return true;
		case 3:
			settingsButtonClicked();
			return true;
		case 4:
			Intent browse = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=M5HYBKFQYS84S&lc=GR&item_name=Donation%20to%20Boof%20application&currency_code=EUR&bn=PP%2dDonationsBF%3abtn_donate_LG%2egif%3aNonHosted"));
			startActivity(browse);
			return true;
		}
		return false;
	}

	public void instructionsButtonClicked() {
		Intent help = new Intent(this, com.vasilakos.boof.Instructions.class);
		startActivity(help);
	}

	public void infoButtonClicked() {
		Intent info = new Intent(this, com.vasilakos.boof.Information.class);
		startActivity(info);
	}

	public void settingsButtonClicked() {
		Intent settingsActivity = new Intent(this,
				com.vasilakos.boof.Preferences.class);
		startActivity(settingsActivity);
	}

	private void getPrefs() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		soundEffects = prefs.getBoolean("soundEffects", true);
		music = prefs.getBoolean("music", true);
		
		SharedPreferences mySharedPreferences = getSharedPreferences(
                "myCustomSharedPrefs", Activity.MODE_PRIVATE);
		bgColor = mySharedPreferences.getInt("bgColor", 0xff000000);
		Log.d("Boof : ", "Read pref for color : " + bgColor);
	}

	public void setSize() {
		Display display = getWindowManager().getDefaultDisplay();
		Integer width = display.getWidth();
		Integer height = display.getHeight();
		if (width < height)
			size = width;
		else
			size = height;
	}

	public static Integer getScreenSize() {
		return size;
	}

	public void plusButtonClicked(View v) {
		Integer a = Integer.parseInt(noOfPlayersEt.getText().toString());
		if (a < 9) {
			playSplashSound();
			a++;
			noOfPlayersEt.setText(a.toString());
			setRandomTextColor();
		}
	}

	public void minusButtonClicked(View v) {
		Integer a = Integer.parseInt(noOfPlayersEt.getText().toString());
		if (a > 2) {
			playSplashSound();
			a--;
			noOfPlayersEt.setText(a.toString());
			setRandomTextColor();
		}
	}

	public void setRandomTextColor() {
		Random random = new Random();
		int randomColor = Color.rgb(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
		noOfPlayersEt.setTextColor(randomColor);
	}

	public void playSplashSound() {
		if (soundEffects) {
			new Thread() {
				public void run() {
					MediaPlayer mp = MediaPlayer
							.create(Boof.this, R.raw.splash);

					mp.setOnCompletionListener(new OnCompletionListener() {

						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					mp.start();
				}
			}.start();
		}
	}

	public void boofButtonClicked(View v) {
		new Thread() {
			public void run() {
				MediaPlayer mp = MediaPlayer.create(Boof.this, R.raw.splash);
				mp.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
				mp.start();
			}
		}.start();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		int counter = prefs.getInt("boofs", 0);
		SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("boofs", ++counter);
        editor.commit();
	}

	public class ImageAdapter extends BaseAdapter {

		private Context ctx;

		public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			ta.recycle();
		}

		public int getCount() {

			return songs.length;
		}

		public Object getItem(int arg0) {

			return arg0;
		}

		public long getItemId(int arg0) {

			return arg0;
		}

		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ImageView iv = new ImageView(ctx);
			iv.setImageResource(songs[arg0]);
			iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			// iv.setLayoutParams(new Gallery.LayoutParams(144,50));
			return iv;
		}
	}
}