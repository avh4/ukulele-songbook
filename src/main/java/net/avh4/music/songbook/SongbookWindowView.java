package net.avh4.music.songbook;

public interface SongbookWindowView {

	public interface Actions {
		public void actionPrint();
	}

	public void showWindow();

	public String getSongText();

	public void setSongText(String string);

}
