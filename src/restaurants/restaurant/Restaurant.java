package restaurants.restaurant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Restaurant<Dishes extends Enum<Dishes>> implements RestaurantClientApi<Dishes>, RestaurantManagersApi {
  private final OrderMaker<Dishes> orderMaker;
  private final List<OrderProcessingStation> orderProcessingStations;
  final Queue<Order> ordersToBePrepared; // TODO this should be private
  private final Map<OrderInfo, Order> preparedOrders;

  public Restaurant(int howManyProcessingStations) {
    this.orderMaker = new OrderMaker<>();
    this.ordersToBePrepared = new ArrayDeque<>();
    this.preparedOrders = new HashMap<>();

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

  // TODO this method violates SRP.
  @Override
  public OrderInfo makeOrder(List<Dishes> dishes) {
    Order newOrder = this.orderMaker.makeOrder(dishes);
    ordersToBePrepared.add(newOrder);
    System.out.printf("New order placed (ID: %2d): %20s\n", newOrder.getId(), newOrder.getDishes());
    return newOrder;
  }

  @Override
  public void retrieveOrder(OrderInfo orderInfo) {
    System.out.printf("%s został odebrany przez klienta.", preparedOrders.remove(orderInfo));
  }
}
