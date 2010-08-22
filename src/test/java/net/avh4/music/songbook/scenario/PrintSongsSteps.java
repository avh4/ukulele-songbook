package net.avh4.music.songbook.scenario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;

public class PrintSongsSteps extends SongbookSteps {

	@Given("that a song has been entered and selected")
	public void enterSong() {
		startApp();
		assertThat(app.getSelectedSong(), notNullValue());
	}

	@When("I ask to print the song")
	public void actionPrint() {
		app.actionPrint();
	}

	@Then("I see the song in a printable format")
	public void verifyPrintedSong() throws IOException {
		verifyRenderedPage("[C] Lyrics to the [F] tune of [G7] this [C] song");
	}

}
