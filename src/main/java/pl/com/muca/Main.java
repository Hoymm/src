package pl.com.muca;

import java.util.stream.Stream;
import pl.com.muca.restaurants.polish.LoveSchabowe;
import pl.com.muca.restaurants.restaurant.Restaurant;
import pl.com.muca.simulator.OrdersSimulator;

class Main {
  private static final int PROCESSING_STATIONS_NUMBER = 4;
  private static final int ORDER_SIMULATORS_NUMBER = 5;
  private static final int ORDERS_PER_SIMULATOR = 10;

  public static void main(String[] args) {
    // Create a restaurant with 4 processing stations.
    // Each processing station works in different thread and processes given orders.
    // During processing a thread simulate preparing a meal,
    // the thread cannot take another order until the meal is prepared.
    Restaurant restaurant = new LoveSchabowe(PROCESSING_STATIONS_NUMBER);

    // Creates 5 ordering simulators.
    // Each simulator works in different thread and making 10 orders.
    Stream.generate(() -> new OrdersSimulator(restaurant))
        .limit(ORDER_SIMULATORS_NUMBER)
        .forEach(s -> s.simulate(ORDERS_PER_SIMULATOR));
  }
}
