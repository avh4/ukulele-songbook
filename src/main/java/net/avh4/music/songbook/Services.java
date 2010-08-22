package net.avh4.music.songbook;

import java.io.IOException;
import java.io.Writer;

public interface Services {

	public Writer getTempHtmlFile() throws IOException;

	public void openTempHtmlFile() throws IOException;

}
