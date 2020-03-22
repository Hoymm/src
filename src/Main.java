import restaurants.chinese.HoanKiem;
import restaurants.restaurant.Restaurant;
import simulator.OrdersSimulator;

public class Main {
  public static void main(String[] args) {
    Restaurant restaurant = new HoanKiem();
    restaurant.open();

    OrdersSimulator ordersSimulator = new OrdersSimulator(restaurant);
    ordersSimulator.simulate(20);
  }
}
