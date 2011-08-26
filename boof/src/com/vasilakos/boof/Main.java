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
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {

	public TextView noOfPlayersEt;
	public MediaPlayer bgMusic, preview;
	public boolean soundEffects;
	public boolean music;
	public Integer bgColor, textColor;
	public Animation anim;
	public static Typeface font;

	Integer[] songs = { R.drawable.song0, R.drawable.song1, R.drawable.song2,
			R.drawable.song3, R.drawable.song4, R.drawable.song5,
			R.drawable.song6, R.drawable.song7, R.drawable.song8 };

	@Override
	public void onStop() {
		super.onStop();

		if (music) {
			if (bgMusic.isPlaying())
				bgMusic.release();
			if (preview.isPlaying())
				preview.release();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (soundEffects) {
			MediaPlayer mp = MediaPlayer.create(Main.this, R.raw.bye);
			mp.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			mp.start();
		}
	}

	public void onStart() {
		super.onStart();
		
		readPreferences();

		setRandomTextColor();

		RelativeLayout mainBg = (RelativeLayout) findViewById(R.id.mainbg);
		mainBg.setBackgroundColor(bgColor);

		Gallery ga = (Gallery) findViewById(R.id.song_select_gallery);
		ga.setAdapter(new ImageAdapter(this));
		ga.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (soundEffects) {
					new Thread() {
						public void run() {
							MediaPlayer mp = MediaPlayer.create(Main.this,
									R.raw.flip);

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

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		ga.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (soundEffects) {
					MediaPlayer mp = MediaPlayer.create(Main.this, R.raw.fail);
					mp.setOnCompletionListener(new OnCompletionListener() {
						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					mp.start();
				}

				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.dialog_box,
						(ViewGroup) findViewById(R.id.dialogBoxLayout));
				TextView tdialogBoxTextView = (TextView) layout
						.findViewById(R.id.dialogBoxText);
				tdialogBoxTextView.setBackgroundColor(bgColor);
				tdialogBoxTextView.setTextColor(textColor);
				tdialogBoxTextView.setTypeface(font);

				Toast toastView = new Toast(getBaseContext());
				toastView.setView(layout);
				toastView.setGravity(Gravity.RIGHT | Gravity.TOP, 0, 0);
				toastView.setDuration(Toast.LENGTH_LONG);
				toastView.show();
			}
		});

		ga.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				preview = MediaPlayer.create(getApplicationContext(),
						R.raw.childrenplaying);

				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(getBaseContext());

				int milis = Integer.parseInt(prefs.getString("previewDuration",
						"5000"));
				if (milis == 0)
					milis = preview.getDuration();

				preview.start();

				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						preview.stop();
					}
				}, milis);

				return false;
			}
		});

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		if (prefs.getBoolean("randomSong", true))
			ga.setSelection(new Random().nextInt(songs.length), true);

		ImageView logo = null;
		logo = (ImageView) findViewById(R.id.logoImg);
		if (logo != null) {
			logo.startAnimation(anim);

			logo.setOnLongClickListener(new View.OnLongClickListener() {
				public boolean onLongClick(View v) {
					if (soundEffects) {
						new Thread() {
							public void run() {
								MediaPlayer mp = MediaPlayer.create(Main.this,
										R.raw.throat);

								mp.setOnCompletionListener(new OnCompletionListener() {

									public void onCompletion(MediaPlayer mp) {
										mp.release();
									}
								});
								mp.start();
							}
						}.start();
					}
					return false;
				}
			});
		}

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

	private void readPreferences() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		soundEffects = prefs.getBoolean("soundEffects", true);
		music = prefs.getBoolean("music", true);
		bgColor = prefs.getInt("bgColor", Color.BLACK);
		textColor = prefs.getInt("textColor", Color.WHITE);
		anim = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
		
		font = Typeface.createFromAsset(getAssets(),
				"fonts/ubscript.ttf");
		noOfPlayersEt = (TextView) findViewById(R.id.numberOfPlayersTv);
		TextView instrTv = (TextView) findViewById(R.id.instructionsTextView);
		instrTv.setTypeface(font);
		instrTv.setTextColor(textColor);
		instrTv.startAnimation(anim);
		noOfPlayersEt.setTypeface(font);
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
							.create(Main.this, R.raw.splash);

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
		Gallery ga = (Gallery) findViewById(R.id.song_select_gallery);
		int song = (int) ga.getSelectedItemId();
		if (song != 0) {
			if (soundEffects) {
				new Thread() {
					public void run() {
						MediaPlayer mp = MediaPlayer.create(Main.this,
								R.raw.splash);
						mp.setOnCompletionListener(new OnCompletionListener() {
							public void onCompletion(MediaPlayer mp) {
								mp.release();
							}
						});
						mp.start();
					}
				}.start();
			}

			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getBaseContext());
			int counter = prefs.getInt("boofs", 0);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putInt("boofs", ++counter);
			editor.commit();

			Intent view = new Intent(this, com.vasilakos.boof.Boof.class);
			Bundle b = new Bundle();
			b.putInt("players",
					Integer.parseInt(noOfPlayersEt.getText().toString()));

			b.putInt("song", song);

			view.putExtras(b);
			startActivity(view);
		} else {

			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.dialog_box,
					(ViewGroup) findViewById(R.id.dialogBoxLayout));
			TextView dialogBoxTextView = (TextView) layout
					.findViewById(R.id.dialogBoxText);
			dialogBoxTextView.setBackgroundColor(bgColor);
			dialogBoxTextView.setTextColor(textColor);

			dialogBoxTextView.setTypeface(font);
			dialogBoxTextView.setText(getResources().getString(
					R.string.noSelectedSong));
			dialogBoxTextView.setLineSpacing(5, 1);

			Toast toastView = new Toast(getBaseContext());
			toastView.setView(layout);
			toastView.setGravity(Gravity.RIGHT | Gravity.TOP, 0, 10);
			toastView.setDuration(Toast.LENGTH_LONG);
			toastView.show();

			if (soundEffects) {
				MediaPlayer mp = MediaPlayer.create(Main.this, R.raw.fail);
				mp.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
				mp.start();
			}
		}
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