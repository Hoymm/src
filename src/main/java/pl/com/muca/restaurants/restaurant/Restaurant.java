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
 *
 * @author Damian Muca
 */
public abstract class Restaurant implements RestaurantClientApi {

  private static final int BUFFER_SIZE = 10;
  private final static String BUFFER_FULL_INFO = String.format(
      "%sBufor jest pełny. Złożenie następnego zamówienia będzie "
          + "zrealizowane po zwolnieniu bufora.%s%n",
      Colors.RED, Colors.RESET);

  private final static String NEW_ORDER_IN_QUEUE_INFO = String.format(
      "%sW buforze kolejki zostalo umieszczone nowe zamówienie:%s%n)",
      Colors.BUFFER_INFO, Colors.RESET);

  private final BlockingQueue<Order> ordersQueue;


  protected Restaurant(int howManyProcessingStations) {
    this.ordersQueue = new ArrayBlockingQueue<>(BUFFER_SIZE);

    Stream.generate(() -> new Workplace(this.ordersQueue))
        .limit(howManyProcessingStations)
        .forEach(Thread::start);

  }

  @Override
  public void makeOrder(List<String> dishes) {
    Order newOrder = OrderMaker.createNewOrder(dishes);

    String threadInfo = String
        .format("%sID wątku (PRODUCENTA) wypisującego informacje: %s%s%n",
            Colors.THREAD_PRODUCER_INFO,
            Thread.currentThread().getId(), Colors.RESET);

    if (BUFFER_SIZE == ordersQueue.size()) {
      System.out.println(threadInfo + BUFFER_FULL_INFO);
    }

    try {
      ordersQueue.put(newOrder);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    String newOrderInfo = String.format("%s%n", newOrder);
    String bufferSizeInfo = getBufferSizeInfo();
    System.out.println(threadInfo + NEW_ORDER_IN_QUEUE_INFO + newOrderInfo +
        bufferSizeInfo);
  }

  private String getBufferSizeInfo() {
    return String.format("%sRozmiar bufora %d/%d%s %n",
        Colors.BUFFER_INFO, ordersQueue.size(), BUFFER_SIZE,
        Colors.RESET);
  }

}
