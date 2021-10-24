package com.oliveryasuna.automaton;

public class Transition {

  private final Object symbol;

  Transition(final Object symbol) {
    super();

    this.symbol = symbol;
  }

  public Object getSymbol() {
    return symbol;
  }

}
