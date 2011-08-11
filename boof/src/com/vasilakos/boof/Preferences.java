package com.vasilakos.boof;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferences extends PreferenceActivity {

	public Integer Color = 0xff000000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		Preference bgColor = (Preference) findPreference("bgColor");

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		Color = prefs.getInt("bgColor", 0xff000000);

		bgColor.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {
				final ColorPickerDialog d = new ColorPickerDialog(
						Preferences.this, Color);
				d.setAlphaSliderVisible(false);

				d.setButton(getResources().getString(R.string.ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Color = d.getColor();
								SharedPreferences customSharedPreference = getSharedPreferences(
										"myCustomSharedPrefs",
										Activity.MODE_PRIVATE);
								SharedPreferences.Editor editor = customSharedPreference
										.edit();
								editor.putInt("bgColor", Color);
								editor.commit();
								Log.d("Pref : ", "Just saved color : " + Color);
							}
						});

				d.setButton2(getResources().getString(R.string.cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				d.show();

				return true;
			}

		});
	}
}
