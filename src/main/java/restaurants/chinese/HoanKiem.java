package restaurants.chinese;

import java.util.Arrays;
import java.util.List;
import restaurants.restaurant.Restaurant;

public class HoanKiem extends Restaurant {
  public HoanKiem(int howManyProcessingStations) {
    super(howManyProcessingStations);
  }

  @Override
  public List<String> getDishesList() {
    return Arrays.asList(
        "Sweet and sour",
        "Spring roll",
        "Fried shrimp",
        "Chow mein",
        "Dumpling",
        "Fried rice",
        "Kung Pao chicken");
  }
}
