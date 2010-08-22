package net.avh4.music.songbook.scenario;

import org.jbehave.scenario.Scenario;
import org.jbehave.scenario.steps.CandidateSteps;

public class PrintSongs extends Scenario {

	public PrintSongs() {
		addSteps(new CandidateSteps[] { new PrintSongsSteps() });
	}

}
