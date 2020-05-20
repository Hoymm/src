package pl.com.muca.restaurants.restaurant;

import pl.com.muca.common.Colors;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Workplace extends Thread {

  private static int stationNumberCounter = 1;

  private final int stationNumber = stationNumberCounter++;
  private final Colors color = Colors.getNextColor();
  private final BlockingQueue<Order> orderSupplier;


  Workplace(BlockingQueue<Order> orderSupplier) {
    this.orderSupplier = orderSupplier;
    this.setName(toString());
  }

  @Override
  public String toString() {
    return String.format("Station Number %d", this.stationNumber);
  }

  @Override
  public void run() {
    tryToSleep(new Random().nextInt(14000) + 3000);
    while (true) {
      try {
        Order order = this.orderSupplier.take();
        order.setOrderState(OrderState.IS_BEING_PREPARED);
        printOrderStatus(order);

        // TODO change processing time in order to get exception because of
        // either empty or full buffer.
//      int processingTime = 5000;
        int processingTime = 25000;
        tryToSleep(new Random().nextInt(processingTime));

        order.setOrderState(OrderState.READY_TO_PICKUP);
        printOrderStatus(order);

        tryToSleep(processingTime / 3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void printOrderStatus(Order order) {
    System.out.printf(
        "%s%s%s \n%s.\n\n", this.color, this.toString(), Colors.RESET, order);
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
