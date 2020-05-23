package pl.com.muca;

import java.util.Scanner;
import pl.com.muca.common.Colors;
import pl.com.muca.restaurants.RestaurantType;

public class UserInfoReader {

  private final Scanner scanner;

  public UserInfoReader() {
    this.scanner = new Scanner(System.in);
  }

  public AppParameters readApplicationParameters() {

    System.out.printf("\n\t%s ########## Witaj w symulatorze restauracji %s\n",
        Colors.BLUE,
        Colors.RESET);
    System.out.println("\t ########## Czas przygotowywania posiłku trwa od 10 do 45 sekund.");
    System.out.println(
        "\t ########## Symulator również składa zamówienie składa zamówienie co 10-45 "
            + "sekund.");

    return AppParameters.builder()
        .setOrderSimulatorsNumber(readOrderSimulatorNumbers())
        .setOrdersPerSimulator(readOrdersPerSimulator())
        .setProcessingStationsNumber(readProcessingStationsNumber())
        .setRestaurantType(readRestaurantType())
        .build();
  }

  private int readOrderSimulatorNumbers() {
    return readInt("Wybierz ilośc symulatorów składających zamówienia");
  }

  private int readOrdersPerSimulator() {
    return readInt("Podaj liczbę zamówień jaką ma złożyć każdy symulator");
  }

  private int readProcessingStationsNumber() {
    return readInt("Podaj ilość stanowisk do obsługi zamówień w restauracji");
  }

  private RestaurantType readRestaurantType() {
    int restaurantTypeNumber = readInt(
        "Wybierz typ restauracji \n"
            + "\t1.Chińska \n"
            + "\t2.Włoska \n"
            + "\t3.Polska \n"
            + "Podaj numer");

    switch (restaurantTypeNumber) {
      case 1:
        return RestaurantType.CHINESE;
      case 2:
        return RestaurantType.ITALIAN;
      case 3:
        return RestaurantType.POLISH;
      default:
        return readRestaurantType();
    }
  }

  private int readInt(String messageToDisplay) {
    System.out.printf("%s: ", messageToDisplay);
    return scanner.nextInt();
  }
}
