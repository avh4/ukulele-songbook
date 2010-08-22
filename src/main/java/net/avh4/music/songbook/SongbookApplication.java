package net.avh4.music.songbook;

import java.io.IOException;
import java.io.Writer;

import javax.swing.JFrame;

public class SongbookApplication {

	public static void main(String args[]) {
		new SongbookApplication(new SwingServices()).start();
	}

	private final JFrame window;
	private final Services services;

	public SongbookApplication(Services services) {
		this.services = services;
		window = new JFrame();
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

	public void showRenderedPage() {
		try {
			Writer w = services.getTempHtmlFile();
			w.append("<html>Ukulele Songbook</html>");
			w.flush();
			w.close();
			services.openTempHtmlFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
