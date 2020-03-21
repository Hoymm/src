import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Restaurant {
  private OrderMaker orderMaker;
  private List<OrderProcessingStation> orderProcessingStations;
  Queue<Order> orders; // TODO this should be private
  private boolean isOpen;

  Restaurant() {
    this.orderMaker = new OrderMaker();
    this.orders = new ArrayDeque<>();
    prepareWorkStations();
  }

  private void prepareWorkStations() {
    orderProcessingStations =
        Stream.generate(() -> new OrderProcessingStation(this))
              .limit(3)
              .collect(Collectors.toList());
  }

  void openRestaurant() {
    this.isOpen = true;
    this.orderProcessingStations.forEach(Thread::start);
  }

  // TODO this method violates SRP.
  OrderInfo makeOrder(Dish dish) {
    Order newOrder = this.orderMaker.makeOrder(dish);
    orders.add(newOrder);
    System.out.printf("New order placed (ID: %2d): %20s\n", newOrder.getId(), newOrder.getDish());
    return newOrder;
  }

  boolean isOpen() {
    return this.isOpen;
  }
}
