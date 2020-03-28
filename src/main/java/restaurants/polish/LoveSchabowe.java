package restaurants.polish;

import java.util.Arrays;
import java.util.List;
import restaurants.restaurant.Restaurant;

public class LoveSchabowe extends Restaurant {

  public LoveSchabowe(int howManyProcessingStations) {
    super(howManyProcessingStations);
  }

  @Override
  public List<String> getDishesList() {
    return Arrays.asList(
        "Placek Ziemniaczany",
        "Jaja Sadzone",
        "Naleśnik z Serem",
        "Naleśnik z Dżemem",
        "Naleśnik ze Szpinakiem",
        "Pierogi Ruskie",
        "Pierogi z Mięsem",
        "Filet z Ryby",
        "Schabowy z Ziemniakami",
        "Schabowy XXL",
        "Mega schabowy",
        "Schabowy po Parysku",
        "Schabowy w Panierce");
  }
}
