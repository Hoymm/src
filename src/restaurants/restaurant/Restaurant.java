package restaurants.restaurant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Restaurant implements RestaurantClientApi, RestaurantManagersApi {
  final Queue<Order> waitingOrders; // TODO this should be private
  private final Queue<Workplace> availableWorkplaces;

  protected Restaurant(int howManyProcessingStations) {
    this.waitingOrders = new ArrayDeque<>();
    availableWorkplaces =
        Stream.generate(() -> new Workplace(this))
            .limit(howManyProcessingStations)
            .collect(Collectors.toCollection(ArrayDeque::new));
  }

  @Override
  public void open() {
    this.availableWorkplaces.forEach(Workplace::open);
  }

  @Override
  public void close() {
    this.availableWorkplaces.forEach(Workplace::close);
  }

  @Override
  public void makeOrder(List<String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);
    waitingOrders.add(newOrder);
    System.out.printf("New order placed %s\n", newOrder);
  }
}
