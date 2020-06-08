package pl.com.muca.restaurants;

import java.util.Random;
import pl.com.muca.restaurants.chinese.HoanKiem;
import pl.com.muca.restaurants.italian.Toscana;
import pl.com.muca.restaurants.polish.LoveSchabowe;
import pl.com.muca.restaurants.restaurant.Restaurant;

public enum RestaurantType {
  CHINESE {
    @Override
    public Restaurant generateRestaurant(int howManyProcessingStations) {
      return new HoanKiem(howManyProcessingStations);
    }
  },
  ITALIAN {
    @Override
    public Restaurant generateRestaurant(int howManyProcessingStations) {
      return new Toscana(howManyProcessingStations);
    }
  },
  POLISH {
    @Override
    public Restaurant generateRestaurant(int howManyProcessingStations) {
      return new LoveSchabowe(howManyProcessingStations);
    }
  };

  RestaurantType() {}

  public static RestaurantType getRandomType() {
    int typeIndex = new Random().nextInt(RestaurantType.values().length);
    return RestaurantType.values()[typeIndex];
  }

  public abstract Restaurant generateRestaurant(int howManyProcessingStations);
}
