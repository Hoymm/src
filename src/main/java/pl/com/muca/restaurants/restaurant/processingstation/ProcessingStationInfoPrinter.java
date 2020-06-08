package pl.com.muca.restaurants.restaurant.processingstation;

import pl.com.muca.common.Colors;
import pl.com.muca.restaurants.restaurant.BufferInfo;
import pl.com.muca.restaurants.restaurant.order.Order;

public class ProcessingStationInfoPrinter {

  private static final String TAKE_ORDER_INFO = String.format(
      "%sZamówienie zostaje ściągniete z bufora z bufora kolejki i "
          + "przekazane do przygotowania:%s%n",
      Colors.CONSUMER_BUFFER_INFO, Colors.RESET);

  private static final String BUFFER_EMPTY_INFO = String.format(
      "%sBufor jest pusty. Pobranie zamówienia będzie złożone po "
          + "umieszczeniu zamówienia w buforze kolejki.%s%n",
      Colors.RED, Colors.RESET);

  private final BufferInfo bufferInfo;
  private final int stationNumber;

  public ProcessingStationInfoPrinter(BufferInfo bufferInfo,
      int stationNumber) {
    this.bufferInfo = bufferInfo;
    this.stationNumber = stationNumber;
  }

  public void infoBeforeProcessingStart() {
    if (bufferInfo.isEmpty()) {
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
        Colors.CONSUMER_BUFFER_INFO, bufferInfo.size(),
        bufferInfo.getTotalCapacity(),
        Colors.RESET);
  }

  private String getStationNumberInfo() {
    return String.format("Stanowisko Obsługi %d", this.stationNumber);
  }
}
