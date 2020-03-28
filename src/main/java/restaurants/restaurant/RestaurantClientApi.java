package restaurants.restaurant;

import java.util.List;

public interface RestaurantClientApi {
    void makeOrder(List <String> dishes);
    List<String> getDishesList();
}
