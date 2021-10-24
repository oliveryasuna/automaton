package com.oliveryasuna.automaton;

import com.oliveryasuna.commons.language.exception.UtilityClassException;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiPredicate;

public final class AutomatonRuns {

  public static boolean linearRun(final Object[] input, final Automaton automaton, final BiPredicate<Object, Object> equalityChecker) {
    if(input == null) throw new IllegalArgumentException("Argument [automaton] is null.");
    if(automaton == null) throw new IllegalArgumentException("Argument [automaton] is null.");
    if(!automaton.hasStartState()) throw new IllegalArgumentException("Argument [automaton] has no start state.");

    return linearRun(input, automaton, automaton.getStartState(), equalityChecker != null ? equalityChecker : Object::equals);
  }

  private static <T> boolean linearRun(final Object[] input, final Automaton automaton, final State state, final BiPredicate<Object, Object> equalityChecker) {
    if(input.length == 0) {
      return state.isAccepting();
    }

    final Set<Transition> transitions = automaton.getGraph().outgoingEdgesOf(state);

    for(final Transition transition : transitions) {
      if(equalityChecker.test(transition.getSymbol(), input[0])) {
        final State nextState = automaton.getGraph().getEdgeTarget(transition);

        if(input.length == 1) {
          return nextState.isAccepting();
        }

        final boolean nextStateRun = linearRun(Arrays.copyOfRange(input, 1, input.length), automaton, nextState, equalityChecker);

        if(nextStateRun) {
          return true;
        }
      }
    }

    return false;
  }

  private AutomatonRuns() {
    throw new UtilityClassException();
  }

}
