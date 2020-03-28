package restaurants.restaurant;

import common.Colors;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class Restaurant implements RestaurantClientApi {
  private final Consumer<Order> ordersConsumer;

  protected Restaurant(int howManyProcessingStations) {
    Queue<Order> ordersQueue = new ArrayBlockingQueue<>(10);
    this.ordersConsumer = ordersQueue::add;

    Stream.generate(() -> new Workplace(ordersQueue::remove))
        .limit(howManyProcessingStations)
        .forEach(Thread::start);
  }

  @Override
  public void makeOrder(List<String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);
    String blackBold = "\033[1;30m";
    System.out.printf("%s%s%s\n\n", blackBold, newOrder, Colors.RESET);
    ordersConsumer.accept(newOrder);
  }

}
