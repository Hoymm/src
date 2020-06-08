package pl.com.muca.restaurants.restaurant.order;

import static pl.com.muca.common.Colors.BGREEN;
import static pl.com.muca.common.Colors.YELLOW;
import static pl.com.muca.common.Colors.RESET;

public enum OrderState {
  PLACED("złożone"),
  IS_BEING_PREPARED(YELLOW + "przygotowywane" + RESET),
  READY_TO_PICKUP(BGREEN + "gotowe do odbioru" + RESET);

  private final String stringName;

  OrderState(String stringName) {
    this.stringName = stringName;
  }

  @Override
  public String toString() {
    return stringName;
  }
}
