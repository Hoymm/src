import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Random;

public class OrderProcessingStation extends Thread {
  private static int stationNumberCounter = 1;
  private final int stationNumber;
  private final Restaurant restaurant;
  private final Color color;

  public OrderProcessingStation(Restaurant restaurant) {
    this.restaurant = restaurant;
    this.color = Color.getNextColor();
    this.stationNumber = stationNumberCounter++;
  }

  @Override
  public void run() {
    while (this.restaurant.isOpen()) {
      try {
        Order order = restaurant.orders.remove();
        order.setOrderState(OrderState.IS_BEING_PREPARED);
        printOrderStatus(order);

        simulateProcessing();

        order.setOrderState(OrderState.READY_TO_PICKUP);
        printOrderStatus(order);

        simulateBreak();
      }
      // TODO handle it somehow differently.
      catch (NoSuchElementException e) {
        tryToSleep(100);
      }
    }
  }

  private PrintStream printOrderStatus(Order order) {
    return System.out.printf(
            "%sStation Number %d \n%s.%s\n", this.color, stationNumber, order,
            Color.RESET);
  }

  private void simulateBreak() {
    tryToSleep(1500);
  }

  private void simulateProcessing() {
    tryToSleep(new Random().nextInt(8000));
  }

  private void tryToSleep(long l) {
    try {
      Thread.sleep(l);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
