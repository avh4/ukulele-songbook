package net.avh4.music.songbook.scenario;

import org.jbehave.scenario.Scenario;
import org.jbehave.scenario.steps.CandidateSteps;

public class ReadableSongs extends Scenario {

	public ReadableSongs() {
		addSteps(new CandidateSteps[] { new SongbookSteps() });
	}

}
