package com.vasilakos.boof;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Information extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);

		TextView link = (TextView) findViewById(R.id.site);
		TextView cont = (TextView) findViewById(R.id.contact);

		link.setText("<a href='" + link.getText() + "'>" + link.getText()
				+ "</a>");
		cont.setText(cont.getText()
				+ " <a href='mailto:forfolias@gmail.com'></a>");
		Linkify.addLinks(cont, Pattern.compile("forfolias@gmail.com"),
				"http://");
		Linkify.addLinks(link,
				Pattern.compile(getResources().getString(R.id.site)), "http://");
		cont.setText(Html.fromHtml(cont.getText()
				+ "<a href='mailto:forfolias@gmail.com'>forfolias@gmail.com</a>"));
		link.setText(Html.fromHtml((String) link.getText()));
		cont.setMovementMethod(LinkMovementMethod.getInstance());
		link.setMovementMethod(LinkMovementMethod.getInstance());

		ImageView logo = (ImageView) findViewById(R.id.infoImage);
		Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.logo_animation);
		logo.startAnimation(anim);
		logo.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View v) {
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(getBaseContext());
				Boolean soundEffects = prefs.getBoolean("soundEffects", true);
				int counter = prefs.getInt("boofs", 0);

				if (soundEffects) {
					MediaPlayer mp = MediaPlayer.create(Information.this,
							R.raw.omg);
					mp.setOnCompletionListener(new OnCompletionListener() {

						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					mp.start();
				}

				Toast toast = Toast.makeText(
						getApplicationContext(),
						getResources().getString(R.string.noOfBoofs) + " "
								+ counter + " "
								+ getResources().getString(R.string.boof),
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 75, 35);
				toast.show();
				return true;
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, R.string.licence).setIcon(
				R.drawable.ic_menu_info_details);
		menu.add(0, 2, 0, R.string.credits).setIcon(
				R.drawable.ic_menu_allfriends);
		menu.add(0, 3, 0, R.string.donate).setIcon(R.drawable.ic_menu_star);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
			alertbox.setMessage(getResources().getString(R.string.licenceText))
					.setTitle(getResources().getString(R.string.licence));
			alertbox.setNeutralButton(getResources().getString(R.string.back),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}
					});
			alertbox.setIcon(R.drawable.ic_menu_info_details);
			alertbox.show();
			return true;
		case 2:
			Intent info = new Intent(this, com.vasilakos.boof.Credits.class);
			startActivity(info);
			return true;
		case 3:
			Intent browse = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=M5HYBKFQYS84S&lc=GR&item_name=Donation%20to%20Boof%20application&currency_code=EUR&bn=PP%2dDonationsBF%3abtn_donate_LG%2egif%3aNonHosted"));
			startActivity(browse);
			return true;
		}
		return false;
	}

}
