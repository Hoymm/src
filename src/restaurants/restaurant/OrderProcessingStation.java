package restaurants.restaurant;

import common.Colors;

import java.util.NoSuchElementException;
import java.util.Random;

class OrderProcessingStation  {
  private static int stationNumberCounter = 1;
  private final int stationNumber;
  private final Restaurant  restaurant; // TODO remove this dependency.
  private final Colors color;
  private boolean isOpen;

  OrderProcessingStation(Restaurant  restaurant) {
    this.restaurant = restaurant;
    this.color = Colors.getNextColor();
    this.stationNumber = stationNumberCounter++;
  }

  private void printOrderStatus(Order order) {
    System.out.printf(
            "%sStation Number %d \n%s.%s\n", this.color, stationNumber, order
            , Colors.RESET);
  }

  void open() {
    this.isOpen = true;
    startNewThreadForOrderProcessing();
  }

  private void startNewThreadForOrderProcessing() {
    new Thread(
            () -> {
              while (this.isOpen) {
                tryToProcessOrder();
              }
            })
        .start();
  }

  private void tryToProcessOrder() {
    try {
      processOrder();
    }
    // TODO handle it somehow differently.
    catch (NoSuchElementException e) {
      tryToSleep(100);
    }
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void processOrder() {
    Order order = restaurant.ordersToBePrepared.remove();
    order.setOrderState(OrderState.IS_BEING_PREPARED);
    printOrderStatus(order);

    tryToSleep(new Random().nextInt(8000));

    order.setOrderState(OrderState.READY_TO_PICKUP);
    printOrderStatus(order);

    tryToSleep(3000);
  }

  void close() {
    this.isOpen = false;
  }
}
