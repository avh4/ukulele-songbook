package net.avh4.music.songbook.scenario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;

public class PrintSongsSteps extends SongbookSteps {

	@Given("that a song has been entered and selected")
	public void enterSong() {
		startApp();
		app.setSong("[C] Lyrics to the [F] tune of [G7] this [C] song");
		assertThat(app.getSelectedSong(), notNullValue());
	}

	@Then("I see the song in a printable format")
	public void verifyPrintedSong() throws IOException {
		verifyRenderedPage("Test Song B.html");
	}

}
