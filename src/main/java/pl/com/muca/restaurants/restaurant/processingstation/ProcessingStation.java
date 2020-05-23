package pl.com.muca.restaurants.restaurant.processingstation;

import java.util.Random;
import java.util.function.Supplier;
import pl.com.muca.restaurants.restaurant.BufferInfo;
import pl.com.muca.restaurants.restaurant.order.Order;
import pl.com.muca.restaurants.restaurant.order.OrderState;

public class ProcessingStation extends Thread {

  private static int stationNumberCounter = 0;
  private final Supplier<Order> orderSupplier;
  private final ProcessingStationInfoPrinter processingStationInfoPrinter;

  public ProcessingStation(Supplier<Order> orderSupplier,
      BufferInfo bufferInfo) {
    int currentStationNumber = ++stationNumberCounter;
    this.orderSupplier = orderSupplier;
    this.processingStationInfoPrinter = new ProcessingStationInfoPrinter(
        bufferInfo, currentStationNumber);
  }

  @Override
  public void run() {
    while (true) {
      processingStationInfoPrinter.infoBeforeProcessingStart();

      Order order = this.orderSupplier.get();
      order.setOrderState(OrderState.IS_BEING_PREPARED);
      processingStationInfoPrinter.printOrderStatus(order);

      // TODO change processing time to get empty or full buffer.
      int processingTime = 35000;
      tryToSleep(new Random().nextInt(processingTime) + 10000);

      order.setOrderState(OrderState.READY_TO_PICKUP);

      processingStationInfoPrinter.printOrderStatus(order);
      tryToSleep(processingTime / 3);
    }
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
