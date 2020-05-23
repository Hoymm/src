package pl.com.muca.restaurants.restaurant;

import java.util.function.Supplier;
import pl.com.muca.common.Colors;

public class RestaurantInfoPrinter {

  private final int totalBufferCapacity;
  private final Supplier<Integer> getHowManyOrdersInBuffer;

  public RestaurantInfoPrinter(Supplier<Integer> getHowManyOrdersInBuffer,
      int totalBufferCapacity) {
    this.totalBufferCapacity = totalBufferCapacity;
    this.getHowManyOrdersInBuffer = getHowManyOrdersInBuffer;
  }

  private final static String BUFFER_FULL_INFO = String.format(
      "%sBufor jest pełny. Złożenie następnego zamówienia będzie "
          + "zrealizowane po zwolnieniu bufora.%s%n",
      Colors.RED, Colors.RESET);

  private final static String NEW_ORDER_IN_QUEUE_INFO = String.format(
      "%sW buforze kolejki zostalo umieszczone nowe zamówienie:%s%n",
      Colors.BUFFER_INFO, Colors.RESET);

  private String getBufferSizeInfo() {
    return String.format("%sRozmiar bufora %d/%d%s %n",
        Colors.BUFFER_INFO, getHowManyOrdersInBuffer.get(), totalBufferCapacity,
        Colors.RESET);
  }

  public void infoBeforePuttingIntoBuffer() {
    String threadInfo = String
        .format("%sProducent%s, wątek nr. %s%n",
            Colors.THREAD_PRODUCER_INFO, Colors.RESET,
            Thread.currentThread().getId());
    if (totalBufferCapacity == getHowManyOrdersInBuffer.get()) {
      System.out.println(threadInfo + BUFFER_FULL_INFO);
    }
  }

  public void infoAfterPuttingIntoBuffer(Order newOrder) {
    String threadInfo = String
        .format("%sProducent%s, wątek nr. %s%n",
            Colors.THREAD_PRODUCER_INFO, Colors.RESET,
            Thread.currentThread().getId());

    String newOrderInfo = String.format("%s%n", newOrder);
    String bufferSizeInfo = getBufferSizeInfo();
    System.out.println(threadInfo + NEW_ORDER_IN_QUEUE_INFO + newOrderInfo +
        bufferSizeInfo);
  }
}
