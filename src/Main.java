import restaurants.chinese.HoanKiem;
import restaurants.italian.Toscana;
import restaurants.restaurant.Restaurant;
import simulator.OrdersSimulator;

class Main {
  public static void main(String[] args){
    Restaurant restaurant = new HoanKiem(5);
//    Restaurant restaurant = new Toscana(5);
    restaurant.open();

    OrdersSimulator ordersSimulator = new OrdersSimulator(restaurant);
    ordersSimulator.simulate(20);
  }
}
