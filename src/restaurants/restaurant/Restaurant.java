package restaurants.restaurant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Restaurant implements RestaurantClientApi, RestaurantManagersApi {
  private final List<Workplace> workplaces;
  final Queue<Order> ordersToBePrepared; // TODO this should be private

  protected Restaurant(int howManyProcessingStations) {
    this.ordersToBePrepared = new ArrayDeque<>();
    workplaces =
            Stream.generate(() -> new Workplace(this))
                  .limit(howManyProcessingStations)
                  .collect(Collectors.toList());
  }

  @Override
  public void open() {
    this.workplaces.forEach(Workplace::open);
  }

  @Override
  public void close() {
    this.workplaces.forEach(Workplace::close);
  }

  @Override
  public void makeOrder(List <String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);
    ordersToBePrepared.add(newOrder);
    System.out.printf("New order placed %s\n", newOrder);
  }
}
