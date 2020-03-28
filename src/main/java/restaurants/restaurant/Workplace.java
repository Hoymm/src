package restaurants.restaurant;

import common.Colors;
import java.util.Random;
import java.util.function.Supplier;

class Workplace extends Thread{
  private static int stationNumberCounter = 1;

  private final int stationNumber = stationNumberCounter++;
  private final Colors color = Colors.getNextColor();
  private final Supplier<Order> orderSupplier;


  Workplace(Supplier <Order> orderSupplier) {
    this.orderSupplier = orderSupplier;
    this.setName(toString());
  }

  @Override
  public String toString() {
    return String.format("Station Number %d", this.stationNumber);
  }

  @Override
  public void run() {
    /* When removed, thread will try to get order from empty buffer.
      Such attempt will result in an exception.
     */
    // TODO comment out this line.
    tryToSleep(20000);

    Order order = this.orderSupplier.get();
    order.setOrderState(OrderState.IS_BEING_PREPARED);
    printOrderStatus(order);

    // TODO comment out this line.
    // Too long processing will result in buffer overflow and exception.
    int processingTime = 500;
//    int processingTime = 500;

    tryToSleep(new Random().nextInt(processingTime));

    order.setOrderState(OrderState.READY_TO_PICKUP);
    printOrderStatus(order);

    tryToSleep(3000);
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
