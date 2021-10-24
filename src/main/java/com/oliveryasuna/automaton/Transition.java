package com.oliveryasuna.automaton;

public class Transition {

  private Object symbol;

  Transition(final Object symbol) {
    super();

    this.symbol = symbol;
  }

  public Object getSymbol() {
    return symbol;
  }

  public void setSymbol(final Object symbol) {
    this.symbol = symbol;
  }

}
