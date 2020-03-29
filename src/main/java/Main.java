import java.util.stream.Stream;
import restaurants.polish.LoveSchabowe;
import restaurants.restaurant.Restaurant;
import simulator.OrdersSimulator;

class Main {
  public static void main(String[] args) {
    Restaurant restaurant = new LoveSchabowe(5);

    // Creates 10 order simulators, each make 3 orders.
    Stream.generate(() -> new OrdersSimulator(restaurant)).limit(10)
        .forEach(s -> s.simulate(3));
  }
}
