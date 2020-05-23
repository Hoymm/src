package pl.com.muca.restaurants.restaurant.order;

import static pl.com.muca.common.Colors.BGREEN;
import static pl.com.muca.common.Colors.YELLOW;
import static pl.com.muca.common.Colors.RESET;

public enum OrderState {
  PLACED("placed"),
  IS_BEING_PREPARED(YELLOW + "being prepared" + RESET),
  READY_TO_PICKUP(BGREEN + "ready to pickup" + RESET);

  private final String stringName;

  OrderState(String stringName) {
    this.stringName = stringName;
  }

  @Override
  public String toString() {
    return stringName;
  }
}
