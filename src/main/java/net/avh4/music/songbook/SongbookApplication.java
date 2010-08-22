package net.avh4.music.songbook;

import java.io.IOException;
import java.io.Writer;

public class SongbookApplication implements SongbookWindowView.Actions {

	public static void main(String args[]) {
		new SongbookApplication(new SwingServices()).start();
	}

	private final SongbookWindowView window;
	private final Services services;

	public SongbookApplication(Services services) {
		this.services = services;
		window = new SwingSongbookWindow(this);
	}

	public SongbookWindowView getMainWindow() {
		return window;
	}

	public void start() {
		window.showWindow();
	}

	public void actionPrint() {
		try {
			Writer w = services.getTempHtmlFile();
			w.append(getSelectedSong());
			w.flush();
			w.close();
			services.printFile(w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getSelectedSong() {
		return window.getSongText();
	}

	public void setSong(String string) {
		window.setSongText(string);
	}
}
