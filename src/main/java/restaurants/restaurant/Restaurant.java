package restaurants.restaurant;

import common.Colors;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Restaurant implements RestaurantClientApi, WorkplaceReleaseNotifier {
  private final Queue<Order> waitingOrders;
  private final Queue<Workplace> availableWorkplaces;

  protected Restaurant(int howManyProcessingStations) {
    this.waitingOrders = new ArrayBlockingQueue<>(10);
    availableWorkplaces =
        Stream.generate(() -> new Workplace(this, this.waitingOrders::remove))
            .limit(howManyProcessingStations)
            .collect(Collectors.toCollection(ArrayDeque::new));
  }

  @Override
  public void makeOrder(List<String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);
//    Optional<Workplace> availableWorkplace = Optional.ofNullable(availableWorkplaces.poll());

    String blackBold = "\033[1;30m";
    System.out.printf("%s%s%s\n\n", blackBold, newOrder, Colors.RESET);

//    if (availableWorkplace.isPresent()) {
//      availableWorkplace.get().processOrder(newOrder);
//    } else {
      waitingOrders.add(newOrder);
//    }

  }

  // TODO REMOVE ?
  @Override
  public void notify(Workplace workplace) {
//    Optional<Order> order = Optional.ofNullable(waitingOrders.poll());
//    if (order.isPresent()) {
//      workplace.processOrder(order.get());
//    } else {
//      availableWorkplaces.add(workplace);
//    }
  }
}
