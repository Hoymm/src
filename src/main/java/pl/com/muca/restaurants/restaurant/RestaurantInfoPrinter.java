package pl.com.muca.restaurants.restaurant;

import java.util.function.Supplier;
import pl.com.muca.common.Colors;

public class RestaurantInfoPrinter {

  private final static String BUFFER_FULL_INFO = String.format(
      "%sBufor jest pełny. Złożenie następnego zamówienia będzie "
          + "zrealizowane po zwolnieniu bufora.%s%n",
      Colors.RED, Colors.RESET);

  private final static String NEW_ORDER_IN_QUEUE_INFO = String.format(
      "%sW buforze kolejki zostalo umieszczone nowe zamówienie:%s%n",
      Colors.CONSUMER_BUFFER_INFO, Colors.RESET);

  private final int totalBufferCapacity;
  private final Supplier<Integer> getHowManyOrdersInBuffer;

  public RestaurantInfoPrinter(Supplier<Integer> getHowManyOrdersInBuffer,
      int totalBufferCapacity) {
    this.totalBufferCapacity = totalBufferCapacity;
    this.getHowManyOrdersInBuffer = getHowManyOrdersInBuffer;
  }


  public void printInfoBeforePuttingIntoBuffer() {
    if (isBufferFull()) {
      System.out.println(getThreadInfo() + BUFFER_FULL_INFO);
    }
  }

  private boolean isBufferFull() {
    return totalBufferCapacity == getHowManyOrdersInBuffer.get();
  }

  public void printInfoAfterPuttingIntoBuffer(Order newOrder) {
    String newOrderInfo = String
        .format("%s%s%n", NEW_ORDER_IN_QUEUE_INFO, newOrder);
    System.out.println(getThreadInfo() + newOrderInfo + getBufferInfo());
  }

  private String getBufferInfo() {
    return String.format("%sRozmiar bufora %d/%d%s %n",
        Colors.CONSUMER_BUFFER_INFO, getHowManyOrdersInBuffer.get(),
        totalBufferCapacity,
        Colors.RESET);
  }

  private String getThreadInfo() {
    return String
        .format("%sProducent%s, wątek nr. %s%n",
            Colors.PRODUCER_THREAD_INFO, Colors.RESET,
            Thread.currentThread().getId());
  }
}
