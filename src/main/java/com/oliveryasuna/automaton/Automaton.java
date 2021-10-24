package com.oliveryasuna.automaton;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;

public class Automaton {

  private final Graph<State, Transition> graph = new DirectedPseudograph<>(Transition.class);

  private final Object epsilonSymbol;

  private State startState;

  public Automaton(final Object epsilonSymbol) {
    super();

    this.epsilonSymbol = epsilonSymbol;
  }

  public State addState(final boolean accepting) {
    final State state = new State(graph.vertexSet().isEmpty(), accepting);

    if(state.isStart()) {
      this.startState = state;
    }

    graph.addVertex(state);

    return state;
  }

  public State addState() {
    return addState(false);
  }

  public Transition addTransition(final State from, final State to, final Object symbol) {
    final Transition transition = new Transition(symbol);

    graph.addEdge(from, to, transition);

    return transition;
  }

  public Transition addTransition(final State from, final State to) {
    return addTransition(from, to, epsilonSymbol);
  }

  public boolean isDeterministic() {
    for(final State state : graph.vertexSet()) {
      if(graph.outgoingEdgesOf(state).size() > 1) {
        return false;
      }
    }

    return true;
  }

  public boolean hasEpsilonTransitions() {
    if(isDeterministic()) {
      return false;
    }

    for(final Transition transition : graph.edgeSet()) {
      if(transition.getSymbol().equals(epsilonSymbol)) {
        return true;
      }
    }

    return false;
  }

  boolean hasStartState() {
    return (startState != null);
  }

  Graph<State, Transition> getGraph() {
    return graph;
  }

  Object getEpsilonSymbol() {
    return epsilonSymbol;
  }

  State getStartState() {
    return startState;
  }

}
