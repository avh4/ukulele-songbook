package net.avh4.music.songbook.scenario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JFrame;

import net.avh4.music.songbook.Services;
import net.avh4.music.songbook.SongbookApplication;

import org.apache.commons.io.IOUtils;
import org.fest.swing.fixture.FrameFixture;
import org.jbehave.scenario.annotations.AfterScenario;
import org.jbehave.scenario.annotations.BeforeScenario;
import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

public class SongbookSteps extends Steps {

	protected SongbookApplication app = null;
	protected final Services mockServices = mock(Services.class);
	protected Writer tempFileStream;
	protected FrameFixture window;

	@BeforeScenario
	public void mockSetup() throws IOException {
		tempFileStream = new StringWriter();
		when(mockServices.getTempHtmlFile()).thenReturn(tempFileStream);
	}

	@AfterScenario
	public void cleanUp() {
		if (window != null) {
			window.cleanUp();
		}
	}

	@Given("that the application is running")
	public void startApp() {
		app = new SongbookApplication(mockServices);
		app.start();
		assertThat("Could not launch the application", app, notNullValue());
		window = new FrameFixture((JFrame) app.getMainWindow());
		assertThat(window, notNullValue());
	}

	@Given("the song \"$song\" has been entered and selected")
	public void loadSong(String song) throws IOException {
		startApp();
		String songData = getResourceAsString(song + ".song");
		app.setSong(songData);

		// Post-conditions: the select song is not null
		assertThat(app.getSelectedSong(), notNullValue());
	}

	private String getResourceAsString(String resource) throws IOException {
		InputStream stream = getClass().getResourceAsStream(resource);
		if (stream == null)
			fail("Could not find resource " + resource);
		String songData = IOUtils.toString(stream);
		return songData;
	}

	@When("I ask to print the song")
	public void actionPrint() {
		window.button("print").click();
	}

	@Then("the printed version looks like \"$file\"")
	public void verifyRenderedPage(final String file) throws IOException {
		String html = getResourceAsString(file);
		verify(mockServices).printFile(tempFileStream);
		assertThat(tempFileStream.toString(), equalTo(html));
	}

}