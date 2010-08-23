package net.avh4.music.songbook;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.Arrays;

import org.junit.Test;

public class SongTest {

	@Test
	public void testTitle() {
		Song song = new Song("Song's Title\n\nSome lyrics\n");
		assertThat(song.getTitle(), is("Song's Title"));
	}

	@Test
	public void testChords() {
		Song song = new Song(
				"Song with chords\n\n[Gm] Words [Cm] words [D7]\n[Bb7] More words [F+] words [CM7]\n");
		assertThat(Arrays.asList(song.getChords()), hasItems("Gm", "Cm", "D7",
				"Bb7", "F+", "CM7"));
	}

	@Test
	public void testLyrics() {
		Song song = new Song(
				"Song with chords\n\n[Gm] Words [Cm] words [D7]\n[Bb7] More words [F+] words [CM7]\n");
		assertThat(
				song.getFormattedLyrics(),
				is("<div class=\"stanza\"><div class=\"line\"><span class=\"chord\">Gm</span>Words <span class=\"chord\">Cm</span>words <span class=\"chord\">D7</span></div><div class=\"line\"><span class=\"chord\">Bb7</span>More words <span class=\"chord\">F+</span>words <span class=\"chord\">CM7</span></div></div>"));
	}

	@Test
	public void testShortSongWithNoTitle() {
		Song song = new Song("[Bbm] Test song on one line\n");
		assertThat(song.getTitle(), nullValue());
		assertThat(
				song.getFormattedLyrics(),
				is("<div class=\"stanza\"><div class=\"line\"><span class=\"chord\">Bbm</span>Test song on one line</div></div>"));
	}

}
