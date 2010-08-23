package net.avh4.music.songbook;

import static net.avh4.test.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class SongRendererTest {

	private Song song;

	@Before
	public void setUp() {
		song = mock(Song.class);
	}

	@Test
	public void testHeader() throws IOException {
		assertThat(SongRenderer.format(song), isApproved());
	}

	@Test
	public void testTitle() {
		when(song.getTitle()).thenReturn("Title of the song (8f7bc)");
		assertThat(SongRenderer.format(song),
				containsString("Title of the song (8f7bc)"));
	}

	@Test
	public void testChords() {
		when(song.getChords()).thenReturn(new String[] { "Gm", "C7", "Fsus" });
		final String rendered = SongRenderer.format(song);
		assertThat(rendered,
				containsString("<div class=\"fingering jtab\"> Gm </div>"));
		assertThat(rendered,
				containsString("<div class=\"fingering jtab\"> C7 </div>"));
		assertThat(rendered,
				containsString("<div class=\"fingering jtab\"> Fsus </div>"));
	}

	@Test
	public void testLyrics() {
		when(song.getFormattedLyrics()).thenReturn("<div>Lyrics (9a882)</div>");
		assertThat(SongRenderer.format(song),
				containsString("<div>Lyrics (9a882)</div>"));
	}
}
