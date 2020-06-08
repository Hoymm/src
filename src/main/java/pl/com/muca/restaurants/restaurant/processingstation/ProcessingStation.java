package pl.com.muca.restaurants.restaurant.processingstation;

import java.util.Random;
import java.util.function.Supplier;
import pl.com.muca.restaurants.restaurant.BufferInfo;
import pl.com.muca.restaurants.restaurant.order.Order;
import pl.com.muca.restaurants.restaurant.order.OrderState;

public class ProcessingStation implements Runnable {
  private static int stationNumberCounter = 0;
  private final int stationNumber;
  private final Supplier<Order> orderSupplier;
  private final ProcessingStationInfoPrinter processingStationInfoPrinter;
  private boolean isCloseCommandIssued = false;

  public ProcessingStation(Supplier<Order> orderSupplier, BufferInfo bufferInfo) {
    this.stationNumber = ++stationNumberCounter;
    this.orderSupplier = orderSupplier;
    this.processingStationInfoPrinter = new ProcessingStationInfoPrinter(bufferInfo, stationNumber);
  }

  @Override
  public void run() {
    while (!isCloseCommandIssued) {
      processingStationInfoPrinter.infoBeforeProcessingStart();

      Order order = this.orderSupplier.get();
      order.setOrderState(OrderState.IS_BEING_PREPARED);
      processingStationInfoPrinter.printOrderStatus(order);

      int processingTime = 35000;
      tryToSleep(new Random().nextInt(processingTime) + 10000);

      order.setOrderState(OrderState.READY_TO_PICKUP);

      processingStationInfoPrinter.printOrderStatus(order);
      tryToSleep(processingTime / 3);
    }
    System.out.println("###########################################################################");
    System.out.printf("Stanowisko obsługi zamówień nr.%d zostało zamknięte.\n", stationNumber);
    System.out.println("###########################################################################");
  }

  public void close() {
    this.isCloseCommandIssued = true;
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
