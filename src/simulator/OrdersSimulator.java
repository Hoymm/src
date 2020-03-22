package simulator;

import restaurants.restaurant.RestaurantClientApi;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OrdersSimulator {
  private RestaurantClientApi restaurantClientApi;

  public OrdersSimulator(RestaurantClientApi restaurantClientApi) {
    this.restaurantClientApi = restaurantClientApi;
  }

  public void simulate(int ordersNumber) {
    List dishes = restaurantClientApi.getDishesList();
    IntStream.generate(() -> new Random().nextInt(dishes.size()))
        .limit(ordersNumber)
        .forEach(
            i ->
                restaurantClientApi.makeOrder(
                    Arrays.asList(
                        dishes.get(i % dishes.size()),
                        dishes.get(i * new Random().nextInt(3) % dishes.size()))));
  }
}
