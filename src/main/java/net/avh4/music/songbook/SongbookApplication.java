package net.avh4.music.songbook;

import java.io.IOException;
import java.io.Writer;

import javax.swing.JFrame;

public class SongbookApplication implements SongbookWindowView.Actions {

	public static void main(String args[]) {
		new SongbookApplication(new SwingServices()).start();
	}

	private final SwingSongbookWindow window;
	private final Services services;
	private final String selectedSong = "[C] Lyrics to the [F] tune of [G7] this [C] song";

	public SongbookApplication(Services services) {
		this.services = services;
		window = new SwingSongbookWindow(this);
	}

	public JFrame getMainWindow() {
		return window;
	}

	public void start() {
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPrint() {
		try {
			Writer w = services.getTempHtmlFile();
			w.append(selectedSong);
			w.flush();
			w.close();
			services.printFile(w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getSelectedSong() {
		return selectedSong;
	}
}
