package pl.com.muca;

import java.util.stream.Stream;
import pl.com.muca.restaurants.RestaurantType;
import pl.com.muca.restaurants.restaurant.Restaurant;
import pl.com.muca.simulator.OrdersSimulator;

class Main {


  public static void main(String[] args) {
    // Read custom application parameters from user.
    AppParameters appParameters =
        new UserInfoReader().readApplicationParameters();
    System.out.println(
        "Uruchamiam stanowiska w restauracji i oczekuję na zamówienia... :)");

    // Creates restaurant chosen by user.
    Restaurant restaurant = initRestaurant(appParameters);

    // Initialize simulator to orders meals.
    initOrdersSimulator(appParameters, restaurant);
  }

  // Creates ordering simulators.
  // Each simulator works in different thread.
  private static void initOrdersSimulator(AppParameters appParameters,
      Restaurant restaurant) {
    Stream.generate(() -> new OrdersSimulator(restaurant))
        .limit(appParameters.getOrderSimulatorsNumber())
        .forEach(s -> s.simulate(appParameters.getOrdersPerSimulator()));
  }

  // Create a restaurant with processing stations.
  // Each processing station works in different thread and processes given
  // orders. During processing a thread simulate preparing a meal,
  // the thread cannot take another order until the meal is prepared.
  private static Restaurant initRestaurant(AppParameters appParameters) {
    int processingStationsNumber = appParameters.getProcessingStationsNumber();
    RestaurantType restaurantType = appParameters.getRestaurantType();
    return restaurantType
        .generateRestaurant(processingStationsNumber);
  }
}
