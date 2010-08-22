package net.avh4.music.songbook.scenario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JFrame;

import net.avh4.music.songbook.Services;
import net.avh4.music.songbook.SongbookApplication;

import org.jbehave.scenario.annotations.BeforeScenario;
import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

public class IterationZeroSteps extends Steps {

	private SongbookApplication app = null;
	private final Services mockServices = mock(Services.class);
	private Writer tempFileStream;

	@BeforeScenario
	public void mockSetup() throws IOException {
		tempFileStream = new StringWriter();
		when(mockServices.getTempHtmlFile()).thenReturn(tempFileStream);
	}

	@Given("that the application is running")
	public void startApp() {
		app = new SongbookApplication(mockServices);
		app.start();
		assertThat("Could not launch the application", app, notNullValue());
	}

	@When("I launch the application")
	public void launchApp() {
		startApp();
	}

	@When("I request an action that launches a web browser")
	public void browserAction() {
		app.showRenderedPage();
	}

	@Then("I should see the application window")
	public void assertApplicationWindow() {
		JFrame window = app.getMainWindow();
		assertThat(window, notNullValue());
		assertThat(window.isVisible(), is(true));
	}

	@Then("I should see the appropriate rendered page")
	public void verifyRenderedPage() throws IOException {
		assertThat(tempFileStream.toString(),
				is("<html>Ukulele Songbook</html>"));
		verify(mockServices).openTempHtmlFile();
	}

}
