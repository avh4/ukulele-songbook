package net.avh4.music.songbook;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LyricsRendererTest {

	@Test
	public void testSingleLineNoChords() {
		assertThat(
				LyricsRenderer.format("Single line"),
				is("<div class=\"stanza\"><div class=\"line\">Single line</div></div>"));
	}

	@Test
	public void testLineWithChords() {
		assertThat(
				LyricsRenderer.format("[Gm] Line with [C] chords"),
				is("<div class=\"stanza\"><div class=\"line\"><span class=\"chord\">Gm</span>Line with <span class=\"chord\">C</span>chords</div></div>"));
	}

	@Test
	public void testOneStanza() {
		assertThat(
				LyricsRenderer
						.format("[Gm] Line with [C] chords\n[F7] Second line with [CM7] chords\n"),
				is("<div class=\"stanza\"><div class=\"line\"><span class=\"chord\">Gm</span>Line with <span class=\"chord\">C</span>chords</div>"
						+ "<div class=\"line\"><span class=\"chord\">F7</span>Second line with <span class=\"chord\">CM7</span>chords</div></div>"));
	}

	@Test
	public void testTwoStanzas() {
		assertThat(
				LyricsRenderer.format("One\nTwo\n\nThree\nFour\n"),
				is("<div class=\"stanza\"><div class=\"line\">One</div><div class=\"line\">Two</div></div>"
						+ "<div class=\"stanza\"><div class=\"line\">Three</div><div class=\"line\">Four</div></div>"));
	}

	@Test
	public void testFirstSection() {
		assertThat(
				LyricsRenderer.format("Verse 1:\nOne\nTwo"),
				is("<h2>Verse 1</h2><div class=\"stanza\"><div class=\"line\">One</div><div class=\"line\">Two</div></div>"));
	}

	@Test
	public void testMiddleSection() {
		assertThat(
				LyricsRenderer.format("One\nTwo\n\nChorus:\nThree\nFour\n"),
				is("<div class=\"stanza\"><div class=\"line\">One</div><div class=\"line\">Two</div></div>"
						+ "<h2>Chorus</h2>"
						+ "<div class=\"stanza\"><div class=\"line\">Three</div><div class=\"line\">Four</div></div>"));

	}

	@Test
	public void testOtherSections() {
		assertThat(
				LyricsRenderer
						.format("Intro:\n\nOne\nTwo\n\nEnding:\nThree\nFour\n"),
				is("<h2>Intro</h2>"
						+ "<div class=\"stanza\"><div class=\"line\">One</div><div class=\"line\">Two</div></div>"
						+ "<h2>Ending</h2>"
						+ "<div class=\"stanza\"><div class=\"line\">Three</div><div class=\"line\">Four</div></div>"));

	}

	@Test
	public void testRepeatedChorus() {
		assertThat(
				LyricsRenderer
						.format("Chorus:\n\nOne\nTwo\n\nChorus\n\nThree\nFour\n"),
				is("<h2>Chorus</h2>"
						+ "<div class=\"stanza\"><div class=\"line\">One</div><div class=\"line\">Two</div></div>"
						+ "<h2 class=\"repeat\">Chorus</h2>"
						+ "<div class=\"stanza\"><div class=\"line\">Three</div><div class=\"line\">Four</div></div>"));

	}
}
