package restaurants.restaurant;

import java.util.List;

public interface RestaurantClientApi<Dishes extends Enum<Dishes>> {
    List<Dishes> getDishesList();
    OrderInfo makeOrder(List<Dishes> dishes);
    void retrieveOrder(OrderInfo orderInfo);
}
