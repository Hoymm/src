package restaurants.restaurant;

import java.util.List;

public class Order implements OrderInfo {
  private int id;
  private List dishes;
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
    return String.format(
        "\trestaurants.restaurant.Order number %2d (%s) is %10s", id, dishes, orderState);
  }

  @Override
  public List getDishes() {
    return this.dishes;
  }

  @Override
  public int getId() {
    return id;
  }
}
