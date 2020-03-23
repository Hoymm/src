package restaurants.restaurant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Restaurant
    implements RestaurantClientApi, RestaurantManagersApi, WorkplaceReleaseNotifier {
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
    System.out.println("RESTAURANT OPENED.");
  }

  @Override
  public void close() {
    System.out.println("RESTAURANT CLOSED.");
  }

  @Override
  public void makeOrder(List<String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);
    Optional<Workplace> availableWorkplace = Optional.ofNullable(availableWorkplaces.poll());

    if (availableWorkplace.isPresent()){
      availableWorkplace.get().processOrder(newOrder);
    }
    else {
      waitingOrders.add(newOrder);
    }

    System.out.printf("New order placed %s\n", newOrder);
  }

  @Override
  public void notify(Workplace workplace) {
    Optional<Order> order = Optional.ofNullable(waitingOrders.poll());
    if (order.isPresent()) {
      workplace.processOrder(order.get());
    } else {
      availableWorkplaces.add(workplace);
    }
  }
}
