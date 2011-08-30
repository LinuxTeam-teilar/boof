package com.vasilakos.boof;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class Songs {

	public static ArrayList<Song> songs;

	public Songs() {
		songs = new ArrayList<Song>();
	}

	public static void initialize(Context context) {
		if (songs == null)
			songs = new ArrayList<Song>();

		XmlResourceParser parser = context.getResources().getXml(R.xml.songs);

		Song song =null;

		try {
			int eventType = parser.getEventType();
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				if (eventType == XmlResourceParser.START_TAG) {
					if (parser.getName().equals("song")) {
						song = new Song();
						song.setSongId(Integer.parseInt(parser
								.getAttributeValue(null, "id")));
						song.setSongName(parser.getAttributeValue(null, "name"));
						song.setSongChanges(parser.getAttributeValue(null,
								"changes"));
						song.setSongLyrics(parser.getAttributeValue(null,
								"lyrics"));
						song.setSongAngle(Integer.parseInt(parser
								.getAttributeValue(null, "angle")));
						song.setSongSong(song.getSongId());
						song.setSongPicture(song.getSongId());
						songs.add(song);
					}
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getNumberOfSongs(){
		return songs.size();
	}
	
	public static Song getSongFromId(int id){
		for (int i = 0 ; i < songs.size() ; i++)
			if (songs.get(i).getSongId() == id)
				return songs.get(i);
		Log.d("koko", "returnimg null!");
		return null;
	}
	
}
