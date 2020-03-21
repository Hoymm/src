package restaurants.chinese;

import restaurants.restaurant.Restaurant;

import java.util.Arrays;
import java.util.List;

public class HoanKiem extends Restaurant<ChineseDishes> {
  public HoanKiem() {
    super(12);
  }

  @Override
  public List<ChineseDishes> getDishesList() {
    return Arrays.asList(ChineseDishes.values());
  }
}
