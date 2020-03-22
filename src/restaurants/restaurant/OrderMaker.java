package restaurants.restaurant;

import java.util.List;

class OrderMaker  {
  private static int idCounter = 0;

  Order makeOrder(List dishes) {
    return new Order(dishes, ++idCounter);
  }
}
