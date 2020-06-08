package pl.com.muca.simulator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pl.com.muca.restaurants.restaurant.RestaurantClientApi;

public class OrdersSimulator implements Runnable {
  private static final Random random = new Random();
  private final RestaurantClientApi restaurantClientApi;
  private final List<String> dishes;
  private final int ordersNumber;

  public OrdersSimulator(RestaurantClientApi restaurantClientApi, int ordersNumber) {
    this.restaurantClientApi = restaurantClientApi;
    dishes = restaurantClientApi.getDishesList();
    this.ordersNumber = ordersNumber;
  }

  private void tryToSleep(long timeInMs) {
    try {
      Thread.sleep(timeInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    for (int i = 0; i < ordersNumber; ++i) {
      tryToSleep(new Random().nextInt(5000));
      List<String> randomDishes =
          Stream.generate(() -> random.nextInt(dishes.size()))
              .limit(random.nextInt(dishes.size()) / 2 + 1)
              .map(dishes::get)
              .collect(Collectors.toList());
      restaurantClientApi.makeOrder(randomDishes);
      tryToSleep(new Random().nextInt(35000) + 5000);
    }
  }
}
