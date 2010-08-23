package net.avh4.music.songbook;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Song {

	private final String title;
	private final String[] chords;
	private final String lyrics;

	public Song(String songText) {
		if (songText.split("\n").length < 2) {
			title = null;
			chords = findChords(songText);
			lyrics = LyricsRenderer.format(songText);
		} else {
			title = songText.split("\n")[0];
			chords = findChords(songText);
			lyrics = LyricsRenderer.format(songText.split("\n", 2)[1]);
		}
	}

	private String[] findChords(String songText) {
		ArrayList<String> chords = new ArrayList<String>();
		Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
		Matcher m = p.matcher(songText);
		while (m.find()) {
			String chord = m.group(1);
			if (!chords.contains(chord)) {
				chords.add(chord);
			}
		}
		return chords.toArray(new String[chords.size()]);
	}

	public String getTitle() {
		return title;
	}

	public String[] getChords() {
		return chords;
	}

	public String getFormattedLyrics() {
		return lyrics;
	}

}
