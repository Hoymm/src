package pl.com.muca.restaurants.restaurant;

import java.util.List;

class Order {
  private final int id;
  private final List dishes;
  private OrderState orderState;

  Order(List dishes, int id) {
    this.dishes = dishes;
    this.id = id;
    this.orderState = OrderState.PLACED;
  }

  void setOrderState(OrderState orderState) {
    this.orderState = orderState;
  }

  @Override
  public String toString() {
    return String.format("(ID:%2d) %s\nis %s", id, dishes, orderState);
  }
}
