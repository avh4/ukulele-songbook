package net.avh4.music.songbook.scenario;

import org.jbehave.scenario.Scenario;
import org.jbehave.scenario.steps.CandidateSteps;

public class IterationZero extends Scenario {

	public IterationZero() {
		addSteps(new CandidateSteps[] { new IterationZeroSteps() });
	}

}
