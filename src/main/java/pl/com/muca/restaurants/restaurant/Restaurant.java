package pl.com.muca.restaurants.restaurant;

import pl.com.muca.common.Colors;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

/**
 * Solution for the producer consumer problem using {@link ArrayBlockingQueue}
 * class. The class is a special type of a queue with helpful methods:
 * <ul>
 *   <li>{@link ArrayBlockingQueue#take}</li>
 *   <li>{@link ArrayBlockingQueue#put}</li>
 * </ul>
 * These methods easily solve two main issues of the consumer producer problem:
 * <ul>
 *   <li>producer writing to a full buffer</li>
 *   <li>consumer reading from an empty buffer</li>
 * </ul>
 * @author Damian Muca
 */
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
