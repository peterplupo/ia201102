package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum State {
	
	CLEAN, WET, DIRTY;
	
	public static State getRandomState() {
		List<State> states = Arrays.asList(values());
		Collections.shuffle(states);
		return states.get(0);
	}

}
