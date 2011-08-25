package com.vasilakos.boof;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Instructions extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instructions);
		
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.logo_animation);

		Typeface font = Typeface.createFromAsset(getAssets(),
				"fonts/ubscript.ttf");
		TextView text = (TextView) findViewById(R.id.instructionsText);
		text.setTypeface(font);
		text.startAnimation(anim);
	}
}
