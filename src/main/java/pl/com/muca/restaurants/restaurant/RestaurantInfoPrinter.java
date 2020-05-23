package pl.com.muca.restaurants.restaurant;

import pl.com.muca.common.Colors;
import pl.com.muca.restaurants.restaurant.order.Order;

public class RestaurantInfoPrinter {

  private final static String BUFFER_FULL_INFO = String.format(
      "%sBufor jest pełny. Złożenie następnego zamówienia będzie "
          + "zrealizowane po zwolnieniu bufora.%s%n",
      Colors.RED, Colors.RESET);

  private final static String NEW_ORDER_IN_QUEUE_INFO = String.format(
      "%sW buforze kolejki zostalo umieszczone nowe zamówienie:%s%n",
      Colors.PRODUCER_BUFFER_INFO, Colors.RESET);
  private final BufferInfo bufferInfo;

  public RestaurantInfoPrinter(BufferInfo bufferInfo) {
    this.bufferInfo = bufferInfo;
  }


  public void printInfoBeforePuttingIntoBuffer() {
    if (bufferInfo.isFull()) {
      System.out.println(getThreadInfo() + BUFFER_FULL_INFO);
    }
  }

  public void printInfoAfterPuttingIntoBuffer(Order newOrder) {
    String newOrderInfo = String
        .format("%s%s%n", NEW_ORDER_IN_QUEUE_INFO, newOrder);
    System.out.println(getThreadInfo() + newOrderInfo + getBufferInfo());
  }

  private String getBufferInfo() {
    return String.format("%sRozmiar bufora %d/%d%s %n",
        Colors.PRODUCER_BUFFER_INFO, bufferInfo.size(),
        bufferInfo.getTotalCapacity(),
        Colors.RESET);
  }

  private String getThreadInfo() {
    return String
        .format("%sProducent%s, wątek nr. %s%n",
            Colors.PRODUCER_THREAD_INFO, Colors.RESET,
            Thread.currentThread().getId());
  }
}
