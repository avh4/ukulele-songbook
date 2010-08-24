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
	public void testFirstSectionInline() {
		assertThat(LyricsRenderer.format("Verse 1: One\nTwo"),
				is(LyricsRenderer.format("Verse 1:\nOne\nTwo")));
	}

	@Test
	public void testMiddleSectionInline() {
		assertThat(LyricsRenderer.format("One\nTwo\n\nChorus: Three\nFour\n"),
				is(LyricsRenderer.format("One\nTwo\n\nChorus:\nThree\nFour\n")));
	}

	@Test
	public void testOtherSectionsInline() {
		assertThat(
				LyricsRenderer
						.format("Intro: One\nTwo\n\nEnding: Three\nFour\n"),
				is(LyricsRenderer
						.format("Intro:\n\nOne\nTwo\n\nEnding:\nThree\nFour\n")));
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

	@Test
	public void testFloatingChords() {
		assertThat(LyricsRenderer.format("F     \nFreedom\n"),
				is(LyricsRenderer.format("[F] Freedom")));
	}

	@Test
	public void testFloatingChords2() {
		assertThat(LyricsRenderer.format("F       Bb\nFreedom reigns\n"),
				is(LyricsRenderer.format("[F] Freedom [Bb] reigns")));
	}

	/**
	 * Words trail the end of the chords line.
	 */
	@Test
	public void testFloatingChordsTrailingWords() {
		assertThat(
				LyricsRenderer
						.format("F       Bb\nFreedom reigns on and on and on and on\n"),
				is(LyricsRenderer
						.format("[F] Freedom [Bb] reigns on and on and on and on")));
	}

	/**
	 * The first chord is offset from the start of the line.
	 */
	@Test
	public void testFloatingChordsOffset() {
		assertThat(LyricsRenderer.format("  F     Bb\nFreedom reigns\n"),
				is(LyricsRenderer.format("Freedom [F] [Bb] reigns")));
	}

	/**
	 * The lyrics are offset from the start of the line.
	 */
	@Test
	public void testFloatingChordsLyricsOffset() {
		assertThat(LyricsRenderer
				.format("F D C       Bb\n    Freedom reigns\n"),
				is(LyricsRenderer.format("[F] [D] [C] Freedom [Bb] reigns")));
	}

	/**
	 * A chord in the middle of a word should come after the word.
	 */
	@Test
	public void testFloatingChordsMidWordChord() {
		assertThat(LyricsRenderer.format("F   D   Bb\nFreedom reigns\n"),
				is(LyricsRenderer.format("[F] Freedom [D] [Bb] reigns")));
	}

	/**
	 * A chord trailing the end of the line.
	 */
	@Test
	public void testFloatingChordsTrailingChord() {
		assertThat(LyricsRenderer
				.format("F       Bb       C\nFreedom reigns\n"),
				is(LyricsRenderer.format("[F] Freedom [Bb] reigns [C]")));
	}

	/**
	 * Several chords trailing the end of the line.
	 */
	@Test
	public void testFloatingChordsTrailingChords() {
		assertThat(LyricsRenderer
				.format("F       Bb  C D Eb\nFreedom reigns\n"),
				is(LyricsRenderer
						.format("[F] Freedom [Bb] reigns [C] [D] [Eb]")));
	}

	/**
	 * The chords line starts with a chord other than [F].
	 */
	@Test
	public void testFloatingChordsOtherChords() {
		assertThat(LyricsRenderer.format("C#      A\nFreedom reigns\n"),
				is(LyricsRenderer.format("[C#] Freedom [A] reigns")));
	}

}
