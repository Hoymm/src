package pl.com.muca;

import java.util.stream.Stream;
import pl.com.muca.restaurants.polish.LoveSchabowe;
import pl.com.muca.restaurants.restaurant.Restaurant;
import pl.com.muca.simulator.OrdersSimulator;

class Main {
  public static void main(String[] args) {
    Restaurant restaurant = new LoveSchabowe(5);

    // Creates 10 order simulators, each make 3 orders.
    Stream.generate(() -> new OrdersSimulator(restaurant)).limit(1)
        .forEach(s -> s.simulate(3));
  }
}
