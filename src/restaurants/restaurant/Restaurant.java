package restaurants.restaurant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Restaurant implements RestaurantClientApi, RestaurantManagersApi {
  private final OrderMaker orderMaker;
  private final List<OrderProcessingStation> orderProcessingStations;
  final Queue<Order> ordersToBePrepared; // TODO this should be private

  protected Restaurant(int howManyProcessingStations) {
    this.orderMaker = new OrderMaker();
    this.ordersToBePrepared = new ArrayDeque<>();
    orderProcessingStations =
            Stream.generate(() -> new OrderProcessingStation(this))
                  .limit(howManyProcessingStations)
                  .collect(Collectors.toList());
  }

  @Override
  public void open() {
    this.orderProcessingStations.forEach(OrderProcessingStation::open);
  }

  @Override
  public void close() {
    this.orderProcessingStations.forEach(OrderProcessingStation::close);
  }

  @Override
  public void makeOrder(List dishes) {
    Order newOrder = this.orderMaker.makeOrder(dishes);
    ordersToBePrepared.add(newOrder);
    System.out.printf("New order placed %s\n", newOrder);
  }
}
