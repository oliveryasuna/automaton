package com.oliveryasuna.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutomatonRunsTests {

  @Test
  public void oddNumberOfBs() {
    final Automaton automaton = new Automaton(null);

    final State startState = automaton.addState();
    final State state0 = automaton.addState();

    state0.setAccepting(true);

    automaton.addTransition(startState, startState, 'a');
    automaton.addTransition(startState, state0, 'b');
    automaton.addTransition(state0, state0, 'a');
    automaton.addTransition(state0, state0, 'a');
    automaton.addTransition(state0, startState, 'b');

    Assertions.assertTrue(AutomatonRuns.linearRun(new Object[] {'b'}, automaton, null));
    Assertions.assertFalse(AutomatonRuns.linearRun(new Object[] {'b', 'b'}, automaton, null));
    Assertions.assertTrue(AutomatonRuns.linearRun(new Object[] {'b', 'b', 'b'}, automaton, null));
    Assertions.assertTrue(AutomatonRuns.linearRun(new Object[] {'a', 'b', 'a'}, automaton, null));
    Assertions.assertFalse(AutomatonRuns.linearRun(new Object[] {'a', 'b', 'a', 'b', 'a'}, automaton, null));
    Assertions.assertTrue(AutomatonRuns.linearRun(new Object[] {'a', 'b', 'a', 'b', 'a', 'b', 'a'}, automaton, null));
  }

}
