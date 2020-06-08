package pl.com.muca.restaurants.restaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pl.com.muca.restaurants.restaurant.order.Order;
import pl.com.muca.restaurants.restaurant.order.OrderMaker;
import pl.com.muca.restaurants.restaurant.processingstation.ProcessingStation;

/**
 * Solution for the producer consumer problem using locks.
 *
 * @author Damian Muca
 */
public abstract class Restaurant implements RestaurantClientApi {

  private static final int MAX_BUFFER_SIZE = 10;

  private final RestaurantInfoPrinter restaurantInfoPrinter;
  private final Queue<Order> ordersQueue;
  private final ReentrantLock queueLock;
  private final Condition isQueueFull;
  private final Condition isQueueEmpty;
  private final ExecutorService processingStationsExecutor;
  private final List<ProcessingStation> processingStations;

  protected Restaurant(int howManyProcessingStations) {
    ordersQueue = new LinkedList<>();
    this.queueLock = new ReentrantLock(true);
    this.isQueueFull = queueLock.newCondition();
    this.isQueueEmpty = queueLock.newCondition();
    this.processingStationsExecutor = Executors.newFixedThreadPool(howManyProcessingStations);

    BufferInfo bufferInfo = new BufferInfo(ordersQueue::size, MAX_BUFFER_SIZE);
    this.restaurantInfoPrinter = new RestaurantInfoPrinter(bufferInfo);

    this.processingStations = Stream
        .generate(() -> new ProcessingStation(this::remove, bufferInfo))
        .limit(howManyProcessingStations).collect(Collectors.toList());
    processingStations.forEach(processingStationsExecutor::submit);
  }

  public void closeRestaurant(){
    this.processingStations.forEach(ProcessingStation::close);
  }

  public ExecutorService getProcessingStationsExecutor() {
    return processingStationsExecutor;
  }

  @Override
  public void makeOrder(List<String> dishes) {
    put(OrderMaker.createNewOrder(dishes));
  }

  // Entire method is a critical section.
  // This is why we put lock at the beginning and release it after.
  private void put(Order order) {
    restaurantInfoPrinter.printInfoBeforePuttingIntoBuffer();
    queueLock.lock();
    try {
      // Simulate fixed size buffer.
      while (ordersQueue.size() == MAX_BUFFER_SIZE) {
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
    restaurantInfoPrinter.printInfoAfterPuttingIntoBuffer(order);
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
}
