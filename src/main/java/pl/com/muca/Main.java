package pl.com.muca;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pl.com.muca.restaurants.RestaurantType;
import pl.com.muca.restaurants.restaurant.Restaurant;
import pl.com.muca.simulator.OrdersSimulator;

class Main {

  public static void main(String[] args) {
    AppParameters appParameters;
    if (args.length == 3) {
      appParameters =
          AppParameters.builder()
              .setProcessingStationsNumber(Integer.parseInt(args[0]))
              .setOrderSimulatorsNumber(Integer.parseInt(args[1]))
              .setOrdersPerSimulator(Integer.parseInt(args[2]))
              .setRestaurantType(RestaurantType.getRandomType())
              .build();
    } else {
      // Read custom application parameters from user.
      appParameters = new AppParametersReader().readFromUser();
      System.out.println("Uruchamiam stanowiska w restauracji i oczekuję na zamówienia... :)");
    }

    Restaurant restaurant = initRestaurant(appParameters);

    List<OrdersSimulator> orderSimulators =
        Stream.generate(
                () -> new OrdersSimulator(restaurant, appParameters.getOrdersPerSimulator()))
            .limit(appParameters.getOrderSimulatorsNumber())
            .collect(Collectors.toList());
    ExecutorService simulatorsExecutor =
        Executors.newFixedThreadPool(appParameters.getOrderSimulatorsNumber());
    orderSimulators.forEach(simulatorsExecutor::submit);

    simulatorsExecutor.shutdown();

    try {
      simulatorsExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      System.out.println(
          "###########################################################################");
      System.out.println("Zakończył się proces składania zamówień.");
      System.out.println("Symulatory zamówień zostały wyłączone.");
      System.out.println("Kończę zamówienia i zamykam stanowiska przetwarzania.");
      System.out.println(
          "###########################################################################");
      System.out.println();
      restaurant.closeRestaurant();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ExecutorService processingStationsExecutor = restaurant.getProcessingStationsExecutor();
    processingStationsExecutor.shutdown();
    try {
      processingStationsExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      System.out.println(
          "###########################################################################");
      System.out.println("Wszystkie zamówienia zostały zrealizowane.");
      System.out.println("Restauracja została zamknięta.");
      System.out.println("Zapraszamy ponownie! :)");
      System.out.println(
          "###########################################################################");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // Create a restaurant with processing stations.
  // Each processing station works in different thread and processes given
  // orders. During processing a thread simulate preparing a meal,
  // the thread cannot take another order until the meal is prepared.
  private static Restaurant initRestaurant(AppParameters appParameters) {
    int processingStationsNumber = appParameters.getProcessingStationsNumber();
    RestaurantType restaurantType = appParameters.getRestaurantType();
    return restaurantType.generateRestaurant(processingStationsNumber);
  }
}
