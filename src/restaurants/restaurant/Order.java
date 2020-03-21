package restaurants.restaurant;

import java.util.List;

public class Order <T extends Enum<T>> implements OrderInfo<T> {
  private int id;
  private List<T> dishes;
  private OrderState orderState;

  Order(List<T> dishes, int id) {
    this.dishes = dishes;
    this.id = id;
    this.orderState = OrderState.PLACED;
  }

  void setOrderState(OrderState orderState) {
    this.orderState = orderState;
  }

  @Override
  public String toString() {
    return String.format("\trestaurants.restaurant.Order number %2d (%s) is %10s", id, dishes, orderState);
  }

  @Override
  public List<T> getDishes() {
    return this.dishes;
  }

  @Override
  public int getId() {
    return id;
  }
}
