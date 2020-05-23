package pl.com.muca.restaurants.restaurant.workplace;

import java.util.Random;
import java.util.function.Supplier;
import pl.com.muca.restaurants.restaurant.Order;
import pl.com.muca.restaurants.restaurant.OrderState;

public class Workplace extends Thread {

  private static int stationNumberCounter = 0;
  private final Supplier<Order> orderSupplier;
  private final WorkplaceInfoPrinter workplaceInfoPrinter;

  public Workplace(Supplier<Order> orderSupplier,
      Supplier<Integer> getHowManyOrdersInBuffer, int totalBufferCapacity) {
    ++stationNumberCounter;
    this.orderSupplier = orderSupplier;
    this.workplaceInfoPrinter = new WorkplaceInfoPrinter(totalBufferCapacity,
        getHowManyOrdersInBuffer, stationNumberCounter);
  }

  @Override
  public void run() {
    tryToSleep(new Random().nextInt(14000) + 8000);
    while (true) {
      workplaceInfoPrinter.infoBeforeProcessingStart();

      Order order = this.orderSupplier.get();
      order.setOrderState(OrderState.IS_BEING_PREPARED);
      workplaceInfoPrinter.printOrderStatus(order);

      // TODO change processing time to get empty or full buffer.
      int processingTime = 5000;
      // int processingTime = 25000;
      tryToSleep(new Random().nextInt(processingTime) + 3000);

      order.setOrderState(OrderState.READY_TO_PICKUP);

      workplaceInfoPrinter.printOrderStatus(order);
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
