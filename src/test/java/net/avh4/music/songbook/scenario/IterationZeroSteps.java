package net.avh4.music.songbook.scenario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JFrame;

import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;

public class IterationZeroSteps extends SongbookSteps {

	@When("I launch the application")
	public void launchApp() {
		startApp();
	}

	@Then("I should see the application window")
	public void assertApplicationWindow() {
		Object window = app.getMainWindow();
		assertThat(window, notNullValue());
		assertThat(window, is(JFrame.class));
		assertThat(((JFrame) window).isVisible(), is(true));
	}

	@Then("I should see the appropriate rendered page")
	public void verifyRenderedPage() throws IOException {
		verifyRenderedPage("<html>Ukulele Songbook</html>");
	}

}
