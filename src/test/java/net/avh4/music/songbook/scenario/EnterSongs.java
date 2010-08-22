package net.avh4.music.songbook.scenario;

import org.jbehave.scenario.Scenario;
import org.jbehave.scenario.steps.CandidateSteps;

public class EnterSongs extends Scenario {

	public EnterSongs() {
		addSteps(new CandidateSteps[] { new EnterSongsSteps() });
	}

}
