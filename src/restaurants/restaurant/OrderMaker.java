package restaurants.restaurant;

import java.util.List;

class OrderMaker <Dishes extends Enum<Dishes>> {
  private static int idCounter = 0;

  Order makeOrder(List<Dishes> dishes) {
    return new Order<>(dishes, ++idCounter);
  }
}
