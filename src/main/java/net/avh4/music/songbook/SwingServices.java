package net.avh4.music.songbook;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SwingServices implements Services {

	private File tempFile = null;

	public Writer getTempHtmlFile() throws IOException {
		tempFile = File.createTempFile("uke", ".html");
		tempFile.deleteOnExit();
		FileWriter writer = new FileWriter(tempFile);
		return writer;
	}

	public void openTempHtmlFile() throws IOException {
		if (tempFile == null) {
			throw new RuntimeException(
					"Asked to display an HTML file in the browser, "
							+ "but no HTML files have been rendered.");
		} else {
			Desktop.getDesktop().browse(tempFile.toURI());
		}
	}

}
