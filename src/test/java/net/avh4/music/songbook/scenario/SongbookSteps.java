package net.avh4.music.songbook.scenario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import net.avh4.music.songbook.Services;
import net.avh4.music.songbook.SongbookApplication;

import org.jbehave.scenario.annotations.BeforeScenario;
import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.steps.Steps;

public abstract class SongbookSteps extends Steps {

	protected SongbookApplication app = null;
	protected final Services mockServices = mock(Services.class);
	protected Writer tempFileStream;

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

	protected void verifyRenderedPage(final String html) throws IOException {
		assertThat(tempFileStream.toString(), is(html));
		verify(mockServices).printFile(tempFileStream);
	}

}