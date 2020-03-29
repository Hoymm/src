package pl.com.muca.restaurants.restaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;
import pl.com.muca.common.Colors;


/**
 * Solution for the producer consumer problem using locks.
 *
 * @author Damian Muca
 */
public abstract class Restaurant implements RestaurantClientApi {

  private final static String BUFFER_FULL_INFO = String.format(
      "%sBufor jest pełny. Złożenie następnego zamówienia będzie "
          + "zrealizowane po zwolnieniu bufora.%s%n",
      Colors.RED, Colors.RESET);

  private final static String NEW_ORDER_IN_QUEUE_INFO = String.format(
      "%sW buforze kolejki zostalo umieszczone nowe zamówienie:%s%n)",
      Colors.BUFFER_INFO, Colors.RESET);
  private final static int BUFFER_SIZE = 10;

  private final Queue<Order> ordersQueue;
  private final ReentrantLock queueLock;
  private Condition isQueueFull;
  private Condition isQueueEmpty;

  protected Restaurant(int howManyProcessingStations) {
    ordersQueue = new LinkedList<>();
    Stream.generate(() -> new Workplace(this::remove, this::getBufferSizeInfo, ordersQueue::isEmpty))
        .limit(howManyProcessingStations)
        .forEach(Thread::start);
    queueLock = new ReentrantLock(true);
    isQueueFull = queueLock.newCondition();
    isQueueEmpty = queueLock.newCondition();
  }

  // Entire method is a critical section.
  // This is why we put lock at the beginning and release it after.
  private void put(Order order) {
    queueLock.lock();
    try {
      // Simulate fixed size buffer.
      while (ordersQueue.size() == BUFFER_SIZE) {
        try {
          // When the buffer is full, put this thread in a wait state.
          // Thread will be awaken after order is removed from the buffer.
          isQueueFull.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      ordersQueue.add(order);
      // Inform other threads that item was added to the buffer.
      isQueueEmpty.signalAll();
    } finally {
      queueLock.unlock();
    }
  }

  // Entire method is a critical section.
  // This is why we put lock at the beginning and release it after.
  private Order remove() {
    queueLock.lock();
    try {
      // When there is no order in the buffer, put this thread in a wait state.
      // Thread will be awaken if anything is an added to the buffer.
      while (ordersQueue.size() == 0) {
        try {
          isQueueEmpty.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      Order order = ordersQueue.remove();
      // Inform other threads that item was added to the buffer.
      isQueueFull.signalAll();
      return order;
    } finally {
      queueLock.unlock();
    }
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

    this.put(newOrder);

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
