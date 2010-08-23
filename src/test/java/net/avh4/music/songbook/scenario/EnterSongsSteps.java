package net.avh4.music.songbook.scenario;

import java.io.IOException;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;

public class EnterSongsSteps extends SongbookSteps {

	@Override
	@Given("that the application has been started")
	public void startApp() {
		super.startApp();
	}

	@When("I enter a song \"[Bb] I've been a-ramblin\"")
	public void enterSong() {
		window.textBox("song").enterText("[Bb] I've been a-ramblin");
	}

	@Then("the currently selected song is \"[Bb] I've been a-ramblin\"")
	public void verifySong() throws IOException {
		actionPrint();
		verifyRenderedPage("Test Song A.html");
	}

}
