package pl.com.muca.restaurants.restaurant;

import java.util.Random;
import java.util.function.Supplier;
import pl.com.muca.common.Colors;

class Workplace extends Thread {

  private static int stationNumberCounter = 1;

  private final int stationNumber = stationNumberCounter++;
  private final Colors color = Colors.getNextColor();
  private final Supplier<Order> orderSupplier;
  private Supplier<String> getBufferSizeInfo;
  private Supplier<Boolean> isBufferEmpty;

  private static final String TAKE_ORDER_INFO = String.format(
      "%sZamówienie zostaje ściągniete z bufora z bufora kolejki i "
          + "przekazane do przygotowania:%s%n",
      Colors.BUFFER_INFO, Colors.RESET);

  String BUFFER_EMPTY_INFO = String.format(
      "%sBufor jest pusty. Pobranie zamówienia będzie złożone po "
          + "umieszczeniu zamówienia w buforze kolejki.%s%n",
      Colors.RED, Colors.RESET);

  Workplace(Supplier<Order> orderSupplier, Supplier<String> getBufferSizeInfo,
      Supplier<Boolean> isBufferEmpty) {
    this.orderSupplier = orderSupplier;
    this.getBufferSizeInfo = getBufferSizeInfo;
    this.isBufferEmpty = isBufferEmpty;
    this.setName(toString());
  }

  @Override
  public String toString() {
    return String.format("Station Number %d", this.stationNumber);
  }

  @Override
  public void run() {
    tryToSleep(new Random().nextInt(14000) + 8000);
    while (true) {
      if (isBufferEmpty.get()) {
        System.out.println(BUFFER_EMPTY_INFO);
      }

      Order order = this.orderSupplier.get();
      order.setOrderState(OrderState.IS_BEING_PREPARED);
      printOrderStatus(order);

      // TODO change processing time in order to get exception because of
      // either empty or full buffer.
      int processingTime = 5000;
//      int processingTime = 25000;
      tryToSleep(new Random().nextInt(processingTime) + 3000);

      order.setOrderState(OrderState.READY_TO_PICKUP);
      printOrderStatus(order);

      tryToSleep(processingTime / 3);
    }
  }

  private void printOrderStatus(Order order) {
    String threadInfo = String
        .format("%sKonsument%s, wątek nr. %s%n",
            Colors.THREAD_CONSUMER_INFO, Colors.RESET,
            Thread.currentThread().getId());
    String takeOrderInfo = "";
    String bufferSizeInfo = "";
    if (order.isBeingPrepared()) {
      takeOrderInfo = TAKE_ORDER_INFO;
      bufferSizeInfo = getBufferSizeInfo.get();
    }

    String stationNumberInfo = getStationNumberInfo();
    String orderInfo = String.format("%s%n", order);
    System.out
        .println(threadInfo + takeOrderInfo + stationNumberInfo + orderInfo
            + bufferSizeInfo);
  }

  private String getStationNumberInfo() {
    return String.format("%s%s%s %n",
        this.color, this.toString(), Colors.RESET);
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
