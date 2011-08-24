package com.vasilakos.boof;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;

public class ColorPickerPreference extends Preference {
	public Integer Color = 0xff000000;
	public String player;

	public ColorPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWidgetLayoutResource(R.layout.color_picker_preference);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ColorPickerPreference);

		player = a.getString(R.styleable.ColorPickerPreference_player);
		Color = a.getInteger(R.styleable.ColorPickerPreference_defaultColor,
				0xff000000);
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
						editor.putInt(player, Color);
						editor.commit();
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
		Color = prefs.getInt(player, Color);
	}
}
