package net.avh4.music.songbook;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SwingSongbookWindow extends JFrame implements SongbookWindowView,
		ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton buttonPrint = null;
	private final Actions handler;
	private JTextArea textSong = null;

	/**
	 * This is the default constructor
	 */
	public SwingSongbookWindow(Actions handler) {
		super();
		this.handler = handler;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getButtonPrint(), BorderLayout.SOUTH);
			jContentPane.add(getTextSong(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes buttonPrint
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getButtonPrint() {
		if (buttonPrint == null) {
			buttonPrint = new JButton();
			buttonPrint.setText("Print");
			buttonPrint.setName("print");
			buttonPrint.addActionListener(this);
		}
		return buttonPrint;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonPrint) {
			handler.actionPrint();
		}
	}

	/**
	 * This method initializes textSong
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTextSong() {
		if (textSong == null) {
			textSong = new JTextArea();
			textSong.setName("song");
		}
		return textSong;
	}

	public String getSongText() {
		return getTextSong().getText();
	}

	public void setSongText(String string) {
		getTextSong().setText(string);
	}

}
