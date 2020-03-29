package restaurants.restaurant;

import common.Colors;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public abstract class Restaurant implements RestaurantClientApi {

  private final static int MAX_SIZE = 10;

  private final Queue<Order> ordersQueue;
  private final ReentrantLock queueLock;
//  private Condition isQueueFull;

  protected Restaurant(int howManyProcessingStations) {
    ordersQueue = new LinkedList<>();
    Stream.generate(() -> new Workplace(this::remove))
        .limit(howManyProcessingStations)
        .forEach(Thread::start);
    queueLock = new ReentrantLock(true);
  }

  private void put(Order order) {
    queueLock.lock();
    try {
      if (ordersQueue.size() == MAX_SIZE) {
        throw new RuntimeException("CANNOT ADD TO FULL QUEUE...");
      }
      ordersQueue.add(order);
    } finally {
      queueLock.unlock();
    }
  }

  private Order remove() {
    try {
      queueLock.lock();
      return ordersQueue.remove();
    } finally {
      queueLock.unlock();
    }
  }

  @Override
  public void makeOrder(List<String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);
    this.put(newOrder);
    String blackBold = "\033[1;30m";
    System.out.printf("%s%s%s\n\n", blackBold, newOrder, Colors.RESET);
  }
}
