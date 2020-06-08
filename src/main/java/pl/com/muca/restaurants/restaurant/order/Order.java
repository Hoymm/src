package pl.com.muca.restaurants.restaurant.order;

import java.util.List;
import pl.com.muca.common.Colors;

public class Order {

  private final int id;
  private final List dishes;
  private OrderState orderState;

  Order(List dishes, int id) {
    this.dishes = dishes;
    this.id = id;
    this.orderState = OrderState.PLACED;
  }

  public void setOrderState(OrderState orderState) {
    this.orderState = orderState;
  }

  @Override
  public String toString() {
    return String
        .format("%s(ID:%2d) %s\njest %s%s", Colors.BBLACK, id, dishes, orderState,
            Colors.RESET);
  }

  public boolean isBeingPrepared() {
    return OrderState.IS_BEING_PREPARED == this.orderState;
  }
}
