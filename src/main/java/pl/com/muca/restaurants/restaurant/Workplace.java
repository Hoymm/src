package pl.com.muca.restaurants.restaurant;

import java.util.Random;
import java.util.function.Supplier;
import pl.com.muca.common.Colors;

// TODO (Damian Muca): 5/23/20 move to package workplace.
class Workplace extends Thread {

  private static int stationNumberCounter = 0;
  private final Supplier<Order> orderSupplier;
  private final WorkplaceInfoPrinter workplaceInfoPrinter;

  Workplace(Supplier<Order> orderSupplier,
      Supplier<Integer> getHowManyOrdersInBuffer, int totalBufferCapacity) {
    ++stationNumberCounter;
    this.orderSupplier = orderSupplier;
    this.workplaceInfoPrinter = new WorkplaceInfoPrinter(stationNumberCounter,
        getHowManyOrdersInBuffer, totalBufferCapacity);
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
