public class Order implements OrderInfo {
  private int id;
  private Dish dish;
  private OrderState orderState;

  public Order(Dish dish, int id) {
    this.dish = dish;
    this.id = id;
    this.orderState = OrderState.PLACED;
  }

  public void setOrderState(OrderState orderState) {
    this.orderState = orderState;
  }

  @Override
  public String toString() {
    return String.format("\tOrder number %2d (%s) is %10s", id, dish, orderState);
  }

  @Override
  public Dish getDish() {
    return dish;
  }

  @Override
  public int getId() {
    return id;
  }
}
