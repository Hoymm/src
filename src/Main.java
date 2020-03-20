public class Main {
  public static void main(String[] args) {
    Restaurant restaurant = new Restaurant();
    restaurant.openRestaurant();

    restaurant.makeOrder(Dish.CHIPS);
    restaurant.makeOrder(Dish.PIZZA);
    restaurant.makeOrder(Dish.SALAD);
    restaurant.makeOrder(Dish.HAMBURGER);
    restaurant.makeOrder(Dish.CHIPS);
    restaurant.makeOrder(Dish.CHIPS);
    restaurant.makeOrder(Dish.PIZZA);
    restaurant.makeOrder(Dish.SALAD);
    restaurant.makeOrder(Dish.HAMBURGER);
    restaurant.makeOrder(Dish.CHIPS);
    restaurant.makeOrder(Dish.HAMBURGER);
  }
}
