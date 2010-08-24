package net.avh4.music.songbook;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
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

	@Test
	public void testFloatingChords() throws IOException {
		Song expected = new Song(
				getResourceAsString("Floating Chords Expected.song"));
		Song actual = new Song(
				getResourceAsString("Floating Chords Input.song"));
		assertThat(actual.getFormattedLyrics(), is(expected
				.getFormattedLyrics()));
	}

	@Test
	public void testInlineHeaders() throws IOException {
		Song expected = new Song(
				getResourceAsString("Inline Headers Expected.song"));
		Song actual = new Song(getResourceAsString("Inline Headers Input.song"));
		assertThat(actual.getFormattedLyrics(), is(expected
				.getFormattedLyrics()));
	}

	private String getResourceAsString(String resource) throws IOException {
		InputStream stream = getClass().getResourceAsStream(resource);
		if (stream == null)
			fail("Could not find resource " + resource);
		String songData = IOUtils.toString(stream);
		return songData;
	}

}
