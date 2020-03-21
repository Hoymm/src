package restaurants.restaurant;

import java.util.List;

class OrderMaker <T extends Enum<T>> {
  private static int idCounter = 0;

  Order makeOrder(List<T> dishes) {
    return new Order<>(dishes, ++idCounter);
  }
}
