package edu.ntnu.idat2003.services;

import java.util.HashMap;

public class StateService {
  private HashMap<String, boolean> states = new HashMap<String, boolean>();

  public void setState(String key, boolean value) {
    states.put(key, value);
  }

  public boolean getState(String key) {
    return states.get(key);
  }

  public void removeState(String key) {
    states.remove(key);
  }

  public void setStateMethod(String key) {
    // I want to pass a method as a parameter
    // This method should be called when the state is set
    // And the state can be handled in the method
  }
}
