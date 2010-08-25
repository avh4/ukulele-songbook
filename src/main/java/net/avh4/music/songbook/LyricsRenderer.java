package net.avh4.music.songbook;

import java.util.Scanner;

public class LyricsRenderer {

	private static final String CHORD_REGEX = "[ABCDEFG][b#]?[m+4567913susaug]*";
	private static boolean inStanza;
	private static String mergeline;

	public static String format(String string) {
		inStanza = false;
		StringBuilder sb = new StringBuilder();
		String[] lines = string.split("\n");
		mergeline = null;
		for (String line : lines) {
			if (line.equals("")) {
				// Start new stanza
				clearMergeline(sb);
				endStanza(sb);
			} else if (isSectionLine(line)) {
				clearMergeline(sb);
				endStanza(sb);
				sb.append(processSectionName(line));
			} else if (isFloatingChordsLine(line)) {
				clearMergeline(sb);
				mergeline = line;
			} else {
				// Process the line
				String lyrics = line;
				if (!inStanza) {
					String section = getInlineSection(lyrics);
					if (section != null) {
						sb.append(processSectionName(section));
						lyrics = lyrics.substring(section.length()).trim();
					}
					inStanza(sb);
				}
				sb.append("<div class=\"line\">");
				if (mergeline != null) {
					sb.append(mergeLines(mergeline, line));
					mergeline = null;
				} else {
					sb.append(processChords(lyrics));
				}
				sb.append("</div>");
			}
		}
		sb.append("</div>");
		return sb.toString();
	}

	private static void clearMergeline(StringBuilder sb) {
		if (mergeline != null) {
			inStanza(sb);
			sb.append("<div class=\"line\">");
			sb.append(mergeLines(mergeline, "").trim());
			sb.append("</div>");
			mergeline = null;
		}
	}

	private static void inStanza(StringBuilder sb) {
		if (!inStanza) {
			sb.append("<div class=\"stanza\">");
			inStanza = true;
		}
	}

	private static void endStanza(StringBuilder sb) {
		if (inStanza) {
			sb.append("</div>");
			inStanza = false;
		}
	}

	private static String getInlineSection(String line) {
		if (line.contains(":")) {
			String sectionPrefix = line.substring(0, line.indexOf(':') + 1);
			if (isSectionLine(sectionPrefix)) {
				return sectionPrefix;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private static String mergeLines(String chords, String lyrics) {
		Scanner scanChords = new Scanner(chords);
		Scanner scanWords = new Scanner(lyrics);
		StringBuffer merge = new StringBuffer();
		boolean moreWords;
		if (scanWords.hasNext()) {
			moreWords = true;
			scanWords.next();
		} else {
			moreWords = false;
		}
		while (scanChords.hasNext()) {
			scanChords.next();
			int chordIndex = scanChords.match().start();
			while (moreWords && chordIndex > scanWords.match().start()) {
				merge.append(scanWords.match().group() + " ");
				if (scanWords.hasNext()) {
					scanWords.next();
				} else {
					moreWords = false;
				}
			}
			final String possibleChord = scanChords.match().group();
			if (possibleChord.matches(CHORD_REGEX + "\\*?")) {
				merge.append(processChord(possibleChord) + " ");
			} else {
				merge.append(possibleChord + " ");
			}
		}
		if (moreWords) {
			merge.append(scanWords.match().group());
			while (scanWords.hasNext()) {
				merge.append(" " + scanWords.next());
			}
		}
		return processChords(merge.toString());
	}

	private static String processChord(final String chord) {
		if (chord.endsWith("*")) {
			return "[" + chord.substring(0, chord.length() - 1) + "]" + "*";
		} else {
			return "[" + chord + "]";
		}
	}

	private static boolean isFloatingChordsLine(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter("[ \t*]+");
		int wordCount = 0;
		int chordCount = 0;
		int tokenCount = 0;
		while (scan.hasNext()) {
			String token = scan.next();
			tokenCount++;
			if (token.matches("^" + CHORD_REGEX + "$")) {
				chordCount++;
			} else if (token.matches(".*[a-zA-Z].*")) {
				wordCount++;
			}
		}
		if (chordCount > wordCount && wordCount < 2) {
			return true;
		} else {
			return false;
		}
	}

	private static String processSectionName(String string) {
		string = string.trim();
		final boolean endsWithColon = string.endsWith(":");
		if (endsWithColon) {
			string = string.substring(0, string.length() - 1);
		}
		if (string.toLowerCase().contains("chorus") && !endsWithColon) {
			return "<h2 class=\"repeat\">" + string + "</h2>";
		} else {
			return "<h2>" + string + "</h2>";
		}
	}

	private static boolean isSectionLine(String line) {
		if (line.trim().endsWith(":")
				&& line.replaceAll("[^a-zA-Z]", "").length() < 10) {
			return true;
		}
		// If there is a section identifier but there are also lyrics, return
		// false.
		// This line will be handled later by processInlineSection()
		if (line.contains(":")
				&& (line.trim().length() - line.indexOf(':') > 2)) {
			return false;
		}
		if (line.toLowerCase().contains("verse")
				&& line.replaceAll("[^a-zA-Z]", "").length() < 10) {
			return true;
		} else if (line.toLowerCase().contains("chorus")
				&& line.replaceAll("[^a-zA-Z]", "").length() < 10) {
			return true;
		} else {
			return false;
		}
	}

	private static String processChords(String string) {
		return string.replaceAll("\\[([^\\]]+)\\] *",
				"<span class=\"chord\">$1</span>");
	}
}
