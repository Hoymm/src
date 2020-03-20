class OrderMaker {
  private static int idCounter = 0;

  Order makeOrder(Dish dish) {
    return new Order(dish, ++idCounter);
  }
}
