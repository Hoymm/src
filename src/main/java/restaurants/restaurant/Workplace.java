package restaurants.restaurant;

import common.Colors;
import java.util.Random;

class Workplace {
  private static int stationNumberCounter = 1;

  private final int stationNumber = stationNumberCounter++;
  private final Colors color = Colors.getNextColor();
  private final WorkplaceReleaseNotifier workplaceReleaseNotifier;

  Workplace(WorkplaceReleaseNotifier workplaceReleaseNotifier) {
    this.workplaceReleaseNotifier = workplaceReleaseNotifier;
  }

  private void printOrderStatus(Order order) {
    System.out.printf(
        "%sStation Number %d%s \n%s.\n\n", this.color, stationNumber, Colors.RESET, order);
  }

  void processOrder(Order order) {
    new Thread(
            () -> {
              order.setOrderState(OrderState.IS_BEING_PREPARED);
              printOrderStatus(order);

              tryToSleep(new Random().nextInt(20000));

              order.setOrderState(OrderState.READY_TO_PICKUP);
              printOrderStatus(order);

              tryToSleep(3000);
              workplaceReleaseNotifier.notify(this);
            },
            "Order processing thread " + order.toString())
        .start();
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
