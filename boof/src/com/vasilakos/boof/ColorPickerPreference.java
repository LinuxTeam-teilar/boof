package com.vasilakos.boof;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ColorPickerPreference extends Preference {
	public Integer Color = 0xff000000;

	public ColorPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWidgetLayoutResource(R.layout.color_picker_preference);
	}

	@Override
	protected void onBindView(View view) {
		super.onBindView(view);

		View v = (View) view.findViewById(R.id.colorPickerBox);
		if (v != null) {
			v.setBackgroundColor(Color);
		}
	}

	@Override
	protected void onClick() {
		final ColorPickerDialog d = new ColorPickerDialog(getContext(), Color);
		d.setAlphaSliderVisible(false);

		d.setButton(getContext().getResources().getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Color = d.getColor();
						SharedPreferences customSharedPreference = getSharedPreferences();
						SharedPreferences.Editor editor = customSharedPreference
								.edit();
						editor.putInt("bgColor", Color);
						editor.commit();
						Log.d("PICKER onClick: ", "color : " + Color);
						notifyChanged();
					}
				});

		d.setButton2(getContext().getResources().getString(R.string.cancel),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		d.show();
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		return a.getInteger(index, 0xff000000);
	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		SharedPreferences prefs = getSharedPreferences();
		Color = prefs.getInt("bgColor", 0xff000000);
	}
}