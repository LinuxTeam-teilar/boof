package com.vasilakos.boof;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
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

		ImageView img = (ImageView) findViewById(R.id.infoImage);
		img.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View v) {
				Toast toast = Toast.makeText(getApplicationContext(), "Boof!",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 75, 35);
				toast.show();
				return true;
			}
		});
	}

	public void donateButtonClicked(View v) {
		Intent browse = new Intent(
				Intent.ACTION_VIEW,
				Uri.parse(""));
		startActivity(browse);
	}

	public void licenceButtonClicked(View v) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		alertbox.setMessage(getResources().getString(R.string.licenceText))
				.setTitle(getResources().getString(R.string.licence));
		alertbox.setNeutralButton(getResources().getString(R.string.back),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
		alertbox.show();
	}

}
