package restaurants.italian;

import java.util.Arrays;
import java.util.List;
import restaurants.restaurant.Restaurant;

public class Toscana extends Restaurant {

  public Toscana(int howManyProcessingStations) {
    super(howManyProcessingStations);
  }

  @Override
  public List<String> getDishesList() {
    return Arrays.asList(
        "Panzenella",
        "Bruschetta",
        "Focaccia Bread",
        "Pasta Carbonara",
        "Margherita Pizza",
        "Mushroom Risotto");
  }
}
