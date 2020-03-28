package restaurants.restaurant;

import common.Colors;
import java.util.Random;
import java.util.function.Supplier;

class Workplace implements Runnable{
  private static int stationNumberCounter = 1;

  private final int stationNumber = stationNumberCounter++;
  private final Colors color = Colors.getNextColor();
  private final WorkplaceReleaseNotifier workplaceReleaseNotifier;
  private final Supplier<Order> orderSupplier;
  private final Thread orderProcessing;


  Workplace(WorkplaceReleaseNotifier workplaceReleaseNotifier, Supplier <Order> orderSupplier) {
    this.workplaceReleaseNotifier = workplaceReleaseNotifier;
    this.orderSupplier = orderSupplier;
    this.orderProcessing = new Thread(this);
    this.orderProcessing.start();
  }

  private void printOrderStatus(Order order) {
    System.out.printf(
        "%sStation Number %d%s \n%s.\n\n", this.color, stationNumber, Colors.RESET, order);
  }



//  Thread processOrder(Supplier <Order> orderSupplier) {
//    Order order = orderSupplier.get();
//    return new Thread(
//            () -> {
//
//              order.setOrderState(OrderState.IS_BEING_PREPARED);
//              printOrderStatus(order);
//
//              tryToSleep(new Random().nextInt(20000));
//
//              order.setOrderState(OrderState.READY_TO_PICKUP);
//              printOrderStatus(order);
//
//              tryToSleep(3000);
//              workplaceReleaseNotifier.notify(this);
//            },"Order processing thread " + order.toString()
//            )
//        .start();
//  }

  @Override
  public void run() {
    tryToSleep(5000); // todo remove
    Order order = this.orderSupplier.get();
    order.setOrderState(OrderState.IS_BEING_PREPARED);
    printOrderStatus(order);

    tryToSleep(new Random().nextInt(20000));

    order.setOrderState(OrderState.READY_TO_PICKUP);
    printOrderStatus(order);

    tryToSleep(3000);
    workplaceReleaseNotifier.notify(this);
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    }
}
