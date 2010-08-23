package net.avh4.music.songbook;

public class LyricsRenderer {

	public static String format(String string) {
		boolean inStanza = false;
		StringBuilder sb = new StringBuilder();
		String[] lines = string.split("\n");
		for (String line : lines) {
			if (line.equals("")) {
				// Start new stanza
				if (inStanza) {
					sb.append("</div>");
					inStanza = false;
				}
			} else if (isSectionLine(line)) {
				// Start the new section
				if (inStanza) {
					sb.append("</div>");
					inStanza = false;
				}
				sb.append(processSectionName(line));
			} else {
				// Process the line
				if (!inStanza) {
					sb.append("<div class=\"stanza\">");
					inStanza = true;
				}
				sb.append("<div class=\"line\">");
				sb.append(processChords(line));
				sb.append("</div>");
			}
		}
		sb.append("</div>");
		return sb.toString();
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
