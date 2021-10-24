package com.oliveryasuna.automaton;

public class State {

  private final boolean start;

  private boolean accepting;

  State(final boolean start, final boolean accepting) {
    super();

    this.start = start;
    this.accepting = accepting;
  }

  public boolean isStart() {
    return start;
  }

  public boolean isAccepting() {
    return accepting;
  }

  public void setAccepting(final boolean accepting) {
    this.accepting = accepting;
  }

}
