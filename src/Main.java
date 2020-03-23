import restaurants.chinese.HoanKiem;
import restaurants.italian.Toscana;
import restaurants.polish.LoveSchabowe;
import restaurants.restaurant.Restaurant;
import simulator.OrdersSimulator;

class Main {
  public static void main(String[] args){
//    Restaurant restaurant = new HoanKiem(5);
//    Restaurant restaurant = new Toscana(5);
    Restaurant restaurant = new LoveSchabowe(5);

    OrdersSimulator ordersSimulator = new OrdersSimulator(restaurant);
    ordersSimulator.simulate(20);
  }
}
