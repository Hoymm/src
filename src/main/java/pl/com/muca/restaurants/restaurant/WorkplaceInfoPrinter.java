package pl.com.muca.restaurants.restaurant;

import java.util.function.Supplier;
import pl.com.muca.common.Colors;

public class WorkplaceInfoPrinter {

  private static final String TAKE_ORDER_INFO = String.format(
      "%sZamówienie zostaje ściągniete z bufora z bufora kolejki i "
          + "przekazane do przygotowania:%s%n",
      Colors.CONSUMER_BUFFER_INFO, Colors.RESET);

  String BUFFER_EMPTY_INFO = String.format(
      "%sBufor jest pusty. Pobranie zamówienia będzie złożone po "
          + "umieszczeniu zamówienia w buforze kolejki.%s%n",
      Colors.RED, Colors.RESET);

  private int totalBufferCapacity;
  private Supplier<Integer> getHowManyOrdersInBuffer;
  private int stationNumber;

  public WorkplaceInfoPrinter(int totalBufferCapacity,
      Supplier<Integer> getHowManyOrdersInBuffer,
      int stationNumber) {
    this.totalBufferCapacity = totalBufferCapacity;
    this.getHowManyOrdersInBuffer = getHowManyOrdersInBuffer;
    this.stationNumber = stationNumber;
  }

  public void infoBeforeProcessingStart() {
    if (getHowManyOrdersInBuffer.get() == 0) {
      System.out.println(BUFFER_EMPTY_INFO);
    }
  }

  void printOrderStatus(Order order) {
    String threadInfo = String
        .format("%sKonsument%s, wątek nr. %s%n",
            Colors.CONSUMER_THREAD_INFO, Colors.RESET,
            Thread.currentThread().getId());
    String takeOrderInfo = "";
    String bufferSizeInfo = "";
    if (order.isBeingPrepared()) {
      takeOrderInfo = TAKE_ORDER_INFO;
      bufferSizeInfo = getBufferSizeInfo();
    }

    String stationNumberInfo = getStationNumberInfo();
    String orderInfo = String.format("%s%n", order);
    System.out
        .println(threadInfo + takeOrderInfo + stationNumberInfo + orderInfo
            + bufferSizeInfo);
  }

  private String getBufferSizeInfo() {
    return String.format("%sRozmiar bufora %d/%d%s %n",
        Colors.CONSUMER_BUFFER_INFO, getHowManyOrdersInBuffer.get(), this.totalBufferCapacity,
        Colors.RESET);
  }

  private String getStationNumberInfo() {
    return String.format("Station Number %d", this.stationNumber);
  }

  public void setStationNumber(int stationNumber) {
    this.stationNumber = stationNumber;
  }
}
