package com.vasilakos.boof;

import android.content.Context;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ColorPickerPreference extends Preference {
	
	public ColorPickerPreference(Context context) {
		super(context);
	}

	@Override
	 protected View onCreateView(ViewGroup parent){
	    LayoutInflater inflater =  (LayoutInflater)getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	    View view = inflater.inflate(R.layout.color_picker_preference, parent, false);
		
		return view;
	}
}
