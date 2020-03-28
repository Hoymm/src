package restaurants.restaurant;

import common.Colors;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

public abstract class Restaurant implements RestaurantClientApi {
  private final BlockingQueue<Order> ordersQueue;

  protected Restaurant(int howManyProcessingStations) {
    this.ordersQueue = new ArrayBlockingQueue<>(10);

    Stream.generate(() -> new Workplace(this.ordersQueue))
        .limit(howManyProcessingStations)
        .forEach(Thread::start);
  }

  @Override
  public void makeOrder(List<String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);
    String blackBold = "\033[1;30m";
    System.out.printf("%s%s%s\n\n", blackBold, newOrder, Colors.RESET);
    try {
      ordersQueue.put(newOrder);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
