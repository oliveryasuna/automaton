package com.oliveryasuna.automaton;

import org.jgrapht.Graph;
import org.jgrapht.graph.AsUnmodifiableGraph;
import org.jgrapht.graph.DirectedPseudograph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Automaton {

  private final Graph<State, Transition> graph = new DirectedPseudograph<>(Transition.class);

  private final Object epsilonSymbol;

  private State startState;

  private Set<State> acceptingStates = new HashSet<>();

  public Automaton(final Object epsilonSymbol) {
    super();

    this.epsilonSymbol = epsilonSymbol;
  }

  public State addState(final boolean accepting) {
    final State state = new State(graph.vertexSet().isEmpty(), accepting);

    if(!graph.addVertex(state)) {
      return null;
    }

    if(state.isStart()) this.startState = state;
    if(state.isAccepting()) acceptingStates.add(state);

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

  public Graph<State, Transition> getGraph() {
    return new AsUnmodifiableGraph<>(graph);
  }

  public Object getEpsilonSymbol() {
    return epsilonSymbol;
  }

  public State getStartState() {
    return startState;
  }

  public Set<State> getAcceptingStates() {
    return Collections.unmodifiableSet(acceptingStates);
  }

}
