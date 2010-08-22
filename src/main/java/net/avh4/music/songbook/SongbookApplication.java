package net.avh4.music.songbook;

import javax.swing.JFrame;

public class SongbookApplication {

	public static void main(String args[]) {
		new SongbookApplication().start();
	}

	private final JFrame window;

	public SongbookApplication() {
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

}
