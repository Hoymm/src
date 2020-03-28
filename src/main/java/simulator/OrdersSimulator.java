package simulator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import restaurants.restaurant.RestaurantClientApi;

public class OrdersSimulator {
  private static final Random random = new Random();
  private final RestaurantClientApi restaurantClientApi;
  private final List<String> dishes;

  public OrdersSimulator(RestaurantClientApi restaurantClientApi) {
    this.restaurantClientApi = restaurantClientApi;
    dishes = restaurantClientApi.getDishesList();
  }

  public void simulate(int ordersNumber) {
    new Thread(
            () -> {
              for (int i = 0; i < ordersNumber; ++i) {
                List<String> randomDishes =
                    Stream.generate(() -> random.nextInt(dishes.size()))
                        .limit(random.nextInt(dishes.size()) / 2 + 1)
                        .map(dishes::get)
                        .collect(Collectors.toList());
                restaurantClientApi.makeOrder(randomDishes);

                try {
                  Thread.sleep(5000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
  }
}
