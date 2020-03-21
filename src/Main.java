import java.util.Random;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {
    Restaurant restaurant = new Restaurant();
    restaurant.openRestaurant();

    Dish[] dishes = Dish.values();
    IntStream.generate(() -> new Random().nextInt(dishes.length))
             .limit(20)
             .forEach(i -> restaurant.makeOrder(dishes[i%dishes.length]));
  }
}
