package net.avh4.music.songbook.scenario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.swing.JFrame;

import net.avh4.music.songbook.SongbookApplication;

import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

public class IterationZeroSteps extends Steps {

	private SongbookApplication app = null;

	@When("I launch the application")
	public void startApp() {
		app = new SongbookApplication();
		app.start();
		assertThat("Could not launch the application", app, notNullValue());
	}

	@Then("I should see the application window")
	public void assertApplicationWindow() {
		JFrame window = app.getMainWindow();
		assertThat(window, notNullValue());
		assertThat(window.isVisible(), is(true));
	}

}
